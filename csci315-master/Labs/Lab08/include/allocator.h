struct dlist *free_list;
struct dlist *allocated_list;
int allocator_init(size_t size);
void *first_allocate(size_t size);
void *worst_allocate(size_t size);
void *best_allocate(size_t size);
int deallocate(void *ptr);
void *allocate(int type, size_t size);
double average_frag();
