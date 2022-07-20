package infra.view

import application.AccountServiceImpl
import domain.Transaction
import infra.database.TransactionRepositoryInMemory

fun main(args: Array<String>) {

    // Main class
    // It should create an accountService, do all the operations needed, and print the statement on console
    // Note that the accountService needs a transactionRepository implementation, so it should also be created here!
    val transactionRepository = TransactionRepositoryInMemory()
    val accountService = AccountServiceImpl(transactionRepository)

    val optionList:List<String> = listOf("1.- BALANCE INQUIRY", "2.- DEPOSIT", "3.- WITHDRAWAL", "4.- EXTRACT INQUIRY", "5.- EXIT")

    fun mainMenu(): String {
        println("Welcome to Kata - Bank")
        println("Please, choose your option")
        for (item:String in optionList){
            println(item)
        }

        return readLine().toString()
    }

    fun clear(){
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------------------------------")
    }

    fun isOptionValid(option:String, min:String="1", max:String="9"):Boolean
    {
        val regex = "[${min}-${max}]".toRegex()
        return option.matches(regex)
    }

    fun readUserInfo(message:String):String{
        println(message)
        return readLine().toString()
    }

    fun printStatement(list:List<Transaction>){
        if(list.isNullOrEmpty())
        {
            println("ERROR: the account does not exist")
        }
        else {
            println("account number = ${list[0].accountNumber}")
            for (item in list) {
                println(item.date.toString() + "      " + item.amount)
            }
            println("Account balance = ${list.last().balance}")
        }
    }

    fun messageContinue(){
        println("Press enter to continue")
        readLine().toString()
        clear()
    }

    var option:String = ""
    while (option != "5"){
        option = mainMenu()

        if (isOptionValid(option, "1", "5")){
            clear()

            when(option){
                "1" -> println("Your account balace is ${accountService.retrieveAccountBalance("Dani")}")
                "2" -> accountService.deposit("Dani", 150)
                "3" -> accountService.withdraw("Albert", 60)
                "4" -> printStatement(accountService.retrieveStatement("Dani"))
            }

        }
        else{
            println("INCORRECT OPTION")
        }
        println("Please, press enter to continue")
        readLine()


    }
}
