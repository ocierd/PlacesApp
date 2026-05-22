export const DEVELOPER_MENU_DATA: MenuItem[] = [
    {
        menuId: Number.MAX_SAFE_INTEGER, nombre: 'Developer', icono: 'code', hijos: [
            { menuId: Number.MAX_SAFE_INTEGER - 1, nombre: 'Autocomplete', icono: 'credit_card', ruta: '/main/developer/autocomplete', padreMenuId: Number.MAX_SAFE_INTEGER },
            { menuId: Number.MAX_SAFE_INTEGER - 2, nombre: 'Buttons', icono: 'gamepad_circle_down', ruta: '/main/developer/buttons', padreMenuId: Number.MAX_SAFE_INTEGER },
            { menuId: Number.MAX_SAFE_INTEGER - 3, nombre: 'Dialogs', icono: 'dialog', ruta: '/main/developer/dialogs', padreMenuId: Number.MAX_SAFE_INTEGER },
        ]
    }
];

export const MENU_DATA: MenuItem[] = [
    {
        menuId: 1,
        nombre: 'Home', icono: 'home', hijos: [
            { menuId: 2, nombre: 'Overview', icono: 'workspaces', ruta: '/main/home/overview' ,padreMenuId: 1},
            { menuId: 3, nombre: 'Dashboard', icono: 'dashboard', ruta: '/main/home/dashboard', padreMenuId: 1 }
        ]
    },
    {
        menuId: 4, nombre: 'Visitas', icono: 'visibility', ruta: '/main/visitas', hijos: [
            {
                menuId: 5, nombre: 'Visitas padre', icono: 'visibility_off', ruta: '/main/visitas', hijos: [
                    { menuId: 6, nombre: 'Visitas Pendientes', icono: 'pending_actions', ruta: '/main/visitas/lista-visitas', padreMenuId: 5 },
                    { menuId: 7, nombre: 'Visitas Completadas', icono: 'check_circle', ruta: '/main/visitas/completadas', padreMenuId: 5 },
                ],
                padreMenuId: 4
            },

        ]
    },
    {
        ...DEVELOPER_MENU_DATA[0]
    },
    {
        menuId: 11, nombre: 'Configuración', icono: 'settings'
    }
];