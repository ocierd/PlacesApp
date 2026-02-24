-- SP para insertar una nueva sucursal con su ubicación asociada
-- Este procedimiento almacenado permite insertar una nueva sucursal junto con su ubicación asociada. 
-- Si no se proporciona una ubicación, se insertará una nueva ubicación con los datos proporcionados.
CREATE PROCEDURE sp_sucursal_insert(
    @empresa_id INT,
    @nombre_sucursal VARCHAR(255),
    @ubicacion_id BIGINT NULL,
    @latitud DECIMAL(8,6) NULL,
    @longitud DECIMAL(9,6) NULL,
    @enlace_maps VARCHAR(255) NULL,
    @sucursal_id BIGINT NULL OUT
)
AS
BEGIN

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Si no se proporcionó una ubicación, insertarla y obtener su ID
        IF @ubicacion_id IS NULL AND @latitud IS NOT NULL AND @longitud IS NOT NULL
        BEGIN
            DECLARE @UbicacionTbl TABLE (UbicacionId BIGINT);

            INSERT INTO ubicacion (latitud, longitud, enlace_maps)
            OUTPUT INSERTED.ubicacion_id INTO @UbicacionTbl
            VALUES (@latitud, @longitud, @enlace_maps);

            -- Obtener el ID de la ubicación recién insertada
            SELECT @ubicacion_id = UbicacionId FROM @UbicacionTbl;
        END

        DECLARE @SucursalTbl TABLE (SucursalId BIGINT);

        -- Asociar la ubicación con la empresa (asumiendo que hay una relación en la tabla sucursal)
        INSERT INTO sucursal (nombre, empresa_id, ubicacion_id)
        OUTPUT INSERTED.sucursal_id INTO @SucursalTbl
        VALUES (@nombre_sucursal, @empresa_id, @ubicacion_id);

        
        SELECT @sucursal_id=SucursalId FROM @SucursalTbl;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        -- Manejo de errores (puedes personalizar esto según tus necesidades)
        DECLARE @ErrorMessage NVARCHAR(4000);
        DECLARE @ErrorSeverity INT;
        DECLARE @ErrorState INT;

        SELECT 
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();

        RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH;

END