/**
 * Interfaz que define la estructura de los datos necesarios para mostrar un cuadro de diálogo de alerta en la aplicación. Esta interfaz incluye propiedades como el título del diálogo, el mensaje a mostrar, el tipo de alerta (éxito, error, advertencia o información), y los textos opcionales para los botones de confirmación y cancelación. Esta interfaz se utiliza para tipar los datos que se pasan al servicio de alertas cuando se desea mostrar un cuadro de diálogo de alerta, asegurando que se proporcionen los datos necesarios para configurar correctamente el diálogo y mejorar la experiencia del usuario al mostrar mensajes claros y relevantes.
 */
export interface AlertDialogData {
    title?: string;
    message: string;
    type?: AlertType;
    confirmButtonText?: string;
    cancelButtonText?: string;
}

/**
 * Interfaz que define la estructura del resultado devuelto por un cuadro de diálogo de alerta en la aplicación. Esta interfaz incluye una propiedad booleana llamada "confirmed" que indica si el usuario confirmó la acción representada por el cuadro de diálogo (por ejemplo, haciendo clic en un botón de confirmación) o si canceló la acción (por ejemplo, haciendo clic en un botón de cancelación o cerrando el diálogo). Esta interfaz se utiliza para tipar los datos que se reciben del servicio de alertas después de que el usuario interactúa con el cuadro de diálogo, permitiendo a los componentes manejar adecuadamente las acciones del usuario en función de si confirmaron o cancelaron la acción representada por el diálogo.
 */
export interface AlertDialogResult {
    confirmed: boolean | null;
}

/**
 * Tipo que define las posibles categorías de alertas que se pueden mostrar en la aplicación. Este tipo es una unión de literales de cadena que incluye los valores 'success' (éxito), 'error' (error), 'warning' (advertencia) e 'info' (información). Este tipo se utiliza para tipar la propiedad "type" en la interfaz AlertDialogData, lo que permite especificar claramente el tipo de alerta que se desea mostrar en un cuadro de diálogo, mejorando la claridad y la experiencia del usuario al proporcionar mensajes visualmente representativos del tipo de información que se está comunicando.
 */
export type AlertType = 'success' | 'error' | 'warning' | 'info';