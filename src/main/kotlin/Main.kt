import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var status = "Archive"//"Archive"//NotesText//Notes
    var archivesMap:MutableMap<Int,Archive > = mutableMapOf()
    var keyArchive :Int = 0
    var keyNotes: Int = 0

    var navigation = Navigation()
    do{
        if(status  =="Archive" ){

            var itemSelect  = navigation.screenItems(status, archivesMap,0,0)
            var exitItem = archivesMap.size+1


            if(itemSelect ==exitItem){
                println("Выходим из программы ...")
                exitProcess(0)
            }
            else if(itemSelect ==0){
                var newArchive = Archive.create()
                newArchive.addToStorage(archivesMap,newArchive)
            }
            else {
                status ="Notes"
                keyArchive = itemSelect
                //navigation.screenItems(status, archivesMap,keyArchive,0)
            }
        }
        if(status  =="Notes" ){

            var itemSelect  = navigation.screenItems(status, archivesMap,keyArchive,0)
            var exitItem = (archivesMap[keyArchive]?.notesList?.size?.plus(1))


            if(itemSelect ==exitItem){
                println("Возврат в предыдущее меню")
                println()
                status ="Archive"
                //navigation.screenItems(status, archivesMap,0,0)

            }
            else if (itemSelect ==0){
                var notetoadd = Notes.create()
                var archiveToChange = archivesMap[keyArchive]
                archiveToChange?.addNoteArchive(notetoadd)
                println("NOT READY Function")

            }
            else{
                status ="NotesText"
                keyNotes = itemSelect
                //navigation.screenItems(status, archivesMap,keyArchive,keyNotes )
            }
        }
        if(status  =="NotesText" ){
            var itemSelect  = navigation.screenItems(status, archivesMap,keyArchive,keyNotes)
            var exitItem = 1
            if(itemSelect ==1){
                println("Возврат в предыдущее меню")
                println()
                status ="Notes"

                //navigation.screenItems(status, archivesMap,keyArchive,keyNotes )
            }
        }
    }while (true)
}





