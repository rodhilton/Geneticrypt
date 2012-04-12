package frontend

application(title: 'frontend',
  preferredSize: [800, 600],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    // add content here
//
//     gridLayout(cols: 2, rows: 3)
//        label 'Cipher Text:'
//    textArea columns: 20, text: bind('cipherText', target: model)
//    label 'Username:'
//    textField columns: 20, text: bind('username', target: model)
//    label ''
//    button "Crack!"


    panel() {
        label(id: "outputLabel",
                text: "Cipher Text:")
        gridLayout(cols:1, rows: 2)

        textArea(id: "nameInput",
                    columns: 10)
                    //actionPerformed: {controller.textEntered()})

//        hbox(constraints:NORTH) {
//            textArea(id: "nameInput",
//                    columns: 10)
//                    //actionPerformed: {controller.textEntered()})
//        }
    }
}
