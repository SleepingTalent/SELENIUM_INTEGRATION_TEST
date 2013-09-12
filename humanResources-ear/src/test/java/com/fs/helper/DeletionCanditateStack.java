package com.fs.helper;

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
        log.info("Adding Deletion Candidate :"+candidate);
        deletionCandidates.push(candidate);
    }

    public void deleteCandidates(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        while(!deletionCandidates.empty()) {
            Object deletionCandidate = deletionCandidates.pop();
            log.info("Deleting Candidate :"+deletionCandidate);
            entityManager.remove(deletionCandidate);
        }

        transaction.commit();
    }
}
