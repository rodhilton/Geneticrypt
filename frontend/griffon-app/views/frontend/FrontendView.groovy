package frontend

application(title: 'Geneticrypt',
  preferredSize: [800, 600],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/geneticrypt-icon-64x64.png').image,
  iconImages: [imageIcon('/geneticrypt-icon-48x48.png').image,
               imageIcon('/geneticrypt-icon-32x32.png').image,
               imageIcon('/geneticrypt-icon-16x16.png').image]) {

    panel(border:emptyBorder(6)) {
        borderLayout()

        label(constraints: NORTH, text:"Cipher Text:")

        scrollPane(constraints:CENTER) {
            textArea(id:"cipherText", text:bind(target:model, targetProperty:'cipherText'),
                    editable: bind {!model.running},
                    columns:40, rows:10, lineWrap: true, font:new Font(Font.MONOSPACED, Font.PLAIN, 12))
        }

        hbox(constraints:SOUTH) {
            button(text:bind{model.availableAction}, actionPerformed:controller.&action)
            hstrut(5)
            label("Key:")
            hstrut(5)
            textField(text:bind {model.key}, editable: false)
            hstrut(5)
            label(text: "Generation: ")
            label(text: bind{model.generation})
        }
    }


}
