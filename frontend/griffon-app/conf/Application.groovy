application {
    title = 'Geneticrypt Frontend'
    startupGroups = ['frontend']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "frontend"
    'frontend' {
        model      = 'frontend.FrontendModel'
        view       = 'frontend.FrontendView'
        controller = 'frontend.FrontendController'
    }

}
