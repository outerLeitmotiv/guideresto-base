package ch.hearc.ig.guideresto.services;

/**
 * @author Olivier
 * <p>
 * GitHub link : https://github.com/outerLeitmotiv
 */

import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.business.Restaurant;

public interface EvaluationService {
    void addBasicEvaluation(Evaluation evaluation);
    void addCompleteEvaluation(CompleteEvaluation evaluation);
}
