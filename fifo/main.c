#include<stdio.h>
#include<stdlib.h>
#include "TFIFO.h"

int main() {
    TFIFO* MaFifo = TFIFO_Create();
    MaFifo->Push(MaFifo, 10);
    MaFifo->Push(MaFifo, 25);
    MaFifo->Push(MaFifo, 41);
    MaFifo->View(MaFifo);
    MaFifo->Pop(MaFifo);
    MaFifo->View(MaFifo);
    MaFifo->Push(MaFifo, 15);
    MaFifo->View(MaFifo);
    MaFifo->Free(MaFifo);
    return 0;
}
