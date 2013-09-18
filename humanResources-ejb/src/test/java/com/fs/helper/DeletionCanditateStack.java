package com.fs.helper;

import com.fs.humanResources.model.common.entities.BaseEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Stack;

public class DeletionCanditateStack {

    Logger log = Logger.getLogger(DeletionCanditateStack.class);

    private Stack deletionCandidates;

    public DeletionCanditateStack() {
        deletionCandidates = new Stack();
    }

    public int size() {
        return deletionCandidates.size();
    }

    public void clear() {
        deletionCandidates.clear();
    }

    public void push(Object candidate) {
        log.info("Adding Deletion Candidate :" + candidate);
        deletionCandidates.push(candidate);
    }

    public void deleteCandidates(EntityManager entityManager) {
        while (!deletionCandidates.empty()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Object deletionCandidate = deletionCandidates.pop();

            Long candidateId = ((BaseEntity) deletionCandidate).getId();
            log.info("Deleting Candidate Type :" +deletionCandidate.getClass()+" with Id ("+candidateId+")");

            deletionCandidate = entityManager.find(deletionCandidate.getClass(),candidateId);
            entityManager.remove(deletionCandidate);
            transaction.commit();
        }


    }
}
