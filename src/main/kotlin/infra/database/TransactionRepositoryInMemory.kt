package infra.database

import domain.Transaction
import domain.TransactionRepository

class TransactionRepositoryInMemory : TransactionRepository {

    private val database: MutableMap<String, MutableList<Transaction>> = mutableMapOf()

    override fun saveTransaction(transaction: Transaction) {
        // store the transaction the correct account number database
        val transactions = database.getOrDefault(
            key = transaction.accountNumber,
            defaultValue = mutableListOf()
        )
        transactions.add(transaction)
        database[transaction.accountNumber] = transactions


//        if (database[transaction.accountNumber].isNullOrEmpty()) {
//            database[transaction.accountNumber] = mutableListOf(transaction)
//        } else {
//            database[transaction.accountNumber]?.add(transaction)
//        }
    }

    override fun retrieveAllTransactions(accountNumber: String): List<Transaction> {
        // retrieve all the transaction of the given account number
        return database.getOrDefault(accountNumber, emptyList())
//        var returned = listOf<Transaction>()
//        if (!database[accountNumber].isNullOrEmpty()) {
//            return database[accountNumber]!!.toList()
//        }
//        return returned
    }

    fun retrieveAccountBalance(accountNumber: String): Int {
        if (!database[accountNumber].isNullOrEmpty()) {
            return database[accountNumber]!!.last().balance
        }
        return 0
    }
}