package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.BasicEvaluationDataMapper;
import ch.hearc.ig.guideresto.persistence.CompleteEvaluationDataMapper;

import java.time.LocalDate;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */
public class EvaluationServiceImpl implements EvaluationService {

    private BasicEvaluationDataMapper basicEvaluationDataMapper;
    private CompleteEvaluationDataMapper completeEvaluationDataMapper;
    @Override
    public void addBasicEvaluation(Restaurant restaurant, boolean like, String ipAddress) {
        basicEvaluationDataMapper.insert(new BasicEvaluation(null, LocalDate.now(), restaurant, like, ipAddress));
    }

    @Override
    public void addCompleteEvaluation(CompleteEvaluation evaluation) {
        completeEvaluationDataMapper.insert(evaluation);
    }
}
