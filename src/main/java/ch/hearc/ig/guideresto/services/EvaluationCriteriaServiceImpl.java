package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.persistence.DataMapperException;
import ch.hearc.ig.guideresto.persistence.EvaluationCriteriaDataMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class EvaluationCriteriaServiceImpl implements EvaluationCriteriaService{

    private  EvaluationCriteriaDataMapper evaluationCriteriaDataMapper;
    private  TransactionService transactionService;

    public EvaluationCriteriaServiceImpl() {
        this.evaluationCriteriaDataMapper = new EvaluationCriteriaDataMapper();
        this.transactionService = new TransactionService();
    }

    @Override
    public void addEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        try {
            transactionService.startTransaction();
            evaluationCriteriaDataMapper.insert(evaluationCriteria);
            transactionService.commitTransaction();
        } catch (SQLException | DataMapperException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error adding a new evaluation criteria", e);
        }
    }

    @Override
    public void updateEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        try {
            transactionService.startTransaction();
            evaluationCriteriaDataMapper.update(evaluationCriteria);
            transactionService.commitTransaction();
        } catch (SQLException | DataMapperException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error updating evaluation criteria", e);
        }
    }

    @Override
    public void deleteEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        try {
            transactionService.startTransaction();
            evaluationCriteriaDataMapper.delete(evaluationCriteria);
            transactionService.commitTransaction();
        } catch (SQLException | DataMapperException e) {
            try {
                transactionService.rollbackTransaction();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error rolling back transaction", rollbackEx);
            }
            throw new RuntimeException("Error deleting evaluation criteria", e);
        }
    }

    @Override
    public EvaluationCriteria findById(Integer id) {
        try {
            return evaluationCriteriaDataMapper.findById(id);
        } catch (DataMapperException e) {
            throw new RuntimeException("Error finding evaluation criteria with ID: " + id, e);
        }
    }

    @Override
    public List<EvaluationCriteria> findAllEvaluationCriteria() {
        try {
            return evaluationCriteriaDataMapper.findAll();
        } catch (DataMapperException e) {
            throw new RuntimeException("Error retrieving all evaluation criteria", e);
        }
    }
}
