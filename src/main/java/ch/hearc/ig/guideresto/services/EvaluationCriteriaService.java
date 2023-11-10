package ch.hearc.ig.guideresto.services;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;

import java.util.List;

/**
 * @author Olivier
 * <p>
 * GitHub link : <a href="https://github.com/outerLeitmotiv">...</a>
 */
public interface EvaluationCriteriaService {
    void addEvaluationCriteria(EvaluationCriteria evaluationCriteria);
    void updateEvaluationCriteria(EvaluationCriteria evaluationCriteria);
    void deleteEvaluationCriteria(EvaluationCriteria evaluationCriteria);
    EvaluationCriteria findById(Integer id);
    List<EvaluationCriteria> findAllEvaluationCriteria();
}
