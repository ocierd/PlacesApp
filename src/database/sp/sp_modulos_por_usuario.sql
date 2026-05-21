-- ============================================================
-- SP: sp_modulos_por_usuario
-- Author:		Fernando Ricardo Morán
-- Create date: 2026-05-20
-- Description:	Obtiene la lista de módulos a los que tiene acceso un usuario específico.
-- ============================================================

CREATE OR ALTER PROCEDURE sp_modulos_por_usuario(
	@usuarioId BIGINT
)
AS
BEGIN
	
	SELECT m.modulo_id , m.nombre , m.descripcion , m.ruta 
	FROM modulo m 
	WHERE m.modulo_id IN (
		SELECT mo.modulo_id  
		FROM modulo mo
		INNER JOIN menu m on m.modulo_id = mo.modulo_id 
		INNER JOIN rol_menu rm ON rm.menu_id = m.menu_id 
		INNER JOIN rol r ON r.rol_id  = rm.rol_id
		INNER JOIN usuario_rol ur ON ur.rol_id  = r.rol_id 
		WHERE ur.usuario_id = @usuarioId )
    ORDER BY m.nombre ASC;
	
	
END