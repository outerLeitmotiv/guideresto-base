package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.BasicEvaluationDataMapper;
import ch.hearc.ig.guideresto.persistence.CompleteEvaluationDataMapper;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class EvaluationServiceImpl implements EvaluationService {

    private BasicEvaluationDataMapper basicEvaluationDataMapper;
    private CompleteEvaluationDataMapper completeEvaluationDataMapper;
    private TransactionService transactionService;

    public EvaluationServiceImpl() {
        this.basicEvaluationDataMapper = new BasicEvaluationDataMapper();
        this.completeEvaluationDataMapper = new CompleteEvaluationDataMapper();
        this.transactionService = new TransactionService();
    }
    @Override
    public void addBasicEvaluation(Evaluation evaluation) {
        try {
            transactionService.startTransaction();
            basicEvaluationDataMapper.insert((BasicEvaluation) evaluation);
            transactionService.commitTransaction();
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new basic evaluation", e);
        }
    }
    @Override
    public void addCompleteEvaluation(CompleteEvaluation evaluation) {
        try {
            transactionService.startTransaction();
            completeEvaluationDataMapper.insert(evaluation);
            transactionService.commitTransaction();
        } catch (SQLException e) {
            try {
                transactionService.rollbackTransaction(); // Roll back on error
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new complete evaluation", e);
        }
    }
}
