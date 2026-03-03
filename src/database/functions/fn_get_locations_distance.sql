-- Obtiene la distancia en KM entre 2 ubicaciones
CREATE OR ALTER FUNCTION fn_get_locations_distance(
	@latitud1	DECIMAL(8,6),   -- Ubicación 1
	@longitud1 DECIMAL(9,6),  -- Ubicación 1
  @latitud2	DECIMAL(8,6),   -- Ubicación 2
	@longitud2 DECIMAL(9,6)   -- Ubicación 2
)
RETURNS DECIMAL(10,6)
AS
BEGIN
  
  DECLARE @distance DECIMAL(10,6);
  DECLARE @radioTierra INT = 6371; -- km

  SET @distance = @radioTierra * ACOS(
        COS(RADIANS(@latitud1)) * COS(RADIANS(@latitud2)) *
				COS(RADIANS(@longitud2) - RADIANS(@longitud1)) +
				SIN(RADIANS(@latitud1)) * SIN(RADIANS(@latitud2)));

  RETURN(@distance);

END