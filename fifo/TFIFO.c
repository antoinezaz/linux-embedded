#include<stdio.h>
#include<stdlib.h>
#include "TFIFO.h"

TFIFO* TFIFO_Create(){
    printf("create tfifo \n");
    TFIFO* tfifo = malloc(sizeof(TFIFO));
    tfifo->Nombre = 0;
    tfifo->Top = NULL;
    tfifo->Push = Push;
    tfifo->Pop = Pop;
    tfifo->Length = Length;
    tfifo->View = View;
    tfifo->Clear = Clear;
    tfifo->Free = Free;
    return tfifo;
}

int Push(TFIFO* tfifo, int nb){
    Titem* item = tfifo->Top;
    Titem* tmp = item;
    while (tmp != NULL){
        item = tmp;
        tmp = tmp->next;
    }
    tmp = malloc(sizeof(Titem));
    tmp->value = nb;
    tmp->next = NULL;
    if(item == NULL){
        tfifo->Top = tmp;
    } else {
        item->next = tmp;
    }
    tfifo->Nombre++;
    return 1;
}

int Pop(TFIFO* tfifo){
    if (tfifo->Top != NULL){
        Titem* tmp = tfifo->Top->next;
        int value = tfifo->Top->value;
        free(tfifo->Top);
        tfifo->Top = tmp;
        tfifo->Nombre--;
        return value;
    } else {
        printf("error pop, no data");
        return -1;
    }
}

void Clear(TFIFO* tfifo){
    while (tfifo->Top != NULL){
        Titem* tmp = tfifo->Top->next;
        free(tfifo->Top);
        tfifo->Top = tmp;
    }
    tfifo->Nombre = 0;
}

void Free(TFIFO* tfifo){
    tfifo->Clear(tfifo);
    free(tfifo);
}

int Length (TFIFO* tfifo){
    return tfifo->Nombre;
}

void View(TFIFO* tfifo){
    int i=0;
    if (tfifo->Top != NULL){
        Titem* item = tfifo->Top;
        printf("view function \n");
        while(item != NULL){
            printf("item %d: %d \n", i, item->value);
            i++;
            item = item->next;
        }
    } else {
        printf("no data \n");
    }
}