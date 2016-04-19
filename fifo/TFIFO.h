typedef struct Titem
{
    int value;
    struct Titem* next;
} Titem;

typedef struct TFIFO
{
    int(*Push)(struct TFIFO*, int);
    int(*Pop)(struct TFIFO*);
    void(*Clear)(struct TFIFO*);
    void(*Free)(struct TFIFO*);
    int(*Length)(struct TFIFO*);
    void(*View)(struct TFIFO*);
    int Nombre;
    struct Titem *Top;
} TFIFO;

TFIFO* TFIFO_Create();
int Push(TFIFO* tfifo, int nb);
int Pop(TFIFO* tfifo);
void Clear(TFIFO* tfifo);
void Free(TFIFO* tfifo);
int Length (TFIFO* tfifo);
void View(TFIFO* tfifo);
