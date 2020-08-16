package ua.nure.sivolotskiy.transaction;

public interface TransactionManager {

    <U> U execute(TransactionAction<U> transactionAction);

}
