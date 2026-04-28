export const MENU_DATA: MenuItem[] = [
    {
        label: 'Home', icon: 'home', children: [
            { label: 'Overview', icon: 'workspaces', route: '/main/home/overview' },
            { label: 'Dashboard', icon: 'dashboard', route: '/main/home/dashboard' }]
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
        label: 'Configuración', icon: 'settings'
    }
];