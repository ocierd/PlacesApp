-- =============================================
-- Author:		Fernando Ricardo Morán
-- Create date: 2026-05-18
-- Description:	Busca países por nombre
-- =============================================

CREATE PROCEDURE sp_pais_buscar(
	@nombre VARCHAR(100)
)
AS
BEGIN
	
	SELECT TOP 10 p.pais_id, p.nombre, p.nacionalidad, p.codigo, p.iso_2, p.iso_3
	FROM pais p
	WHERE p.nombre COLLATE Latin1_general_CI_AI like '%'+ @nombre + '%'
	ORDER BY p.nombre ASC

END