-- ============================================================
-- SP: sp_menu_por_usuario_modulo
-- Author:		Fernando Ricardo Morán
-- Create date: 2026-05-20
-- Description:	Obtiene el menú de navegación para un usuario específico y un módulo específico (si se proporciona).
-- ============================================================

CREATE OR ALTER PROCEDURE sp_menu_por_usuario_modulo(
	@usuarioId BIGINT,
	@moduloId TINYINT NULL
)
AS
BEGIN
	
WITH m__ as (
	
		SELECT m.menu_id, m.nombre, CAST(CONCAT(md.ruta,m.ruta) AS VARCHAR(255)) as ruta, m.icono, m.padre_menu_id, m.modulo_id, 1 as level 
		FROM menu m	
		INNER JOIN modulo md ON md.modulo_id = m.modulo_id
		WHERE m.padre_menu_id IS NULL AND (@moduloId IS NULL OR md.modulo_id = @moduloId)
		
		UNION ALL
		
		SELECT m.menu_id, m.nombre, CAST(CONCAT(me.ruta,m.ruta) AS VARCHAR(255)) as ruta, m.icono, m.padre_menu_id, m.modulo_id, me.level + 1 as level
		FROM menu m	
		INNER JOIN m__ me on me.menu_id = m.padre_menu_id 
		WHERE m.padre_menu_id IS NOT NULL
		
	)
	
	SELECT DISTINCT m.menu_id, m.nombre, m.ruta, m.icono, m.padre_menu_id, m.modulo_id, m.level
	FROM m__ m
	INNER JOIN rol_menu rm ON rm.menu_id = m.menu_id
	INNER JOIN usuario_rol ur ON ur.rol_id = rm.rol_id 
	WHERE ur.usuario_id = @usuarioId
	ORDER BY m.menu_id,m.padre_menu_id ASC;
	
END