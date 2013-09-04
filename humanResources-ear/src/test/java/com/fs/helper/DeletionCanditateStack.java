package com.fs.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Stack;

public class DeletionCanditateStack {

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
        deletionCandidates.push(candidate);
    }

    public void deleteCandidates(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        while(!deletionCandidates.empty()) {
            Object deletionCandidate = deletionCandidates.pop();
            entityManager.remove(deletionCandidate);
        }

        transaction.commit();
    }
}
