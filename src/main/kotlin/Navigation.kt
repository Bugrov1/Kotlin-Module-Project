import java.util.*

class Navigation( ) {

    fun screenItems( status: String, archiveslist:MutableMap<Int,Archive>,  keyArchive: Int,keyNotes: Int,):Int {
        var itemReturn :Int = -1

        if (status =="Archive"){
            var lastItem:Int =archiveslist.size+1
            println("Экран выбора Архивов.Выберите архив для просмотра или создайте новый")
            do{
                (0..lastItem).forEach(){
                    when(it) {
                        0 -> println("0 -- Создать Архив")
                        lastItem -> println("$lastItem -- Выход из программы")
                        else -> println("$it -- ${archiveslist[it]?.name}")
                    }
                }
                 var itemSelected = checkInput(lastItem)
                itemReturn = itemSelected
            } while(itemSelected !in (0..lastItem))
            //дописать выбор экрана
            //itemAction(itemSelected,lastItem)
        }
        else if (status =="Notes"){
            var lastItem: Int? = (archiveslist[keyArchive]?.notesList?.size?.plus(1))
            println("Экран выбора заметок для архива ${archiveslist[keyArchive]?.name}.Выберите заметку для просмотра или создайте новую")
            println("")
            do{
            (0..lastItem!!).forEach(){
                when(it){
                    0 -> println("0 -- Создать Заметку")
                    lastItem -> println("$lastItem -- Возврат в предыдущее меню")

                    else -> println("$it -- ${archiveslist[keyArchive]?.notesList?.get(it)?.notesMap?.keys?.toList()
                        ?.get(0)}")}
            }
                var itemSelected = checkInput(lastItem)
                itemReturn = itemSelected
            }
            while (itemSelected !in (0..lastItem!!))
            //itemAction(itemSelected,lastItem)
        }
        else if (status =="NotesText"){
            var lastItem = 1
            do{
            println("Вы просматриваете заметку ${archiveslist[keyArchive]?.notesList?.get(keyNotes)?.notesMap?.keys?.toList()
                ?.get(0)}:")
            println("${archiveslist[keyArchive]?.notesList?.get(keyNotes)?.notesMap?.values?.toList()?.get(0)}")
            println("")
            println("1 -- Возврат в предыдущее меню")
            var itemSelected = checkInput(lastItem)
            itemReturn = itemSelected
            }while (itemSelected!=1)
            //itemAction(itemSelected,lastItem)
        }
        return itemReturn
    }
    fun checkInput(lastItem:Int):Int{
        val input = Scanner(System.`in`)
        if (input.hasNextInt()) {
            val itemSelected = (input.nextInt())
            if (itemSelected in 0.. lastItem) {
                return itemSelected
            } else {
                println("Такого пункта нет в списке , выберите из доступных в списке")
                return -1
            }
        } else {
            println("Необходимо ввести число!!!")
            return -1
        }

    }
}



