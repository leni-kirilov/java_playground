package com.kirilov.v15;

public abstract sealed class SealedParent
        permits FinalChild, NonSealedChild, SealedChild {

    String field;
}
