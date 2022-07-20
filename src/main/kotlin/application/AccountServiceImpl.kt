package application

import java.time.LocalDate
import domain.Transaction
import domain.TransactionRepository
import infra.database.TransactionRepositoryInMemory
import kotlin.math.abs

class AccountServiceImpl(private val transactionRepository: TransactionRepositoryInMemory) : AccountService {

    override fun deposit(accountNumber: String, amount: Int) {
        processTransaction(accountNumber, abs(amount))
    }

    override fun withdraw(accountNumber: String, amount: Int) {
        // It should create a transaction and call the repository to store it
        processTransaction(accountNumber, -abs(amount))
    }

    private fun processTransaction(accountNumber: String, amount: Int){
        val balance = retrieveAccountBalance(accountNumber)
        val transaction = Transaction(accountNumber, amount, balance + amount, LocalDate.now())
        transactionRepository.saveTransaction(transaction)
    }

    override fun retrieveStatement(accountNumber: String): List<Transaction> =
        // It should retrieve all the transactions of the accountNumber and return them
         transactionRepository.retrieveAllTransactions(accountNumber)


    fun retrieveAccountBalance(accountNumber: String): Int{
        return transactionRepository.retrieveAccountBalance(accountNumber)
    }
}