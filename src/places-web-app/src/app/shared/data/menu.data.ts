export const MENU_DATA: MenuItem[] = [
    {
        label: 'Home', icon: 'home', children: [
            { label: 'Overview', icon: 'workspaces', route: '/main/home/overview' },
            { label: 'Dashboard', icon: 'dashboard', route: '/main/home/dashboard' }
        ]
    },
    {
        label: 'Visitas', icon: 'visibility', route: '/main/visitas', children: [
            {
                label: 'Visitas padre', icon: 'visibility_off', route: '/main/visitas', children: [
                    { label: 'Visitas Pendientes', icon: 'pending_actions', route: '/main/visitas/lista-visitas' },
                    { label: 'Visitas Completadas', icon: 'check_circle', route: '/main/visitas/completadas' },
                ]
            },

        ]
    },
    {
        label: 'Developer', icon: 'code', children: [
            { label: 'Autocomplete', icon: 'credit_card', route: '/main/developer/autocomplete' },
            { label: 'Buttons', icon: 'gamepad_circle_down', route: '/main/developer/buttons' }
        ]
    },
    {
        label: 'Configuración', icon: 'settings'
    }
];