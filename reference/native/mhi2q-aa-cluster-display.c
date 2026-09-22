/* RECONSTRUCTED PSEUDOCODE - not original source, not build-ready.
 * Input: mhi2q-aa-cluster-display
 * Language: ARM:LE:32:v8
 * Generated with Ghidra. Types, signatures and control flow may be inaccurate.
 */


/* Function: _init @ 00101200 */

int _init(EVP_PKEY_CTX *ctx)

{
  return (int)ctx;
}



/* Function: clock_gettime @ 0010121c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int clock_gettime(clockid_t __clock_id,timespec *__tp)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_clock_gettime_00108520)(__clock_id);
  return iVar1;
}



/* Function: waitpid @ 00101228 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t waitpid(__pid_t __pid,int *__stat_loc,int __options)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_waitpid_00108524)(__pid);
  return _Var1;
}



/* Function: dlerror @ 00101234 */

void dlerror(void)

{
  (*(code *)PTR_dlerror_00108528)();
  return;
}



/* Function: printf @ 00101240 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int printf(char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_printf_0010852c)(__format);
  return iVar1;
}



/* Function: _init_array @ 0010124c */

void _init_array(void)

{
  (*(code *)PTR__init_array_00108530)();
  return;
}



/* Function: sigemptyset @ 00101258 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigemptyset(sigset_t *__set)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigemptyset_00108534)(__set);
  return iVar1;
}



/* Function: strerror @ 00101264 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strerror(int __errnum)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strerror_00108538)(__errnum);
  return pcVar1;
}



/* Function: getenv @ 00101270 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * getenv(char *__name)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_getenv_0010853c)(__name);
  return pcVar1;
}



/* Function: usleep @ 0010127c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int usleep(__useconds_t __useconds)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_usleep_00108540)(__useconds);
  return iVar1;
}



/* Function: _preinit_array @ 00101288 */

void _preinit_array(void)

{
  (*(code *)PTR__preinit_array_00108544)();
  return;
}



/* Function: memcpy @ 00101294 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memcpy(void *__dest,void *__src,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memcpy_00108548)(__dest);
  return pvVar1;
}



/* Function: execl @ 001012a0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int execl(char *__path,char *__arg,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_execl_0010854c)(__path);
  return iVar1;
}



/* Function: puts @ 001012ac */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int puts(char *__s)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_puts_00108550)(__s);
  return iVar1;
}



/* Function: dup2 @ 001012b8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int dup2(int __fd,int __fd2)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_dup2_00108554)(__fd);
  return iVar1;
}



/* Function: malloc @ 001012c4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * malloc(size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_malloc_00108558)(__size);
  return pvVar1;
}



/* Function: sleep @ 001012d0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

uint sleep(uint __seconds)

{
  uint uVar1;
  
  uVar1 = (*(code *)PTR_sleep_0010855c)(__seconds);
  return uVar1;
}



/* Function: spawnl @ 001012dc */

void spawnl(void)

{
  (*(code *)PTR_spawnl_00108560)();
  return;
}



/* Function: fflush @ 001012e8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fflush(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fflush_00108564)(__stream);
  return iVar1;
}



/* Function: dlclose @ 001012f4 */

void dlclose(void)

{
  (*(code *)PTR_dlclose_00108568)();
  return;
}



/* Function: pipe @ 00101300 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pipe(int *__pipedes)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pipe_0010856c)(__pipedes);
  return iVar1;
}



/* Function: nanosleep @ 0010130c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int nanosleep(timespec *__requested_time,timespec *__remaining)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_nanosleep_00108570)(__requested_time);
  return iVar1;
}



/* Function: calloc @ 00101318 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * calloc(size_t __nmemb,size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_calloc_00108574)(__nmemb);
  return pvVar1;
}



/* Function: write @ 00101324 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t write(int __fd,void *__buf,size_t __n)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_write_00108578)(__fd);
  return sVar1;
}



/* Function: fprintf @ 00101330 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fprintf(FILE *__stream,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fprintf_0010857c)(__stream);
  return iVar1;
}



/* Function: kill @ 0010133c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int kill(__pid_t __pid,int __sig)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_kill_00108580)(__pid);
  return iVar1;
}



/* Function: __deregister_frame_info @ 00101348 */

void __deregister_frame_info(void)

{
  (*(code *)PTR___deregister_frame_info_00108584)();
  return;
}



/* Function: fseek @ 00101354 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fseek(FILE *__stream,long __off,int __whence)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fseek_00108588)(__stream);
  return iVar1;
}



/* Function: memchr @ 00101360 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memchr(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memchr_0010858c)(__s);
  return pvVar1;
}



/* Function: __get_errno_ptr @ 0010136c */

void __get_errno_ptr(void)

{
  (*(code *)PTR___get_errno_ptr_00108590)();
  return;
}



/* Function: strstr @ 00101378 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strstr(char *__haystack,char *__needle)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strstr_00108594)(__haystack);
  return pcVar1;
}



/* Function: signal @ 00101384 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__sighandler_t signal(int __sig,__sighandler_t __handler)

{
  __sighandler_t p_Var1;
  
  p_Var1 = (__sighandler_t)(*(code *)PTR_signal_00108598)(__sig);
  return p_Var1;
}



/* Function: read @ 00101390 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t read(int __fd,void *__buf,size_t __nbytes)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_read_0010859c)(__fd);
  return sVar1;
}



/* Function: dlopen @ 0010139c */

void dlopen(void)

{
  (*(code *)PTR_dlopen_001085a0)();
  return;
}



/* Function: unlink @ 001013a8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int unlink(char *__name)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_unlink_001085a4)(__name);
  return iVar1;
}



/* Function: setenv @ 001013b4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int setenv(char *__name,char *__value,int __replace)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_setenv_001085a8)(__name);
  return iVar1;
}



/* Function: memcmp @ 001013c0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int memcmp(void *__s1,void *__s2,size_t __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_memcmp_001085ac)(__s1);
  return iVar1;
}



/* Function: fork @ 001013cc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t fork(void)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_fork_001085b0)();
  return _Var1;
}



/* Function: sscanf @ 001013d8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sscanf(char *__s,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sscanf_001085b4)(__s);
  return iVar1;
}



/* Function: sigaction @ 001013e4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigaction(int __sig,sigaction *__act,sigaction *__oact)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigaction_001085b8)(__sig);
  return iVar1;
}



/* Function: fread @ 001013f0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t fread(void *__ptr,size_t __size,size_t __n,FILE *__stream)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_fread_001085bc)(__ptr);
  return sVar1;
}



/* Function: dlsym @ 001013fc */

void dlsym(void)

{
  (*(code *)PTR_dlsym_001085c0)();
  return;
}



/* Function: _init_libc @ 00101408 */

void _init_libc(void)

{
  (*(code *)PTR__init_libc_001085c4)();
  return;
}



/* Function: fopen @ 00101414 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

FILE * fopen(char *__filename,char *__modes)

{
  FILE *pFVar1;
  
  pFVar1 = (FILE *)(*(code *)PTR_fopen_001085c8)(__filename);
  return pFVar1;
}



/* Function: memset @ 00101420 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memset(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memset_001085cc)(__s);
  return pvVar1;
}



/* Function: ftell @ 0010142c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

long ftell(FILE *__stream)

{
  long lVar1;
  
  lVar1 = (*(code *)PTR_ftell_001085d0)(__stream);
  return lVar1;
}



/* Function: fclose @ 00101438 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fclose(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fclose_001085d4)(__stream);
  return iVar1;
}



/* Function: connect @ 00101444 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int connect(int __fd,sockaddr *__addr,socklen_t __len)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_connect_001085d8)(__fd);
  return iVar1;
}



/* Function: poll @ 00101450 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int poll(pollfd *__fds,nfds_t __nfds,int __timeout)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_poll_001085dc)(__fds);
  return iVar1;
}



/* Function: _fini_array @ 0010145c */

void _fini_array(void)

{
  (*(code *)PTR__fini_array_001085e0)();
  return;
}



/* Function: strcmp @ 00101468 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int strcmp(char *__s1,char *__s2)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_strcmp_001085e4)(__s1);
  return iVar1;
}



/* Function: pthread_mutex_unlock @ 00101474 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_unlock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_unlock_001085e8)(__mutex);
  return iVar1;
}



/* Function: atexit @ 00101480 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int atexit(__func *__func)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_atexit_001085ec)(__func);
  return iVar1;
}



/* Function: socket @ 0010148c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int socket(int __domain,int __type,int __protocol)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_socket_001085f0)(__domain);
  return iVar1;
}



/* Function: access @ 00101498 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int access(char *__name,int __type)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_access_001085f4)(__name);
  return iVar1;
}



/* Function: freopen @ 001014a4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

FILE * freopen(char *__filename,char *__modes,FILE *__stream)

{
  FILE *pFVar1;
  
  pFVar1 = (FILE *)(*(code *)PTR_freopen_001085f8)(__filename);
  return pFVar1;
}



/* Function: pthread_mutex_lock @ 001014b0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_lock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_lock_001085fc)(__mutex);
  return iVar1;
}



/* Function: exit @ 001014bc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void exit(int __status)

{
  (*(code *)PTR_exit_00108600)(__status);
  return;
}



/* Function: atoi @ 001014c8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int atoi(char *__nptr)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_atoi_00108604)(__nptr);
  return iVar1;
}



/* Function: _exit @ 001014d4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void _exit(int __status)

{
  (*(code *)PTR__exit_00108608)(__status);
  return;
}



/* Function: open @ 001014e0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int open(char *__file,int __oflag,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_open_0010860c)(__file);
  return iVar1;
}



/* Function: fcntl @ 001014ec */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fcntl(int __fd,int __cmd,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fcntl_00108610)(__fd);
  return iVar1;
}



/* Function: __register_frame_info @ 001014f8 */

void __register_frame_info(void)

{
  (*(code *)PTR___register_frame_info_00108614)();
  return;
}



/* Function: close @ 00101504 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int close(int __fd)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_close_00108618)(__fd);
  return iVar1;
}



/* Function: free @ 00101510 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void free(void *__ptr)

{
  (*(code *)PTR_free_0010861c)(__ptr);
  return;
}



/* Function: _start @ 0010151c */

void processEntry _start(undefined4 param_1,int param_2)

{
  int *piVar1;
  int iVar2;
  
  piVar1 = (int *)(&stack0x00000008 + param_2 * 4);
  do {
    iVar2 = *piVar1;
    piVar1 = piVar1 + 1;
  } while (iVar2 != 0);
  (*DAT_001015ec)();
  (*DAT_001015f0)(DAT_0010160c,DAT_00101610);
  (*DAT_001015f4)(DAT_00101614);
  (*DAT_001015f8)(DAT_00101618,DAT_0010161c);
  (*DAT_001015fc)();
  (*DAT_00101600)(DAT_00101620,DAT_00101624);
  *DAT_00101628 = 0;
  (*DAT_00101604)(param_2,&stack0x00000004,&stack0x00000008 + param_2 * 4);
  (*DAT_00101608)();
  return;
}



/* Function: FUN_0010162c @ 0010162c */

/* WARNING: Removing unreachable block (ram,0x00101654) */

void FUN_0010162c(void)

{
  if (6 < DAT_00101658 - 0x10868cU) {
    return;
  }
  return;
}



/* Function: _FINI_0 @ 00101694 */

/* WARNING: Removing unreachable block (ram,0x001016c0) */

void _FINI_0(void)

{
  if (DAT_00108734 == '\0') {
    FUN_0010162c();
    DAT_00108734 = 1;
    return;
  }
  return;
}



/* Function: _INIT_0 @ 001016d8 */

/* WARNING: Removing unreachable block (ram,0x00101680) */
/* WARNING: Removing unreachable block (ram,0x0010168c) */
/* WARNING: Removing unreachable block (ram,0x001016ec) */
/* WARNING: Removing unreachable block (ram,0x0010172c) */
/* WARNING: Removing unreachable block (ram,0x00101690) */

void _INIT_0(void)

{
  return;
}



/* Function: FUN_00101748 @ 00101748 */

undefined4 FUN_00101748(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  pthread_mutex_lock((pthread_mutex_t *)&__data_start);
  DAT_00108760 = param_3;
  pthread_mutex_unlock((pthread_mutex_t *)&__data_start);
  return 0;
}



/* Function: FUN_0010177c @ 0010177c */

undefined4
FUN_0010177c(undefined4 param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,
            undefined4 param_5)

{
  printf("event type=%u data1=0x%08x data2=0x%08x\n",param_3,param_4,param_5,param_4);
  fflush((FILE *)_Stdout);
  return 0;
}



/* Function: FUN_001017ac @ 001017ac */

void * FUN_001017ac(char *param_1,size_t *param_2,undefined4 param_3,undefined4 param_4)

{
  FILE *__stream;
  int iVar1;
  size_t __size;
  void *__ptr;
  size_t sVar2;
  int *piVar3;
  char *pcVar4;
  
  *param_2 = 0;
  __stream = fopen(param_1,"rb");
  if (__stream == (FILE *)0x0) {
    piVar3 = (int *)__get_errno_ptr();
    pcVar4 = strerror(*piVar3);
    fprintf((FILE *)_Stderr,"open %s failed: %s\n",param_1,pcVar4,param_4);
    return (void *)0x0;
  }
  iVar1 = fseek(__stream,0,2);
  if (((iVar1 == 0) && (__size = ftell(__stream), 0 < (int)__size)) &&
     (iVar1 = fseek(__stream,0,0), iVar1 == 0)) {
    __ptr = malloc(__size);
    if ((__ptr != (void *)0x0) && (sVar2 = fread(__ptr,1,__size,__stream), __size == sVar2)) {
      fclose(__stream);
      *param_2 = __size;
      return __ptr;
    }
    free(__ptr);
  }
  fclose(__stream);
  return (void *)0x0;
}



/* Function: FUN_00101894 @ 00101894 */

bool FUN_00101894(int param_1,void *param_2,size_t param_3)

{
  bool bVar1;
  size_t *__ptr;
  int iVar2;
  code *pcVar3;
  code *pcVar4;
  undefined4 local_24;
  
  pcVar3 = *(code **)(param_1 + 0x28);
  pcVar4 = *(code **)(param_1 + 0x1c);
  local_24 = 0;
  __ptr = calloc(1,param_3 + 0x14);
  if (__ptr == (size_t *)0x0) {
    bVar1 = false;
  }
  else {
    *__ptr = param_3 + 0x14;
    __ptr[2] = 0x65646e65;
    __ptr[3] = param_3;
    __ptr[1] = 0x101;
    memcpy(__ptr + 4,param_2,param_3);
    iVar2 = (*pcVar3)(param_1,"OMX.QCOM.index.param.video.SyntaxHdr",&local_24);
    printf("SyntaxHdr index result=0x%08x index=0x%08x\n",iVar2,local_24);
    if (iVar2 == 0) {
      iVar2 = (*pcVar4)(param_1,local_24,__ptr);
    }
    printf("SyntaxHdr set length=%u result=0x%08x\n",param_3,iVar2);
    free(__ptr);
    bVar1 = iVar2 == 0;
  }
  return bVar1;
}



/* Function: FUN_00101974 @ 00101974 */

undefined4 FUN_00101974(int param_1,int param_2,uint param_3)

{
  int iVar1;
  uint uVar2;
  code *pcVar3;
  int local_24;
  
  pcVar3 = *(code **)(param_1 + 0x2c);
  uVar2 = 0;
  local_24 = -1;
  while( true ) {
    iVar1 = (*pcVar3)(param_1,&local_24);
    if (iVar1 != 0) {
      return 0;
    }
    if (local_24 == param_2) break;
    uVar2 = uVar2 + 10;
    usleep(10000);
    if (param_3 < uVar2) {
      printf("state timeout wanted=%u current=%u\n",param_2,local_24);
      return 0;
    }
  }
  printf("state=%u reached after %u ms\n",param_2,uVar2);
  return 1;
}



/* Function: FUN_00101a1c @ 00101a1c */

undefined4 FUN_00101a1c(undefined4 param_1,int param_2,uint *param_3,int *param_4)

{
  uint uVar1;
  byte *pbVar2;
  byte *pbVar3;
  uint uVar4;
  uint uVar5;
  int iVar6;
  uint uVar7;
  int iVar8;
  uint uVar9;
  byte *pbVar10;
  int iVar11;
  uint uVar12;
  uint local_40;
  
  if (DAT_00108640 == 0x53) {
    iVar6 = 0x4e;
    iVar8 = 0x68;
    iVar11 = 0x3c6;
  }
  else if (DAT_00108640 == 0x43) {
    iVar8 = 0x1fe;
    iVar6 = 0x4d;
    iVar11 = iVar8;
  }
  else {
    iVar8 = 0x15e;
    iVar6 = 0x4d;
    iVar11 = iVar8;
  }
  uVar9 = iVar8 + DAT_00108628;
  uVar12 = (DAT_00108628 + DAT_00108630) - iVar11;
  uVar4 = iVar6 + DAT_0010862c;
  local_40 = (DAT_0010862c + DAT_00108634) - 0x92;
  if (local_40 <= uVar4 || uVar12 <= uVar9) {
    return 0;
  }
  uVar7 = 0;
  uVar12 = uVar12 - uVar9;
  pbVar10 = (byte *)(param_2 + 0x10);
  local_40 = local_40 - uVar4;
  *param_4 = 0;
  iVar11 = local_40 * 2;
  do {
    pbVar2 = pbVar10 + -0x10;
    uVar5 = uVar12;
    do {
      uVar1 = uVar5 >> 5;
      uVar5 = uVar5 + uVar12 * 2;
      iVar6 = qcom_tiled_read_luma
                        (param_1,DAT_00108884,DAT_00108888,uVar9 + uVar1,uVar4 + (local_40 >> 4),
                         pbVar2);
      if (iVar6 != 0) {
        return 0;
      }
      pbVar3 = pbVar2 + 1;
      uVar7 = uVar7 + *pbVar2;
      if (0x2f < *pbVar2) {
        *param_4 = *param_4 + 1;
      }
      pbVar2 = pbVar3;
    } while (pbVar10 != pbVar3);
    pbVar10 = pbVar10 + 0x10;
    local_40 = local_40 + iVar11;
  } while (pbVar10 != (byte *)(param_2 + 0x90));
  *param_3 = uVar7 >> 7;
  return 1;
}



/* Function: FUN_00101ba0 @ 00101ba0 */

void FUN_00101ba0(void)

{
  undefined4 local_60;
  undefined4 uStack_5c;
  undefined4 local_58;
  undefined4 uStack_54;
  undefined4 local_50;
  undefined4 uStack_4c;
  undefined4 local_48;
  undefined4 uStack_44;
  undefined4 local_40;
  undefined4 uStack_3c;
  undefined4 local_38;
  undefined4 uStack_34;
  undefined4 local_30;
  undefined4 uStack_2c;
  
  local_60 = DAT_00106854;
  uStack_5c = DAT_00106858;
  local_58 = DAT_00106854;
  uStack_54 = DAT_00106858;
  local_50 = DAT_00106854;
  uStack_4c = DAT_00106858;
  local_48 = DAT_00106854;
  uStack_44 = DAT_00106858;
  local_40 = DAT_00106854;
  uStack_3c = DAT_00106858;
  local_38 = DAT_00106854;
  uStack_34 = DAT_00106858;
  local_30 = DAT_00106854;
  uStack_2c = DAT_00106858;
  if ((DAT_001087ec != standalone_decoder_main) && (DAT_001087d0 != 0)) {
    (*DAT_001087ec)(DAT_001087d0,5,&local_60);
    (*DAT_001087ec)(DAT_001087d0,0x23,&local_38);
    (*DAT_001087ec)(DAT_001087d0,0x29,&local_58);
    (*DAT_001087ec)(DAT_001087d0,0x2a,&local_50);
    (*DAT_001087ec)(DAT_001087d0,0x44,&local_48);
    (*DAT_001087ec)(DAT_001087d0,0x48,&local_40);
    (*DAT_001087ec)(DAT_001087d0,0x28,&local_30);
  }
  return;
}



/* Function: FUN_00101c9c @ 00101c9c */

undefined4 FUN_00101c9c(void)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  int iVar6;
  undefined4 uVar7;
  
  if (DAT_00108774 == standalone_decoder_main) {
    return 0;
  }
  if (DAT_001087d0 == 0) {
    return 0;
  }
  iVar1 = (*DAT_00108774)(DAT_001087d0,0x23,&DAT_00108764);
  iVar2 = (*DAT_00108774)(DAT_001087d0,0x29,&DAT_00108784);
  iVar3 = (*DAT_00108774)(DAT_001087d0,0x2a,&DAT_0010876c);
  iVar4 = (*DAT_00108774)(DAT_001087d0,0x44,&DAT_0010888c);
  iVar5 = (*DAT_00108774)(DAT_001087d0,0x48,&DAT_00108878);
  iVar6 = (*DAT_00108774)(DAT_001087d0,0x28,&DAT_00108638);
  uVar7 = FUN_00101ba0();
  if (((((iVar2 == 0 && iVar1 == 0) && iVar3 == 0) && iVar4 == 0) && iVar5 == 0) && iVar6 == 0) {
    uVar7 = 1;
  }
  if (((((iVar2 != 0 || iVar1 != 0) || iVar3 != 0) || iVar4 != 0) || iVar5 != 0) || iVar6 != 0) {
    uVar7 = 0;
  }
  return uVar7;
}



/* Function: FUN_00101d94 @ 00101d94 */

int FUN_00101d94(undefined4 param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  undefined4 uVar2;
  
  iVar1 = dlsym();
  if (iVar1 != 0) {
    return iVar1;
  }
  uVar2 = dlerror();
  fprintf((FILE *)_Stderr,"missing %s: %s\n",param_2,uVar2,param_4);
  return 0;
}



/* Function: FUN_00101dd8 @ 00101dd8 */

undefined4 FUN_00101dd8(int param_1)

{
  bool bVar1;
  int iVar2;
  
  iVar2 = 0x1f5;
  while( true ) {
    pthread_mutex_lock((pthread_mutex_t *)&__data_start);
    bVar1 = DAT_00108760 == param_1;
    if (bVar1) {
      DAT_00108760 = 0;
    }
    pthread_mutex_unlock((pthread_mutex_t *)&__data_start);
    if (bVar1) break;
    usleep(10000);
    iVar2 = iVar2 + -1;
    if (iVar2 == 0) {
      return 0;
    }
  }
  return 1;
}



/* Function: FUN_00101e48 @ 00101e48 */

undefined4
FUN_00101e48(int param_1,int param_2,undefined4 param_3,int *param_4,int param_5,uint param_6,
            undefined4 param_7,undefined4 param_8,int param_9)

{
  int iVar1;
  void *__src;
  int extraout_r1;
  code *pcVar2;
  uint uVar3;
  int iVar4;
  uint uVar5;
  
  pcVar2 = *(code **)(param_1 + 0x40);
  uVar3 = 0;
  while( true ) {
    uVar5 = param_6 - uVar3;
    __src = (void *)(param_5 + uVar3);
    if (param_6 <= uVar3) {
      return 1;
    }
    iVar4 = *(int *)(param_2 + *param_4 * 4);
    if (*(uint *)(iVar4 + 0xc) <= uVar5) {
      uVar5 = *(uint *)(iVar4 + 0xc);
    }
    uVar3 = uVar3 + uVar5;
    memcpy(*(void **)(iVar4 + 8),__src,uVar5);
    *(uint *)(iVar4 + 0x10) = uVar5;
    *(undefined4 *)(iVar4 + 0x38) = param_7;
    *(undefined4 *)(iVar4 + 0x3c) = param_8;
    if (uVar3 == param_6) {
      uVar5 = 0x10;
    }
    else {
      uVar5 = 0;
    }
    *(undefined4 *)(iVar4 + 0x14) = 0;
    if (param_9 == 0) {
      *(uint *)(iVar4 + 0x40) = uVar5;
    }
    else {
      *(uint *)(iVar4 + 0x40) = uVar5 | 0x80;
    }
    pthread_mutex_lock((pthread_mutex_t *)&__data_start);
    DAT_00108760 = 0;
    pthread_mutex_unlock((pthread_mutex_t *)&__data_start);
    iVar1 = (*pcVar2)(param_1,iVar4);
    if ((iVar1 != 0) || (iVar4 = FUN_00101dd8(iVar4), iVar4 == 0)) break;
    FUN_001064d0(*param_4 + 1,param_3);
    *param_4 = extraout_r1;
  }
  return 0;
}



/* Function: FUN_00101f5c @ 00101f5c */

bool FUN_00101f5c(int param_1,int param_2,undefined4 param_3,int param_4,uint param_5)

{
  int iVar1;
  void *__src;
  int extraout_r1;
  code *pcVar2;
  char *pcVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  bool bVar9;
  uint local_2c;
  
  iVar7 = 0;
  local_2c = 0;
  pcVar2 = *(code **)(param_1 + 0x40);
  uVar6 = 0x1d;
  do {
    bVar9 = 0x76 < local_2c;
    if (local_2c < 0x78) {
      bVar9 = param_5 <= uVar6;
    }
    if (bVar9) {
      printf("submitted NAL units=%u\n",local_2c);
      return local_2c != 0;
    }
    uVar4 = uVar6 + 4;
    pcVar3 = (char *)(param_4 + uVar4);
    while ((uVar5 = param_5, uVar4 + 3 < param_5 &&
           (((*pcVar3 != '\0' || (pcVar3[1] != '\0')) ||
            ((uVar5 = uVar4, pcVar3[2] != '\x01' && ((pcVar3[2] != '\0' || (pcVar3[3] != '\x01')))))
            )))) {
      uVar4 = uVar4 + 1;
      pcVar3 = pcVar3 + 1;
    }
    while( true ) {
      uVar4 = uVar5 - uVar6;
      __src = (void *)(param_4 + uVar6);
      if (uVar5 <= uVar6) break;
      iVar8 = *(int *)(param_2 + iVar7 * 4);
      if (*(uint *)(iVar8 + 0xc) <= uVar4) {
        uVar4 = *(uint *)(iVar8 + 0xc);
      }
      uVar6 = uVar6 + uVar4;
      memcpy(*(void **)(iVar8 + 8),__src,uVar4);
      *(uint *)(iVar8 + 0x10) = uVar4;
      *(undefined4 *)(iVar8 + 0x14) = 0;
      if (uVar6 == uVar5) {
        uVar4 = 0x10;
      }
      else {
        uVar4 = 0;
      }
      *(ulonglong *)(iVar8 + 0x38) = (ulonglong)local_2c * 0x8235;
      if (uVar5 == uVar6 && uVar5 == param_5) {
        uVar4 = uVar4 | 1;
      }
      *(uint *)(iVar8 + 0x40) = uVar4;
      pthread_mutex_lock((pthread_mutex_t *)&__data_start);
      DAT_00108760 = 0;
      pthread_mutex_unlock((pthread_mutex_t *)&__data_start);
      iVar1 = (*pcVar2)(param_1,iVar8);
      if (iVar1 != 0) {
        printf("EmptyThisBuffer nal=%u result=0x%08x\n",local_2c);
        return false;
      }
      iVar8 = FUN_00101dd8(iVar8);
      if (iVar8 == 0) {
        printf("input return timeout at NAL %u\n",local_2c);
        return false;
      }
      FUN_001064d0(iVar7 + 1,param_3);
      iVar7 = extraout_r1;
    }
    local_2c = local_2c + 1;
    uVar6 = uVar5;
  } while( true );
}



/* Function: FUN_00102150 @ 00102150 */

void FUN_00102150(int param_1,int param_2)

{
  longlong lVar1;
  int iVar2;
  ssize_t sVar3;
  uint uVar4;
  bool bVar5;
  undefined1 local_21;
  pollfd local_20;
  
  local_21 = 0x4b;
  if (((param_2 != 0) && (*(int *)(param_2 + 8) != 0)) &&
     (iVar2 = clock_gettime(2,(timespec *)&local_20), iVar2 == 0)) {
    lVar1 = FUN_001064f4(local_20._4_4_,(int)local_20._4_4_ >> 0x1f,1000,0);
    lVar1 = (ulonglong)(uint)local_20.fd * 1000000 + lVar1;
    uVar4 = (uint)((ulonglong)lVar1 >> 0x20);
    if (lVar1 != 0) {
      bVar5 = uVar4 <= *(uint *)(param_2 + 0x1c);
      if (*(uint *)(param_2 + 0x1c) == uVar4) {
        bVar5 = (uint)lVar1 <= *(uint *)(param_2 + 0x18);
      }
      if ((!bVar5 || lVar1 == *(longlong *)(param_2 + 0x18)) &&
         (iVar2 = p5092_vc_route_projection_requested(), iVar2 != 0)) {
        local_20.revents = 0;
        local_20.events = 7;
        local_20.fd = param_1;
        iVar2 = poll(&local_20,1,0);
        if ((0 < iVar2) && ((local_20.revents & 2U) != 0)) {
          if ((*(int *)(param_2 + 0x18) == 0 && *(int *)(param_2 + 0x1c) == 0) &&
             ((local_20.revents & 5U) != 0)) {
            *(longlong *)(param_2 + 0x18) = lVar1 + 100000;
          }
          else {
            *(longlong *)(param_2 + 0x18) = lVar1 + 100000;
            sVar3 = write(param_1,&local_21,1);
            if (sVar3 == 1) {
              uVar4 = *(uint *)(param_2 + 0x20);
              *(undefined4 *)(param_2 + 0xc) = 1;
              if (uVar4 == 0) {
                uVar4 = 1000000;
                *(undefined4 *)(param_2 + 0x20) = 1000000;
              }
              else if (uVar4 < 4000000) {
                uVar4 = uVar4 << 1;
                *(uint *)(param_2 + 0x20) = uVar4;
              }
              *(ulonglong *)(param_2 + 0x18) = lVar1 + (ulonglong)uVar4;
            }
          }
        }
      }
    }
  }
  return;
}



/* Function: FUN_001022b4 @ 001022b4 */

undefined4 FUN_001022b4(int param_1,int param_2,uint param_3,undefined4 param_4)

{
  int iVar1;
  ssize_t sVar2;
  undefined4 *puVar3;
  int *piVar4;
  uint uVar5;
  pollfd local_30;
  
  if (param_3 == 0) {
    return 1;
  }
  uVar5 = 0;
  do {
    local_30.events = 5;
    local_30.fd = param_1;
    do {
      do {
        while( true ) {
          p5092_vc_route_poll(DAT_0010877c);
          FUN_00102150(param_1,param_4);
          local_30.revents = 0;
          iVar1 = poll(&local_30,1,0x32);
          if (-1 < iVar1) break;
          piVar4 = (int *)__get_errno_ptr();
          if (*piVar4 != 4) {
            return 0;
          }
        }
      } while (iVar1 == 0);
      if ((local_30.revents & 0x1000U) != 0) {
        puVar3 = (undefined4 *)__get_errno_ptr();
        *puVar3 = 9;
        return 0;
      }
    } while ((local_30.revents & 0x65U) == 0);
    sVar2 = read(param_1,(void *)(param_2 + uVar5),param_3 - uVar5);
    if (sVar2 < 0) {
      piVar4 = (int *)__get_errno_ptr();
      if (*piVar4 != 4) {
        return 0;
      }
    }
    else {
      if (sVar2 == 0) {
        return 0;
      }
      uVar5 = uVar5 + sVar2;
    }
  } while (uVar5 < param_3);
  return 1;
}



/* Function: FUN_001023b8 @ 001023b8 */

undefined4
FUN_001023b8(undefined4 param_1,byte *param_2,undefined4 param_3,uint *param_4,undefined4 param_5)

{
  int iVar1;
  undefined4 uVar2;
  uint uVar3;
  byte local_48;
  byte local_47;
  byte local_46;
  byte local_45;
  char local_44;
  byte local_43;
  byte local_42;
  char local_41;
  byte local_40;
  byte local_3f;
  byte local_3e;
  byte local_3d;
  byte local_3c;
  byte local_3b;
  byte local_3a;
  byte local_39;
  byte local_38;
  byte local_37;
  byte local_36;
  byte local_35;
  byte local_34;
  byte local_33;
  byte local_32;
  byte local_31;
  byte local_30;
  byte local_2f;
  byte local_2e;
  byte local_2d;
  byte local_2c;
  byte local_2b;
  byte local_2a;
  byte local_29;
  
  iVar1 = FUN_001022b4(param_1,&local_48,0x20,param_5);
  if (iVar1 == 0) {
    return 0;
  }
  if (((param_4 != (uint *)0x0) && (local_44 == '\x02')) && (local_41 == ' ')) {
    if (((param_2 != (byte *)0x0) &&
        (((uint)local_47 << 0x10 | (uint)local_48 << 0x18 | (uint)local_45 | (uint)local_46 << 8) ==
         0x41414631)) && (local_43 - 1 < 2)) {
      *param_2 = local_43;
      uVar3 = (uint)local_3b << 0x10 | (uint)local_3c << 0x18 | (uint)local_39 | (uint)local_3a << 8
      ;
      *(uint *)(param_2 + 8) = uVar3;
      *(uint *)(param_2 + 4) =
           (uint)local_3f << 0x10 | (uint)local_40 << 0x18 | (uint)local_3d | (uint)local_3e << 8;
      param_2[1] = local_42;
      *(uint *)(param_2 + 0x10) =
           (uint)local_33 << 0x10 | (uint)local_34 << 0x18 | (uint)local_31 | (uint)local_32 << 8;
      *(uint *)(param_2 + 0x14) =
           (uint)local_37 << 0x10 | (uint)local_38 << 0x18 | (uint)local_35 | (uint)local_36 << 8;
      if (uVar3 < 0x200001) {
        *param_4 = (uint)local_2b << 0x10 | (uint)local_2c << 0x18 | (uint)local_29 |
                   (uint)local_2a << 8;
        param_4[1] = (uint)local_2f << 0x10 | (uint)local_30 << 0x18 | (uint)local_2d |
                     (uint)local_2e << 8;
        uVar2 = FUN_001022b4(param_1,param_3,uVar3,param_5);
        return uVar2;
      }
    }
  }
  return 0;
}



/* Function: main @ 00102fe4 */

/* WARNING: Restarted to delay deadcode elimination for space: ram */

uint main(int param_1,int param_2)

{
  longlong lVar1;
  longlong lVar2;
  undefined4 *puVar3;
  uint uVar4;
  __time_t _Var5;
  int iVar6;
  char *pcVar7;
  int iVar8;
  int iVar9;
  ssize_t sVar10;
  code *pcVar11;
  code *pcVar12;
  code *pcVar13;
  code *pcVar14;
  code *pcVar15;
  code *pcVar16;
  code *pcVar17;
  code *pcVar18;
  code *pcVar19;
  code *pcVar20;
  code *pcVar21;
  code *pcVar22;
  size_t sVar23;
  size_t __n;
  void *pvVar24;
  uint extraout_r1;
  uint uVar25;
  uint uVar26;
  code *pcVar27;
  short sVar28;
  code *pcVar29;
  uint uVar30;
  void *__s;
  bool bVar31;
  code *local_628;
  code *local_61c;
  code *local_618;
  int local_614;
  code *local_610;
  code *local_60c;
  code *local_608;
  code *local_604;
  code *local_600;
  code *local_5fc;
  code *local_5f8;
  void *local_5f4;
  void *local_5f0;
  code *local_5ec;
  int local_5e8;
  code *local_5d0;
  undefined4 uStack_5cc;
  int local_5c8;
  code *local_5a8;
  uint local_5a4;
  int local_5a0;
  undefined4 local_59c;
  undefined4 local_598;
  int local_594;
  undefined4 local_590;
  undefined4 local_58c;
  undefined4 local_588;
  undefined4 local_584;
  undefined4 local_580;
  undefined4 local_57c;
  uint local_578;
  uint uStack_574;
  uint local_570;
  uint uStack_56c;
  undefined4 local_568;
  uint local_564;
  undefined4 local_560;
  uint local_55c;
  undefined4 local_558;
  undefined4 local_554;
  undefined4 local_550;
  undefined4 local_54c;
  undefined4 local_548;
  uint local_544;
  undefined4 local_540;
  undefined4 local_53c;
  undefined4 local_538;
  uint local_534;
  undefined4 local_530;
  uint local_52c;
  timespec local_528;
  code *local_520;
  code *local_51c;
  undefined *local_518;
  undefined4 local_514 [5];
  char local_500;
  byte local_4ff;
  int local_4fc;
  int local_4f8;
  code *local_4f0;
  undefined4 uStack_4ec;
  int local_4e8;
  int local_4e4;
  int local_4e0;
  undefined4 local_4dc;
  uint local_4d8;
  int iStack_4d4;
  undefined4 local_4d0;
  undefined4 uStack_4cc;
  undefined4 local_4c8;
  undefined4 local_4bc;
  undefined4 uStack_4b8;
  undefined4 uStack_4b4;
  undefined4 uStack_4b0;
  undefined4 local_4ac;
  undefined4 uStack_4a8;
  undefined4 uStack_4a4;
  undefined4 uStack_4a0;
  undefined4 local_49c;
  undefined4 uStack_498;
  undefined4 uStack_494;
  undefined4 uStack_490;
  undefined4 local_48c;
  uint local_488;
  int local_484;
  size_t local_480;
  code *local_478;
  undefined4 uStack_474;
  undefined4 local_470;
  sockaddr local_428;
  code *local_418;
  undefined4 local_410;
  undefined4 local_3f8;
  uint local_3f4;
  
  if ((param_1 == 2) && (iVar6 = strcmp(*(char **)(param_2 + 4),"--check-vin"), iVar6 == 0)) {
    uVar30 = mibsi_vin_check();
    return uVar30;
  }
  mibsi_vin_wait_allowed();
  iVar6 = FUN_001058cc();
  puVar3 = DAT_0010400c;
  if (iVar6 == 0) {
    return 0x4e;
  }
  local_550 = *DAT_0010400c;
  local_54c = DAT_0010400c[1];
  local_5a8 = standalone_decoder_main;
  local_548 = DAT_0010400c[2];
  local_544 = DAT_0010400c[3];
  local_578 = 0;
  uStack_574 = 0;
  local_5a4 = 0;
  local_540 = *DAT_0010400c;
  local_53c = DAT_0010400c[1];
  local_5a0 = 0;
  local_59c = 0;
  local_538 = DAT_0010400c[2];
  local_534 = DAT_0010400c[3];
  local_598 = 0;
  local_594 = 0;
  local_590 = 0;
  local_568 = 0;
  local_564 = 0;
  local_560 = 0;
  local_55c = 0;
  local_558 = 0;
  local_554 = 0;
  local_530 = local_538;
  local_52c = local_534;
  signal(0x14,(__sighandler_t)&LAB_00101734);
  local_4bc = puVar3[4];
  uStack_4b8 = puVar3[5];
  uStack_4b4 = puVar3[6];
  uStack_4b0 = puVar3[7];
  local_58c = 0x1000c;
  local_588 = 0x1000c;
  local_584 = 0x8c;
  local_4ac = puVar3[8];
  uStack_4a8 = puVar3[9];
  uStack_4a4 = puVar3[10];
  uStack_4a0 = puVar3[0xb];
  local_580 = 0x8e;
  local_49c = puVar3[0xc];
  uStack_498 = puVar3[0xd];
  uStack_494 = puVar3[0xe];
  uStack_490 = puVar3[0xf];
  local_48c = puVar3[0x10];
  if (param_1 != 2) {
    return 2;
  }
  signal(0xd,(__sighandler_t)0x1);
  iVar6 = strcmp(*(char **)(param_2 + 4),"--live-carplay");
  if (iVar6 == 0) {
    DAT_00108630 = 0x5a0;
    DAT_00108634 = 0x21c;
    DAT_00108628 = 0;
    DAT_0010862c = 0;
    sVar28 = 0x3cc5;
  }
  else {
    sVar28 = 0x3cc3;
  }
  freopen("/dev/null","w",(FILE *)_Stdout);
  freopen("/dev/null","w",(FILE *)_Stderr);
  setenv("IVRDISPLAY","SCREEN",1);
  setenv("LD_LIBRARY_PATH",
         "/proc/boot:/usr/lib:/armle/lib:/armle/lib/dll:/armle/graphics:/lib:/mnt/app/root/lib-target:/eso/lib:/mnt/app/usr/lib:/mnt/app/armle/lib:/mnt/app/armle/lib/dll:/mnt/app/armle/usr/lib:/lib/dll"
         ,1);
  pcVar7 = getenv("AA_CLUSTER_HOLD");
  if ((pcVar7 == (char *)0x0) || (*pcVar7 == '\0')) {
    local_5f0 = (void *)0x2;
  }
  else {
    local_5f0 = (void *)atoi(pcVar7);
  }
  getenv("AA_CLUSTER_IVP_CONFIG_ONLY");
  pcVar7 = getenv("AA_CLUSTER_CROP");
  if (pcVar7 == (char *)0x0) {
LAB_0010368c:
    iVar8 = open("/mnt/app/root/mhi2q_aa_vc_backup/mode",0);
    if (-1 < iVar8) {
      sVar10 = read(iVar8,&local_428,0x3f);
      close(iVar8);
      if (0 < sVar10) {
        local_428.sa_data[sVar10 + -2] = '\0';
        pcVar7 = strstr((char *)&local_428,"blit");
        iVar8 = 0;
        if (pcVar7 != (char *)0x0) {
          iVar8 = 1;
        }
        goto LAB_0010324c;
      }
    }
    iVar8 = 1;
  }
  else {
    iVar8 = strcmp(pcVar7,"blit");
    if (iVar8 == 0) {
      DAT_001087f0 = 1;
      iVar8 = DAT_001087f0;
    }
    else {
      iVar9 = strcmp(pcVar7,"viewport");
      iVar8 = 0;
      if (iVar9 != 0) goto LAB_0010368c;
    }
  }
LAB_0010324c:
  DAT_001087f0 = iVar8;
  pcVar7 = getenv("AA_CLUSTER_CROP_RECT");
  if (pcVar7 != (char *)0x0) {
    sscanf(pcVar7,"%d,%d,%d,%d",DAT_00104010 + -8,DAT_00104010 + -4,DAT_00104010,DAT_00104010 + 4);
  }
  pcVar7 = *(char **)(param_2 + 4);
  iVar8 = strcmp(pcVar7,"--live");
  if (iVar8 == 0 || iVar6 == 0) {
    local_614 = socket(2,1,0);
    if (-1 < local_614) {
      memset(&local_428,0,0x10);
      local_428.sa_data[2] = '\x7f';
      local_428.sa_data[3] = '\0';
      local_428.sa_data[4] = '\0';
      local_428.sa_data[5] = '\x01';
      local_428.sa_family = CONCAT11(2,(undefined1)local_428.sa_family);
      local_428._0_4_ = CONCAT22(sVar28 << 8,local_428.sa_family) | 0x3c0000;
      iVar6 = connect(local_614,&local_428,0x10);
      if (((iVar6 == 0) && (uVar30 = fcntl(local_614,3,0), -1 < (int)uVar30)) &&
         (iVar6 = fcntl(local_614,4,uVar30 | 0x80), -1 < iVar6)) {
        pvVar24 = malloc(0x200000);
        local_578 = 0;
        uStack_574 = 0;
        local_5a4 = 0;
        if (pvVar24 != (void *)0x0) {
          local_628 = standalone_decoder_main;
          do {
            while( true ) {
              iVar6 = FUN_001023b8(local_614,&local_488,pvVar24,&local_578,0);
              if (iVar6 == 0) goto LAB_001037fc;
              if ((char)local_488 != '\x01') break;
              local_628 = (code *)local_480;
              if (0x3ff < local_480 - 1) goto LAB_001037fc;
              memcpy(&local_428,pvVar24,local_480);
            }
          } while (((local_488 & 0x100) == 0) || (local_628 == standalone_decoder_main));
          local_5f4 = malloc((int)local_628 + local_480);
          if (local_5f4 != (void *)0x0) {
            memcpy(local_5f4,&local_428,(size_t)local_628);
            memcpy((void *)((int)local_5f4 + (int)local_628),pvVar24,local_480);
            bVar31 = true;
            local_5d0 = local_478;
            uStack_5cc = uStack_474;
            local_5c8 = local_484;
            local_5a4 = (int)local_628 + local_480;
            free(pvVar24);
            goto LAB_001032e4;
          }
LAB_001037fc:
          free(pvVar24);
        }
        local_5f4 = (void *)0x0;
        goto LAB_0010331c;
      }
      close(local_614);
    }
    pcVar11 = standalone_decoder_main;
    local_608 = standalone_decoder_main;
    local_60c = standalone_decoder_main;
    pcVar29 = standalone_decoder_main;
    local_610 = standalone_decoder_main;
    pcVar12 = standalone_decoder_main;
    pcVar27 = standalone_decoder_main;
    pcVar13 = standalone_decoder_main;
    local_628 = standalone_decoder_main;
    pcVar15 = standalone_decoder_main;
    local_5f4 = (void *)0x0;
    pcVar14 = standalone_decoder_main;
    local_618 = standalone_decoder_main;
    local_61c = standalone_decoder_main;
    local_5ec = standalone_decoder_main;
    local_5f8 = standalone_decoder_main;
    local_604 = standalone_decoder_main;
    local_5fc = standalone_decoder_main;
    local_600 = standalone_decoder_main;
    local_5e8 = 0;
    local_614 = -1;
  }
  else {
    local_5f4 = (void *)FUN_001017ac(pcVar7,&local_5a4);
    if (local_5f4 == (void *)0x0) {
      local_614 = -1;
      pcVar11 = standalone_decoder_main;
      pcVar12 = standalone_decoder_main;
      pcVar27 = standalone_decoder_main;
      pcVar13 = standalone_decoder_main;
      pcVar14 = standalone_decoder_main;
      pcVar29 = standalone_decoder_main;
      pcVar15 = standalone_decoder_main;
      local_628 = standalone_decoder_main;
      local_61c = standalone_decoder_main;
      local_618 = standalone_decoder_main;
      local_610 = standalone_decoder_main;
      local_60c = standalone_decoder_main;
      local_608 = standalone_decoder_main;
      local_604 = standalone_decoder_main;
      local_600 = standalone_decoder_main;
      local_5fc = standalone_decoder_main;
      local_5f8 = standalone_decoder_main;
      local_5ec = standalone_decoder_main;
      local_5e8 = 0;
    }
    else {
      local_5d0 = standalone_decoder_main;
      uStack_5cc = 0;
      local_628 = (code *)0x1d;
      local_614 = -1;
      bVar31 = false;
      local_5c8 = 0;
LAB_001032e4:
      if (local_628 < local_5a4) {
        local_5e8 = dlopen("libOmxCore.so",0x102);
        if (local_5e8 == 0) {
          pcVar11 = standalone_decoder_main;
          pcVar12 = standalone_decoder_main;
          pcVar27 = standalone_decoder_main;
          pcVar13 = standalone_decoder_main;
          pcVar14 = standalone_decoder_main;
          pcVar29 = standalone_decoder_main;
          pcVar15 = standalone_decoder_main;
          local_628 = standalone_decoder_main;
          local_61c = standalone_decoder_main;
          local_618 = standalone_decoder_main;
          local_610 = standalone_decoder_main;
          local_60c = standalone_decoder_main;
          local_608 = standalone_decoder_main;
          local_604 = standalone_decoder_main;
          local_600 = standalone_decoder_main;
          local_5fc = standalone_decoder_main;
          local_5f8 = standalone_decoder_main;
          local_5ec = standalone_decoder_main;
        }
        else {
          pcVar11 = (code *)FUN_00101d94(local_5e8,"OMX_Init");
          local_5f8 = (code *)FUN_00101d94(local_5e8,"OMX_Deinit");
          pcVar12 = (code *)FUN_00101d94(local_5e8,"OMX_GetHandle");
          local_5ec = (code *)FUN_00101d94(local_5e8,"OMX_FreeHandle");
          iVar6 = FUN_00101d94(local_5e8,"OMX_SetupTunnel");
          if (((pcVar11 == standalone_decoder_main || local_5f8 == standalone_decoder_main) ||
              (iVar6 == 0 ||
               (pcVar12 == standalone_decoder_main || local_5ec == standalone_decoder_main))) ||
             (iVar6 = (*pcVar11)(), iVar6 != 0)) {
            local_628 = standalone_decoder_main;
            pcVar11 = local_628;
            pcVar12 = local_628;
            pcVar27 = local_628;
            pcVar13 = local_628;
            pcVar14 = local_628;
            pcVar29 = local_628;
            pcVar15 = local_628;
            local_61c = local_628;
            local_618 = local_628;
            local_610 = local_628;
            local_60c = local_628;
            local_608 = local_628;
            local_604 = local_628;
            local_600 = local_628;
            local_5fc = local_628;
          }
          else {
            memset(&local_520,0,0xc);
            local_51c = FUN_00101748;
            local_518 = &DAT_001025d4;
            local_520 = FUN_0010177c;
            iVar6 = (*pcVar12)(&local_5a8,"OMX.qcom.video.decoder.avc",0,&local_520);
            local_608 = standalone_decoder_main;
            if ((iVar6 == 0) && (local_608 = local_5a8, local_5a8 != standalone_decoder_main)) {
              iVar6 = FUN_00101894(local_5a8,local_5f4,local_628);
              pcVar11 = local_5a8;
              local_608 = standalone_decoder_main;
              if (iVar6 != 0) {
                memset(&local_488,0,0x60);
                local_488 = 0x60;
                local_484 = 0x1020101;
                iVar6 = (**(code **)(pcVar11 + 0x18))(pcVar11,0x2000001,&local_488);
                pcVar11 = local_5a8;
                if (iVar6 == 0) {
                  memset(&local_428,0,0x60);
                  local_428.sa_data[2] = '\x01';
                  local_428.sa_data[3] = '\x01';
                  local_428.sa_data[4] = '\x02';
                  local_428.sa_data[5] = '\x01';
                  local_428.sa_family = 0x60;
                  local_428.sa_data[0] = '\0';
                  local_428.sa_data[1] = '\0';
                  local_428.sa_data[6] = '\x01';
                  local_428.sa_data[7] = '\0';
                  local_428.sa_data[8] = '\0';
                  local_428.sa_data[9] = '\0';
                  iVar6 = (**(code **)(pcVar11 + 0x18))(pcVar11,0x2000001,&local_428);
                  pcVar29 = local_5a8;
                  if (iVar6 == 0) {
                    puts("stable direct decoder-to-Screen recovery path selected");
                    setenv("IPL_CONFIG_DIR","/etc/eso/production",1);
                    setenv("GRAPHICS_ROOT","/proc/boot/",1);
                    setenv("DISPLAY_CATALOG_PATH","/etc",1);
                    setenv("QC_GFX_CONF_DIR","/mnt/app/navigation",1);
                    setenv("ADRENO","OXILI",1);
                    local_600 = (code *)dlopen("libdisplayinit.so",0x102);
                    local_5fc = (code *)dlopen("libscreen.so.1",0x102);
                    local_604 = (code *)dlopen("libEGL.so.1",0x102);
                    pcVar12 = (code *)(uint)(local_600 == standalone_decoder_main ||
                                            local_5fc == standalone_decoder_main);
                    if (local_600 == standalone_decoder_main || local_5fc == standalone_decoder_main
                       ) {
                      pcVar11 = standalone_decoder_main;
                      local_608 = standalone_decoder_main;
                      local_60c = standalone_decoder_main;
                      pcVar12 = standalone_decoder_main;
                      local_610 = standalone_decoder_main;
                      pcVar27 = standalone_decoder_main;
                      pcVar13 = standalone_decoder_main;
                      pcVar15 = standalone_decoder_main;
                      local_618 = standalone_decoder_main;
                      pcVar14 = standalone_decoder_main;
                      local_61c = standalone_decoder_main;
                      local_628 = (code *)0x1;
                    }
                    else if (local_604 == standalone_decoder_main) {
                      local_610 = standalone_decoder_main;
                      pcVar11 = standalone_decoder_main;
                      local_60c = standalone_decoder_main;
                      local_608 = standalone_decoder_main;
LAB_00104214:
                      local_628 = (code *)0x1;
                      pcVar12 = local_610;
                      pcVar27 = local_610;
                      pcVar13 = local_610;
                      pcVar14 = local_610;
                      pcVar15 = local_610;
                      local_61c = local_610;
                      local_618 = local_610;
                    }
                    else {
                      pcVar27 = (code *)FUN_00101d94(local_600,"display_init");
                      pcVar13 = (code *)FUN_00101d94(local_600,"display_create_window");
                      pcVar14 = (code *)FUN_00101d94(local_5fc,"screen_set_window_property_iv");
                      iVar6 = FUN_00101d94(local_5fc,"screen_get_window_property_iv");
                      pcVar15 = (code *)FUN_00101d94(local_5fc,"screen_create_window_buffers");
                      local_60c = (code *)FUN_00101d94(local_5fc,"screen_destroy_window_buffers");
                      local_608 = (code *)FUN_00101d94(local_5fc,"screen_destroy_window");
                      pcVar16 = (code *)FUN_00101d94(local_5fc,"screen_get_window_property_pv");
                      pcVar17 = (code *)FUN_00101d94(local_5fc,"screen_get_buffer_property_pv");
                      DAT_00108778 = FUN_00101d94(local_5fc,"screen_post_window");
                      pcVar18 = (code *)dlsym(local_5fc,"screen_create_window");
                      pcVar19 = (code *)dlsym(local_5fc,"screen_create_window_type");
                      pcVar20 = (code *)FUN_00101d94(local_604,"eglGetDisplay");
                      pcVar21 = (code *)FUN_00101d94(local_604,"eglInitialize");
                      pcVar22 = (code *)FUN_00101d94(local_604,"eglChooseConfig");
                      iVar8 = FUN_00101d94(local_604,"eglGetError");
                      pcVar11 = (code *)FUN_00101d94(local_604,"eglTerminate");
                      if ((pcVar13 != standalone_decoder_main && pcVar27 != standalone_decoder_main)
                         && (pcVar12 = standalone_decoder_main,
                            iVar6 != 0 && pcVar14 != standalone_decoder_main)) {
                        if ((pcVar17 == standalone_decoder_main ||
                             (pcVar16 == standalone_decoder_main ||
                             pcVar15 == standalone_decoder_main)) || (DAT_00108778 == 0)) {
LAB_00103fd4:
                          pcVar12 = standalone_decoder_main;
                        }
                        else {
                          if (pcVar21 != standalone_decoder_main &&
                              pcVar20 != standalone_decoder_main) {
                            if (iVar8 == 0 || pcVar22 == standalone_decoder_main) goto LAB_00103fd4;
                            if (pcVar11 == standalone_decoder_main) {
                              local_628 = (code *)0x1;
                              pcVar12 = standalone_decoder_main;
                              pcVar27 = standalone_decoder_main;
                              pcVar13 = standalone_decoder_main;
                              pcVar14 = standalone_decoder_main;
                              pcVar15 = standalone_decoder_main;
                              local_61c = standalone_decoder_main;
                              local_618 = standalone_decoder_main;
                              local_610 = standalone_decoder_main;
                              goto LAB_00103348;
                            }
                            if ((DAT_001087f0 != 0) &&
                               (pcVar18 == standalone_decoder_main &&
                                pcVar19 == standalone_decoder_main)) {
                              DAT_001087f0 = 0;
                            }
                            (*pcVar27)(0,0);
                            local_610 = (code *)(*pcVar20)(0);
                            if (local_610 == standalone_decoder_main) goto LAB_00104214;
                            iVar8 = (*pcVar21)(local_610,&local_59c,&local_598);
                            if (((iVar8 == 0) ||
                                (iVar8 = (*pcVar22)(local_610,&local_4bc,local_514,5,&local_5a0),
                                iVar8 == 0)) || (local_5a0 < 1)) {
                              pcVar12 = standalone_decoder_main;
                              local_628 = (code *)0x1;
                              pcVar27 = pcVar12;
                              pcVar13 = pcVar12;
                              pcVar14 = pcVar12;
                              pcVar15 = pcVar12;
                              local_61c = pcVar12;
                              local_618 = pcVar12;
                              goto LAB_00103348;
                            }
                            local_568 = local_3f8;
                            local_564 = local_3f4;
                            if (DAT_001087f0 == 0) {
                              local_560 = local_3f8;
                              local_55c = local_3f4;
                            }
                            DAT_00108884 = local_3f8;
                            DAT_00108888 = local_3f4;
                            if (DAT_001087f0 != 0) {
                              local_550 = 0;
                              local_54c = 0;
                              local_560 = DAT_00108630;
                              local_548 = DAT_00108630;
                              local_538 = DAT_00108630;
                              local_530 = DAT_00108630;
                              local_55c = DAT_00108634;
                              local_544 = DAT_00108634;
                              local_534 = DAT_00108634;
                              local_52c = DAT_00108634;
                              local_540 = 0;
                              local_53c = 0;
                            }
                            DAT_00108784 = local_550;
                            DAT_00108788 = local_54c;
                            DAT_0010876c = local_548;
                            DAT_00108770 = local_544;
                            DAT_0010888c = local_540;
                            DAT_00108890 = local_53c;
                            DAT_00108878 = local_538;
                            DAT_0010887c = local_534;
                            DAT_00108638 = local_530;
                            DAT_0010863c = local_52c;
                            DAT_00108764 = local_558;
                            DAT_00108768 = local_554;
                            DAT_00108774 = pcVar14;
                            DAT_001087ec = iVar6;
                            (*pcVar13)(local_610,local_514[0],local_530,local_52c,3,&local_594,
                                       &local_590);
                            DAT_001087d0 = local_594;
                            if (local_60c != standalone_decoder_main && local_594 != 0) {
                              (*local_60c)();
                            }
                            if (((((DAT_001087d0 == 0) ||
                                  (iVar6 = (*pcVar14)(DAT_001087d0,0x30,&local_584), iVar6 != 0)) ||
                                 ((iVar6 = (*pcVar14)(DAT_001087d0,0xe,&local_588), iVar6 != 0 ||
                                  ((iVar6 = (*pcVar14)(DAT_001087d0,5,&local_560), iVar6 != 0 ||
                                   (iVar6 = (*pcVar14)(DAT_001087d0,0x23,&local_558), iVar6 != 0))))
                                 )) || (iVar6 = (*pcVar14)(DAT_001087d0,0x29,&local_550), iVar6 != 0
                                       )) ||
                               ((((iVar6 = (*pcVar14)(DAT_001087d0,0x2a,&local_548), iVar6 != 0 ||
                                  (iVar6 = (*pcVar14)(DAT_001087d0,0x44,&local_540), iVar6 != 0)) ||
                                 (iVar6 = (*pcVar14)(DAT_001087d0,0x48,&local_538), iVar6 != 0)) ||
                                (iVar6 = (*pcVar14)(DAT_001087d0,0x28,&local_530), iVar6 != 0))))
                            goto LAB_001041d8;
                            if (DAT_001087f0 == 0) {
                              DAT_001087d8 = local_418;
                              if (((local_418 == standalone_decoder_main) ||
                                  (iVar6 = (*pcVar15)(DAT_001087d0), iVar6 != 0)) ||
                                 ((iVar6 = FUN_00101c9c(), iVar6 == 0 ||
                                  ((DAT_00108880 = calloc((size_t)DAT_001087d8,4),
                                   DAT_00108880 == (void *)0x0 ||
                                   (iVar6 = (*pcVar16)(DAT_001087d0,0x25), iVar6 != 0))))))
                              goto LAB_001041d8;
LAB_00104788:
                              local_61c = calloc((size_t)local_478,4);
                              pcVar14 = calloc((size_t)local_418,4);
                              if ((local_61c == standalone_decoder_main ||
                                   pcVar14 == standalone_decoder_main) ||
                                 (iVar6 = (**(code **)(local_5a8 + 0x14))(local_5a8,0,2,0),
                                 iVar6 != 0)) {
                                pcVar12 = standalone_decoder_main;
                                local_628 = (code *)0x1;
                                local_618 = standalone_decoder_main;
                                pcVar27 = pcVar12;
                                pcVar13 = pcVar12;
                                pcVar15 = pcVar12;
                              }
                              else {
                                for (local_618 = standalone_decoder_main; local_618 < local_478;
                                    local_618 = local_618 + 1) {
                                  iVar6 = (**(code **)(local_5a8 + 0x38))
                                                    (local_5a8,local_61c + (int)local_618 * 4,0,0,
                                                     local_470);
                                  if (iVar6 != 0) {
                                    local_628 = (code *)0x1;
                                    pcVar12 = standalone_decoder_main;
                                    pcVar27 = standalone_decoder_main;
                                    pcVar13 = standalone_decoder_main;
                                    pcVar15 = standalone_decoder_main;
                                    goto LAB_00103348;
                                  }
                                }
                                pcVar15 = standalone_decoder_main;
                                while( true ) {
                                  if (local_418 <= pcVar15) break;
                                  local_4e8 = 0;
                                  iVar6 = (*pcVar17)(*(undefined4 *)
                                                      ((int)DAT_00108880 + (int)pcVar15 * 4),0x22,
                                                     &local_4e8);
                                  if (((iVar6 != 0) || (local_4e8 == 0)) ||
                                     (iVar6 = (**(code **)(pcVar29 + 0x34))
                                                        (pcVar29,pcVar14 + (int)pcVar15 * 4,1,
                                                         *(undefined4 *)
                                                          ((int)DAT_00108880 + (int)pcVar15 * 4),
                                                         local_410,local_4e8), iVar6 != 0))
                                  goto LAB_0010491c;
                                  pcVar15 = pcVar15 + 1;
                                }
                                iVar6 = FUN_00101974(local_5a8,2,10000);
                                if (iVar6 == 0) {
LAB_0010491c:
                                  local_628 = (code *)0x1;
                                  pcVar12 = standalone_decoder_main;
                                  pcVar27 = standalone_decoder_main;
                                  pcVar13 = standalone_decoder_main;
                                }
                                else {
                                  iVar6 = (**(code **)(local_5a8 + 0x14))(local_5a8,0,3,0);
                                  if (iVar6 == 0) {
                                    iVar6 = FUN_00101974(local_5a8,3,5000);
                                    if (iVar6 == 0) {
                                      local_628 = (code *)0x1;
                                      pcVar12 = standalone_decoder_main;
                                      pcVar27 = standalone_decoder_main;
                                      pcVar13 = (code *)0x1;
                                    }
                                    else {
                                      DAT_001087e8 = 1;
                                      for (pcVar12 = pcVar14; uVar4 = uStack_574, uVar30 = local_578
                                          , pcVar27 = local_5a8,
                                          pcVar12 != pcVar14 + (int)pcVar15 * 4;
                                          pcVar12 = pcVar12 + 4) {
                                        (**(code **)(pcVar29 + 0x44))
                                                  (pcVar29,*(undefined4 *)pcVar12);
                                      }
                                      if (bVar31) {
                                        iVar6 = local_5a4 - (int)local_628;
                                        pvVar24 = malloc(0x200000);
                                        local_57c = 0;
                                        local_570 = uVar30;
                                        uStack_56c = uVar4;
                                        if (pvVar24 == (void *)0x0 ||
                                            ((void *)((int)local_5f4 + (int)local_628) ==
                                             (void *)0x0 || iVar6 == 0)) {
LAB_00104a40:
                                          free(pvVar24);
                                          local_628 = (code *)0x1;
                                          pcVar12 = standalone_decoder_main;
                                          pcVar27 = (code *)0x1;
                                          pcVar13 = (code *)0x1;
                                        }
                                        else {
                                          memset(&local_4e8,0,0x28);
                                          memset(&local_500,0,0x18);
                                          local_4f0 = local_5d0;
                                          uStack_4ec = uStack_5cc;
                                          local_500 = '\x02';
                                          local_4fc = local_5c8;
                                          local_4ff = 1;
                                          local_4f8 = iVar6;
                                          p5092_vc_route_rearm();
                                          local_5f0 = (void *)((int)local_5f4 + (int)local_628);
                                          do {
                                            uVar4 = uStack_56c;
                                            uVar30 = local_570;
                                            lVar2 = CONCAT44(uStack_56c,local_570);
                                            if (local_500 == '\x01') {
                                              if ((0x3ff < local_4f8 - 1U) ||
                                                 (iVar6 = FUN_00101e48(pcVar27,local_61c,local_618,
                                                                       &local_57c,local_5f0,
                                                                       local_4f8,0,0,1), iVar6 == 0)
                                                 ) goto LAB_00104a40;
LAB_00104b48:
                                              FUN_00102150(local_614,&local_4e8);
                                            }
                                            else {
                                              iVar6 = clock_gettime(2,&local_528);
                                              _Var5 = local_528.tv_sec;
                                              uVar25 = extraout_r1;
                                              if (iVar6 != 0) {
                                                uVar25 = 0;
                                              }
                                              lVar1 = (ulonglong)uVar25 << 0x20;
                                              if (iVar6 == 0) {
                                                lVar1 = FUN_001064f4(local_528.tv_nsec,
                                                                     local_528.tv_nsec >> 0x1f,1000,
                                                                     0);
                                                lVar1 = (ulonglong)(uint)_Var5 * 1000000 + lVar1;
                                              }
                                              uVar26 = (uint)((ulonglong)lVar1 >> 0x20);
                                              uVar25 = (uint)lVar1;
                                              if (((local_4e4 != 0) && (local_4fc != local_4e8 + 1))
                                                 || ((local_4ff & 2) != 0)) {
                                                local_4e0 = 1;
                                              }
                                              local_4e8 = local_4fc;
                                              bVar31 = uVar26 <= uVar4;
                                              local_4e4 = 1;
                                              if (uVar4 == uVar26) {
                                                bVar31 = uVar25 <= uVar30;
                                              }
                                              if (((!bVar31 || lVar1 == lVar2) &&
                                                   (uVar30 != 0 || uVar4 != 0)) &&
                                                 (uVar26 != uVar4 + (uVar25 < uVar30) ||
                                                  750000 < uVar25 - uVar30)) {
                                                local_4e0 = 1;
                                                bVar31 = 0xfffffffe < local_4d8;
                                                local_4d8 = local_4d8 + 1;
                                                iStack_4d4 = iStack_4d4 + (uint)bVar31;
                                                goto LAB_00104b48;
                                              }
                                              if ((local_4ff & 1) != 0) {
                                                local_4d0 = 0;
                                                uStack_4cc = 0;
                                                local_4e0 = 0;
                                                local_4dc = 0;
                                                local_4c8 = 0;
                                              }
                                              if (local_4e0 != 0) {
                                                bVar31 = 0xfffffffe < local_4d8;
                                                local_4d8 = local_4d8 + 1;
                                                iStack_4d4 = iStack_4d4 + (uint)bVar31;
                                                local_4e8 = local_4fc;
                                                goto LAB_00104b48;
                                              }
                                              iVar6 = FUN_00101e48(pcVar27,local_61c,local_618,
                                                                   &local_57c,local_5f0,local_4f8,
                                                                   local_4f0,uStack_4ec,0);
                                              if (iVar6 == 0) goto LAB_00104a40;
                                              p5092_vc_route_poll(DAT_0010877c);
                                            }
                                            iVar6 = FUN_001023b8(local_614,&local_500,pvVar24,
                                                                 &local_570,&local_4e8);
                                            local_5f0 = pvVar24;
                                          } while (iVar6 != 0);
                                          free(pvVar24);
LAB_00104b88:
                                          local_628 = (code *)0x1;
                                          pcVar12 = (code *)(uint)(DAT_0010877c != 0);
                                          pcVar27 = (code *)0x1;
                                          pcVar13 = (code *)0x1;
                                        }
                                      }
                                      else {
                                        iVar6 = FUN_00101f5c(local_5a8,local_61c,local_618,local_5f4
                                                             ,local_5a4);
                                        if (iVar6 != 0) {
                                          sleep((uint)local_5f0);
                                          goto LAB_00104b88;
                                        }
                                        local_628 = (code *)0x1;
                                        pcVar12 = standalone_decoder_main;
                                        pcVar27 = (code *)0x1;
                                        pcVar13 = (code *)0x1;
                                      }
                                    }
                                  }
                                  else {
                                    local_628 = (code *)0x1;
                                    pcVar12 = standalone_decoder_main;
                                    pcVar27 = standalone_decoder_main;
                                    pcVar13 = (code *)0x1;
                                  }
                                }
                              }
                            }
                            else {
                              DAT_001087e0 = 3;
                              iVar6 = (*pcVar15)(DAT_001087d0,3);
                              if ((iVar6 != 0) ||
                                 (iVar6 = FUN_00101c9c(), sVar23 = DAT_001087e0, iVar6 == 0))
                              goto LAB_001041d8;
                              pvVar24 = calloc(DAT_001087e0,4);
                              DAT_001087d4 = pvVar24;
                              DAT_00108894 = calloc(sVar23,4);
                              if (DAT_00108894 == (void *)0x0 || pvVar24 == (void *)0x0) {
                                local_628 = (code *)0x1;
                                pcVar12 = standalone_decoder_main;
                                pcVar27 = standalone_decoder_main;
                                pcVar13 = standalone_decoder_main;
                                pcVar14 = standalone_decoder_main;
                                pcVar15 = standalone_decoder_main;
                                local_61c = standalone_decoder_main;
                                local_618 = standalone_decoder_main;
                                goto LAB_00103348;
                              }
                              iVar6 = (*pcVar16)(DAT_001087d0,0x25);
                              if (iVar6 == 0) {
                                sVar23 = qcom_tiled_plane_size(DAT_00108630,DAT_00108634);
                                __n = qcom_tiled_plane_size(DAT_00108630,DAT_00108634 >> 1);
                                uVar30 = 0;
                                while (iVar6 = uVar30 * 4, uVar30 < DAT_001087e0) {
                                  iVar8 = (*pcVar17)(*(undefined4 *)((int)DAT_001087d4 + uVar30 * 4)
                                                     ,0x22,(void *)((int)DAT_00108894 + iVar6));
                                  pvVar24 = DAT_00108894;
                                  if (iVar8 != 0) goto LAB_001041d8;
                                  __s = *(void **)((int)DAT_00108894 + uVar30 * 4);
                                  uVar30 = uVar30 + 1;
                                  if (__s == (void *)0x0) goto LAB_001041d8;
                                  memset(__s,0x10,sVar23);
                                  memset((void *)(*(int *)((int)pvVar24 + iVar6) + sVar23),0x80,__n)
                                  ;
                                }
                                iVar6 = (*pcVar16)(DAT_001087d0,0x5f,&DAT_0010878c);
                                if ((iVar6 == 0) && (DAT_0010878c != 0)) {
                                  if (pcVar18 == standalone_decoder_main) {
                                    if (pcVar19 != standalone_decoder_main) {
                                      iVar6 = (*pcVar19)(&DAT_00108780,DAT_0010878c,0);
                                      goto joined_r0x001046b8;
                                    }
                                  }
                                  else {
                                    iVar6 = (*pcVar18)(&DAT_00108780);
joined_r0x001046b8:
                                    if (iVar6 != 0) {
                                      DAT_00108780 = 0;
                                      local_618 = standalone_decoder_main;
                                      local_61c = standalone_decoder_main;
                                      local_628 = (code *)0x1;
                                      pcVar12 = standalone_decoder_main;
                                      pcVar27 = standalone_decoder_main;
                                      pcVar13 = standalone_decoder_main;
                                      pcVar14 = standalone_decoder_main;
                                      pcVar15 = standalone_decoder_main;
                                      goto LAB_00103348;
                                    }
                                  }
                                  if ((((DAT_00108780 != 0) &&
                                       (iVar6 = (*pcVar14)(DAT_00108780,0x30,&local_580), iVar6 == 0
                                       )) && (iVar6 = (*pcVar14)(DAT_00108780,0xe,&local_58c),
                                             iVar6 == 0)) &&
                                     (iVar6 = (*pcVar14)(DAT_00108780,5,&local_568), iVar6 == 0)) {
                                    DAT_001087d8 = local_418;
                                    if (((local_418 != standalone_decoder_main) &&
                                        (iVar6 = (*pcVar15)(DAT_00108780), iVar6 == 0)) &&
                                       ((DAT_00108880 = calloc((size_t)DAT_001087d8,4),
                                        DAT_00108880 != (void *)0x0 &&
                                        (iVar6 = (*pcVar16)(DAT_00108780,0x25), iVar6 == 0))))
                                    goto LAB_00104788;
                                  }
                                }
                              }
LAB_001041d8:
                              pcVar12 = standalone_decoder_main;
                              local_628 = (code *)0x1;
                              pcVar27 = pcVar12;
                              pcVar13 = pcVar12;
                              pcVar14 = pcVar12;
                              pcVar15 = pcVar12;
                              local_61c = pcVar12;
                              local_618 = pcVar12;
                            }
                            goto LAB_00103348;
                          }
                          pcVar12 = standalone_decoder_main;
                        }
                      }
                      local_628 = (code *)0x1;
                      pcVar27 = pcVar12;
                      pcVar13 = pcVar12;
                      pcVar14 = pcVar12;
                      pcVar15 = pcVar12;
                      local_61c = pcVar12;
                      local_618 = pcVar12;
                      local_610 = pcVar12;
                    }
                    goto LAB_00103348;
                  }
                  local_608 = standalone_decoder_main;
                }
                else {
                  local_608 = standalone_decoder_main;
                }
              }
            }
            local_628 = (code *)0x1;
            pcVar11 = local_608;
            pcVar12 = local_608;
            pcVar27 = local_608;
            pcVar13 = local_608;
            pcVar14 = local_608;
            pcVar29 = local_608;
            pcVar15 = local_608;
            local_61c = local_608;
            local_618 = local_608;
            local_610 = local_608;
            local_60c = local_608;
            local_604 = local_608;
            local_600 = local_608;
            local_5fc = local_608;
          }
        }
      }
      else {
LAB_0010331c:
        local_608 = standalone_decoder_main;
        local_60c = standalone_decoder_main;
        local_610 = standalone_decoder_main;
        local_628 = standalone_decoder_main;
        local_618 = standalone_decoder_main;
        local_61c = standalone_decoder_main;
        local_5ec = standalone_decoder_main;
        local_5f8 = standalone_decoder_main;
        local_604 = standalone_decoder_main;
        local_5fc = standalone_decoder_main;
        local_600 = standalone_decoder_main;
        local_5e8 = 0;
        pcVar11 = standalone_decoder_main;
        pcVar12 = standalone_decoder_main;
        pcVar27 = standalone_decoder_main;
        pcVar13 = standalone_decoder_main;
        pcVar14 = standalone_decoder_main;
        pcVar29 = standalone_decoder_main;
        pcVar15 = standalone_decoder_main;
      }
    }
  }
LAB_00103348:
  DAT_001087e8 = 0;
  p5092_vc_route_restore();
  if (pcVar27 == standalone_decoder_main) {
    if (pcVar13 == standalone_decoder_main) goto LAB_00103374;
  }
  else {
    (**(code **)(local_5a8 + 0x14))(local_5a8,0,2,0);
    FUN_00101974(local_5a8,2,5000);
  }
  (**(code **)(local_5a8 + 0x14))(local_5a8,0,1,0);
  for (pcVar27 = local_61c; pcVar27 != local_61c + (int)local_618 * 4; pcVar27 = pcVar27 + 4) {
    (**(code **)(local_5a8 + 0x3c))(local_5a8,0,*(undefined4 *)pcVar27);
  }
  for (pcVar27 = standalone_decoder_main; pcVar27 != pcVar15; pcVar27 = pcVar27 + 1) {
    (**(code **)(pcVar29 + 0x3c))(pcVar29,1,*(undefined4 *)(pcVar14 + (int)pcVar27 * 4));
  }
  FUN_00101974(local_5a8,1,5000);
LAB_00103374:
  if (local_5ec != standalone_decoder_main && local_5a8 != standalone_decoder_main) {
    (*local_5ec)();
  }
  if (local_5f8 == standalone_decoder_main) {
    local_628 = standalone_decoder_main;
  }
  if (local_628 != standalone_decoder_main) {
    (*local_5f8)();
  }
  if (local_5e8 != 0) {
    dlclose(local_5e8);
  }
  if (DAT_00108780 != 0 && local_60c != standalone_decoder_main) {
    (*local_60c)();
  }
  if (DAT_00108780 != 0 && local_608 != standalone_decoder_main) {
    (*local_608)();
  }
  if (DAT_001087d0 != 0 && local_60c != standalone_decoder_main) {
    (*local_60c)();
  }
  if (DAT_001087d0 != 0 && local_608 != standalone_decoder_main) {
    (*local_608)();
  }
  if (pcVar11 != standalone_decoder_main && local_610 != standalone_decoder_main) {
    (*pcVar11)(local_610);
  }
  if (local_604 != standalone_decoder_main) {
    dlclose(local_604);
  }
  if (local_5fc != standalone_decoder_main) {
    dlclose(local_5fc);
  }
  if (local_600 != standalone_decoder_main) {
    dlclose(local_600);
  }
  free(DAT_00108894);
  free(DAT_001087d4);
  free(DAT_00108880);
  free(local_61c);
  free(pcVar14);
  free(local_5f4);
  if (local_614 != -1) {
    close(local_614);
  }
  return (uint)pcVar12 ^ 1;
}



/* Function: FUN_00104d40 @ 00104d40 */

int FUN_00104d40(int param_1,int param_2,uint param_3,uint param_4)

{
  uint uVar1;
  uint uVar2;
  uint uVar3;
  int iVar4;
  
  uVar1 = param_4 >> 5;
  uVar2 = param_3 >> 6;
  iVar4 = (uVar1 & 0xfffffffe) * ((param_1 + 0x3fU >> 6) + 1 & 0xfffffffe) + uVar2;
  if ((uVar1 & 1) == 0) {
    uVar3 = param_2 + 0x1fU >> 5;
    if (((uVar3 & 1) == 0) || (uVar1 != uVar3 - 1)) {
      iVar4 = iVar4 + (uVar2 + 2 & 0xfffffffc);
    }
  }
  else {
    iVar4 = iVar4 + (uVar2 & 0xfffffffc) + 2;
  }
  return (param_3 & 0x3f) + ((param_4 & 0x1f) + iVar4 * 0x20) * 0x40;
}



/* Function: FUN_00104dbc @ 00104dbc */

undefined4
FUN_00104dbc(int param_1,uint param_2,uint param_3,int param_4,uint param_5,uint param_6,
            uint param_7,int param_8,uint param_9,uint param_10)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  uint __n;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  bool bVar8;
  bool bVar9;
  
  if (param_4 == 0 || param_1 == 0) {
    return 0xffffffff;
  }
  if ((param_7 + param_9 <= param_5) && (param_8 + param_10 <= param_6)) {
    bVar9 = param_3 <= param_10;
    bVar8 = param_10 == param_3;
    if (!bVar9 || bVar8) {
      bVar9 = param_2 <= param_9;
      bVar8 = param_9 == param_2;
    }
    if (!bVar9 || bVar8) {
      uVar7 = 0;
      if (param_10 != 0) {
        do {
          if (param_9 != 0) {
            uVar5 = 0;
            uVar4 = param_9;
            uVar6 = param_7;
            do {
              __n = 0x40 - (uVar6 & 0x3f);
              uVar3 = 0x40 - (uVar5 & 0x3f);
              if (uVar3 <= __n) {
                __n = uVar3;
              }
              if (uVar4 <= __n) {
                __n = uVar4;
              }
              iVar1 = FUN_00104d40(param_2,param_3,uVar5,uVar7);
              uVar5 = uVar5 + __n;
              iVar2 = FUN_00104d40(param_5,param_6,uVar6,uVar7 + param_8);
              memcpy((void *)(param_1 + iVar1),(void *)(param_4 + iVar2),__n);
              uVar4 = uVar4 - __n;
              uVar6 = uVar6 + __n;
            } while (uVar4 != 0);
          }
          uVar7 = uVar7 + 1;
        } while (uVar7 != param_10);
      }
      return 0;
    }
  }
  return 0xffffffff;
}



/* Function: qcom_tiled_plane_size @ 00104eec */

uint qcom_tiled_plane_size(int param_1,int param_2)

{
  return ((param_1 + 0x3fU >> 6) + 1 & 0xfffffffe) * (param_2 + 0x1fU >> 5) * 0x800 + 0x1fff &
         0xffffe000;
}



/* Function: qcom_tiled_fill_nv12 @ 00104f1c */

undefined4 qcom_tiled_fill_nv12(void *param_1,uint param_2,uint param_3,int param_4,byte param_5)

{
  uint uVar1;
  uint uVar2;
  
  uVar1 = param_3;
  if (0 < (int)param_3) {
    uVar1 = param_2;
  }
  if ((param_1 != (void *)0x0 && 0 < (int)uVar1) && (((param_2 | param_3) & 1) == 0)) {
    uVar2 = (param_2 + 0x3f >> 6) + 1 & 0xfffffffe;
    uVar1 = uVar2 * (param_3 + 0x1f >> 5) * 0x800 + 0x1fff & 0xffffe000;
    memset(param_1,param_4,uVar1);
    memset((void *)((int)param_1 + uVar1),(uint)param_5,
           uVar2 * ((param_3 >> 1) + 0x1f >> 5) * 0x800 + 0x1fff & 0xffffe000);
    DataMemoryBarrier(0xf);
    return 0;
  }
  return 0xffffffff;
}



/* Function: qcom_tiled_read_luma @ 00104fdc */

undefined4
qcom_tiled_read_luma
          (int param_1,int param_2,int param_3,int param_4,int param_5,undefined1 *param_6)

{
  int iVar1;
  
  iVar1 = 0;
  if (param_1 != 0) {
    iVar1 = param_2;
  }
  if ((((param_1 != 0 && param_2 != 0) && -1 < iVar1) && (0 < param_3 && -1 < param_4)) &&
     (param_4 < param_2 && -1 < param_5)) {
    iVar1 = 0;
    if (param_6 != (undefined1 *)0x0) {
      iVar1 = param_5 - param_3;
    }
    if (iVar1 < 0 != (param_6 != (undefined1 *)0x0 && SBORROW4(param_5,param_3))) {
      iVar1 = FUN_00104d40(param_2,param_3);
      *param_6 = *(undefined1 *)(param_1 + iVar1);
      return 0;
    }
  }
  return 0xffffffff;
}



/* Function: qcom_tiled_rect_is_black @ 00105068 */

undefined4
qcom_tiled_rect_is_black
          (int param_1,int param_2,int param_3,uint param_4,int param_5,uint param_6,uint param_7,
          byte param_8)

{
  int iVar1;
  byte *pbVar2;
  uint uVar4;
  uint uVar5;
  int iVar6;
  uint uVar7;
  uint local_34;
  int local_30;
  byte *pbVar3;
  
  iVar6 = 0;
  if (param_1 != 0) {
    iVar6 = param_2;
  }
  if ((((((param_1 != 0 && param_2 != 0) && -1 < iVar6) && (0 < param_3 && -1 < (int)param_4)) &&
       (0 < (int)param_6 && -1 < param_5)) &&
      (((int)param_7 <= param_3 && (0 < (int)param_7 && (int)param_6 <= param_2) &&
       ((int)param_4 <= (int)(param_2 - param_6))))) && (param_5 <= (int)(param_3 - param_7))) {
    local_34 = param_7;
    local_30 = 8;
    do {
      iVar6 = 0x10;
      uVar4 = param_6;
      do {
        iVar1 = FUN_00104d40(param_2,param_3,param_4 + (uVar4 >> 5),param_5 + (local_34 >> 4));
        uVar4 = uVar4 + param_6 * 2;
        if (param_8 < *(byte *)(param_1 + iVar1)) {
          return 0;
        }
        iVar6 = iVar6 + -1;
      } while (iVar6 != 0);
      local_30 = local_30 + -1;
      local_34 = local_34 + param_7 * 2;
    } while (local_30 != 0);
    iVar6 = param_5 + param_7;
    uVar4 = param_6;
    uVar7 = param_4;
    if (param_5 < iVar6) {
      do {
        iVar1 = FUN_00104d40(param_2,param_3,uVar7,param_5);
        uVar5 = 0x40 - (uVar7 & 0x3f);
        if (uVar4 <= uVar5) {
          uVar5 = uVar4;
        }
        pbVar3 = (byte *)(param_1 + iVar1);
        do {
          pbVar2 = pbVar3 + 1;
          if (param_8 < *pbVar3) {
            return 0;
          }
          pbVar3 = pbVar2;
        } while (pbVar2 != (byte *)(param_1 + uVar5 + iVar1));
        uVar4 = uVar4 - uVar5;
        uVar7 = uVar7 + uVar5;
      } while ((uVar4 != 0) ||
              (param_5 = param_5 + 1, uVar4 = param_6, uVar7 = param_4, param_5 != iVar6));
    }
    return 1;
  }
  return 0xffffffff;
}



/* Function: qcom_tiled_copy_nv12_crop @ 00105260 */

int qcom_tiled_copy_nv12_crop
              (int param_1,uint param_2,uint param_3,int param_4,uint param_5,uint param_6,
              uint param_7,uint param_8)

{
  uint uVar1;
  int iVar2;
  
  if (param_4 != 0 && param_1 != 0) {
    uVar1 = param_3;
    if (0 < (int)param_3) {
      uVar1 = param_2;
    }
    if ((0 < (int)uVar1) && (-1 < (int)(param_5 | param_6))) {
      uVar1 = param_8;
      if (0 < (int)param_8) {
        uVar1 = param_7;
      }
      if ((((0 < (int)uVar1) && (((param_7 | param_8 | param_6 | param_5) & 1) == 0)) &&
          ((int)(param_5 + param_7) <= (int)param_2)) && ((int)(param_6 + param_8) <= (int)param_3))
      {
        iVar2 = FUN_00104dbc(param_4,param_7,param_8,param_1,param_2,param_3,param_5,param_6,param_7
                             ,param_8);
        if (iVar2 != 0) {
          return iVar2;
        }
        iVar2 = FUN_00104dbc(param_4 + (((param_7 + 0x3f >> 6) + 1 & 0xfffffffe) *
                                        (param_8 + 0x1f >> 5) * 0x800 + 0x1fff & 0xffffe000),param_7
                             ,param_8 >> 1,
                             param_1 + (((param_2 + 0x3f >> 6) + 1 & 0xfffffffe) *
                                        (param_3 + 0x1f >> 5) * 0x800 + 0x1fff & 0xffffe000),param_2
                             ,param_3 >> 1,param_5,param_6 >> 1,param_7,param_8 >> 1);
        DataMemoryBarrier(0xf);
        return iVar2;
      }
    }
  }
  return -1;
}



/* Function: aa_refresh_transition_begin @ 001053c8 */

void aa_refresh_transition_begin
               (undefined4 *param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  if (param_1 == (undefined4 *)0x0) {
    return;
  }
  memset(param_1,0,0x10);
  *param_1 = param_3;
  param_1[1] = param_4;
  param_1[2] = 1;
  return;
}



/* Function: aa_refresh_transition_update @ 001053f8 */

undefined4
aa_refresh_transition_update
          (uint *param_1,undefined4 param_2,uint param_3,uint param_4,int param_5,int param_6,
          uint *param_7)

{
  uint uVar1;
  uint uVar2;
  bool bVar3;
  
  if (param_7 != (uint *)0x0) {
    *param_7 = 0;
  }
  if (param_1 == (uint *)0x0) {
    return 1;
  }
  if (param_1[2] == 0) {
    return 1;
  }
  uVar1 = *param_1;
  uVar2 = param_1[1];
  bVar3 = param_4 <= uVar2;
  if (uVar2 == param_4) {
    bVar3 = param_3 <= uVar1;
  }
  if (bVar3 && (uVar2 != param_4 || uVar1 != param_3)) {
    if (param_6 == 0) {
      return 0;
    }
  }
  else if (param_6 == 0) {
    if (param_5 != 0 || param_4 == uVar2 + (param_3 < uVar1) && param_3 - uVar1 < 1000) {
      return 0;
    }
    param_1[2] = 0;
    if (param_7 == (uint *)0x0) {
      return 1;
    }
    uVar1 = 2;
    goto LAB_00105458;
  }
  param_1[2] = 0;
  uVar1 = (uint)(param_7 != (uint *)0x0);
  if (param_7 == (uint *)0x0) {
    return 1;
  }
LAB_00105458:
  *param_7 = uVar1;
  return 1;
}



/* Function: FUN_001054c4 @ 001054c4 */

bool FUN_001054c4(void)

{
  int __fd;
  ssize_t sVar1;
  byte local_14;
  char local_13;
  
  local_14 = 0;
  local_13 = '\0';
  __fd = open("/tmp/mhi2q_aa_ui_view.state",0);
  if (-1 < __fd) {
    sVar1 = read(__fd,&local_14,2);
    close(__fd);
    if ((0 < sVar1) && (local_14 == 0x4c || (local_14 & 0xef) == 0x43)) {
      if (sVar1 == 1) {
        return true;
      }
      return local_13 == 'P';
    }
  }
  return false;
}



/* Function: FUN_0010555c @ 0010555c */

bool FUN_0010555c(undefined4 param_1)

{
  int iVar1;
  
  setenv("GRAPHICS_ROOT","/proc/boot/",1);
  setenv("IPL_CONFIG_DIR","/etc/eso/production/",1);
  setenv("LD_LIBRARY_PATH",
         "/proc/boot:/usr/lib:/armle/lib:/armle/lib/dll:/armle/graphics:/lib:/mnt/app/root/lib-target:/eso/lib:/mnt/app/usr/lib:/mnt/app/armle/lib:/mnt/app/armle/lib/dll:/mnt/app/armle/usr/lib:/lib/dll"
         ,1);
  iVar1 = spawnl(0,"/eso/bin/apps/versedt","versedt",&DAT_00107214,&DAT_00107218,param_1,0);
  return iVar1 == 0;
}



/* Function: p5092_vc_route_projection_requested @ 001055f4 */

bool p5092_vc_route_projection_requested(void)

{
  int iVar1;
  
  if (DAT_00108644 == 0) {
    return false;
  }
  iVar1 = FUN_001054c4();
  return iVar1 != 0;
}



/* Function: p5092_vc_route_rearm @ 0010561c */

void p5092_vc_route_rearm(void)

{
  if (DAT_00108644 != 0) {
    if (DAT_00108898 == 0) {
      DAT_00108648 = 1;
    }
    return;
  }
  return;
}



/* Function: p5092_vc_route_restore @ 00105654 */

void p5092_vc_route_restore(void)

{
  int iVar1;
  undefined4 uVar2;
  
  unlink("/tmp/mhi2q_aa_context70.request");
  if (DAT_00108898 == 0) {
    return;
  }
  FUN_0010555c(&DAT_0010723c);
  uVar2 = 0;
  iVar1 = spawnl(0,"/eso/bin/apps/dmdt",&DAT_00107254,&DAT_0010725c,&DAT_00107218,&DAT_00107260,0);
  if (iVar1 != 0) {
    iVar1 = spawnl(0,"/eso/bin/apps/dmdt",&DAT_00107254,&DAT_00107264,&DAT_00107218,0,uVar2);
    if (iVar1 != 0) {
      return;
    }
  }
  DAT_00108898 = 0;
  return;
}



/* Function: p5092_vc_route_poll @ 00105718 */

void p5092_vc_route_poll(int param_1)

{
  int iVar1;
  uint uVar2;
  undefined *puVar3;
  undefined *puVar4;
  char local_19;
  
  iVar1 = access("/tmp/mhi2q_aa_context70.request",0);
  if (iVar1 == 0) {
    local_19 = 'C';
    iVar1 = open("/tmp/mhi2q_aa_context70.request",0);
    if (-1 < iVar1) {
      read(iVar1,&local_19,1);
      close(iVar1);
    }
    unlink("/tmp/mhi2q_aa_context70.request");
    if (local_19 == 'O') {
      DAT_00108644 = 0;
      DAT_00108648 = 0;
      p5092_vc_route_restore();
      return;
    }
    uVar2 = 0;
    DAT_00108644 = 1;
    DAT_00108648 = 1;
  }
  else {
    uVar2 = (uint)(DAT_00108648 == 0);
  }
  if (param_1 == 0) {
    uVar2 = 1;
  }
  if ((uVar2 == 0) && (iVar1 = FUN_001054c4(), iVar1 != 0)) {
    puVar3 = &DAT_00107278;
    puVar4 = &DAT_0010727c;
    iVar1 = spawnl(0,"/eso/bin/apps/dmdt",&DAT_00107254,&DAT_00107268,&DAT_0010726c,&DAT_00107270,
                   &DAT_00107274,&DAT_00107278,&DAT_0010727c,0);
    if (iVar1 == 0) {
      iVar1 = spawnl(0,"/eso/bin/apps/dmdt",&DAT_00107254,&DAT_0010725c,&DAT_00107218,&DAT_0010726c,
                     0,puVar3,puVar4,uVar2);
      DAT_00108898 = (uint)(iVar1 == 0);
      if (DAT_00108898 != 0) {
        FUN_0010555c(&DAT_00107280);
        DAT_00108648 = 0;
      }
    }
  }
  return;
}



/* Function: FUN_001058cc @ 001058cc */

undefined4 FUN_001058cc(void)

{
  return 1;
}



/* Function: FUN_001058d4 @ 001058d4 */

void FUN_001058d4(uint *param_1,int param_2)

{
  uint uVar1;
  uint uVar2;
  uint uVar3;
  uint *puVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  uint uVar11;
  uint uVar12;
  uint uVar13;
  int iVar14;
  int iVar15;
  uint local_128 [65];
  
  iVar14 = 0;
  iVar15 = param_2;
  do {
    local_128[iVar14] =
         (uint)*(byte *)(iVar15 + 3) | (uint)*(byte *)(param_2 + iVar14 * 4) << 0x18 |
         (uint)*(byte *)(iVar15 + 1) << 0x10 | (uint)*(byte *)(iVar15 + 2) << 8;
    iVar14 = iVar14 + 1;
    iVar15 = iVar15 + 4;
  } while (iVar14 != 0x10);
  iVar15 = 0x30;
  puVar4 = local_128;
  do {
    uVar6 = puVar4[1];
    iVar15 = iVar15 + -1;
    uVar3 = puVar4[0xe];
    puVar4[0x10] = puVar4[9] + *puVar4 +
                   ((uVar6 >> 7 | uVar6 << 0x19) ^ (uVar6 >> 0x12 | uVar6 << 0xe) ^ uVar6 >> 3) +
                   ((uVar3 >> 0x11 | uVar3 << 0xf) ^ (uVar3 >> 0x13 | uVar3 << 0xd) ^ uVar3 >> 10);
    puVar4 = puVar4 + 1;
  } while (iVar15 != 0);
  iVar15 = 0;
  uVar3 = param_1[4];
  uVar6 = *param_1;
  uVar1 = param_1[2];
  uVar7 = param_1[1];
  uVar2 = param_1[6];
  uVar5 = param_1[5];
  uVar12 = param_1[3];
  uVar13 = param_1[7];
  do {
    uVar11 = uVar5;
    uVar10 = uVar2;
    uVar9 = uVar7;
    uVar8 = uVar1;
    uVar7 = uVar6;
    uVar5 = uVar3;
    iVar14 = iVar15 * 4;
    puVar4 = local_128 + iVar15;
    iVar15 = iVar15 + 1;
    iVar14 = *(int *)(&UNK_00107284 + iVar14) + *puVar4 +
             ((uVar5 >> 6 | uVar5 << 0x1a) ^ (uVar5 >> 0xb | uVar5 << 0x15) ^
             (uVar5 >> 0x19 | uVar5 << 7)) + (uVar10 & ~uVar5 ^ uVar11 & uVar5) + uVar13;
    uVar3 = uVar12 + iVar14;
    uVar6 = (uVar8 & uVar9 ^ (uVar8 ^ uVar9) & uVar7) +
            ((uVar7 >> 2 | uVar7 << 0x1e) ^ (uVar7 >> 0xd | uVar7 << 0x13) ^
            (uVar7 >> 0x16 | uVar7 << 10)) + iVar14;
    uVar1 = uVar9;
    uVar2 = uVar11;
    uVar12 = uVar8;
    uVar13 = uVar10;
  } while (iVar15 != 0x40);
  *param_1 = uVar6 + *param_1;
  param_1[1] = uVar7 + param_1[1];
  param_1[2] = uVar9 + param_1[2];
  param_1[3] = uVar8 + param_1[3];
  param_1[4] = uVar3 + param_1[4];
  param_1[5] = uVar5 + param_1[5];
  param_1[6] = uVar11 + param_1[6];
  param_1[7] = uVar10 + param_1[7];
  return;
}



/* Function: FUN_00105ae8 @ 00105ae8 */

longlong FUN_00105ae8(void)

{
  longlong lVar1;
  int iVar2;
  timespec local_10;
  
  iVar2 = clock_gettime(2,&local_10);
  if (iVar2 == 0) {
    lVar1 = (ulonglong)(uint)local_10.tv_sec * 1000 + (longlong)(local_10.tv_nsec / 1000000);
  }
  else {
    lVar1 = -1;
  }
  return lVar1;
}



/* Function: FUN_00105b40 @ 00105b40 */

bool FUN_00105b40(int param_1)

{
  if (0x19 < param_1 - 0x41U || (param_1 - 0x49U & 0xf7) == 0) {
    return false;
  }
  return param_1 != 0x4f;
}



/* Function: FUN_00105b7c @ 00105b7c */

bool FUN_00105b7c(uint param_1)

{
  int iVar1;
  
  if (param_1 - 0x30 < 10) {
    return true;
  }
  iVar1 = FUN_00105b40();
  if ((iVar1 == 0) && (0x19 < param_1 - 0x61)) {
    return (param_1 & 0xef) == 0x4f || (param_1 - 0x49 & 0xf7) == 0;
  }
  return true;
}



/* Function: mibsi_vin_check @ 00105bd8 */

/* WARNING: Removing unreachable block (ram,0x00106044) */
/* WARNING: Type propagation algorithm not settling */

undefined4 mibsi_vin_check(void)

{
  bool bVar1;
  bool bVar2;
  byte bVar3;
  int iVar4;
  __pid_t __pid;
  uint uVar5;
  ssize_t sVar6;
  void *pvVar7;
  __pid_t _Var8;
  int *piVar9;
  byte bVar10;
  ulong *puVar11;
  int iVar12;
  ulong uVar13;
  undefined1 *puVar14;
  byte *pbVar15;
  uint uVar16;
  uint uVar17;
  byte *__s2;
  byte *pbVar18;
  uint uVar19;
  bool bVar20;
  longlong lVar21;
  longlong lVar22;
  uint local_2108;
  int local_2104;
  int local_2100;
  pollfd local_20fc;
  timespec local_20f4 [2];
  undefined4 local_20e4;
  byte bStack_20e1;
  _union_1051 local_20e0;
  _union_1051 local_20dc;
  _union_1051 local_20d8;
  _union_1051 local_20d4;
  byte bStack_20c1;
  byte local_20c0 [28];
  ulong uStack_20a4;
  _union_1051 local_20a0 [7];
  ulong uStack_2084;
  uint local_2080;
  uint local_207c;
  ulong local_2078;
  long lStack_2074;
  undefined1 local_2068;
  undefined1 local_2067;
  undefined1 auStack_2066 [45];
  byte local_2039 [12];
  byte abStack_202d [8201];
  
  pbVar15 = &bStack_20c1;
  bVar3 = 0;
  iVar4 = 0;
  pbVar18 = pbVar15;
  bVar10 = 0xa7;
  do {
    iVar12 = iVar4 + 1;
    bVar3 = bVar3 | (&DAT_00108664)[iVar4];
    pbVar18 = pbVar18 + 1;
    *pbVar18 = (&DAT_00108664)[iVar4] ^ bVar10;
    iVar4 = iVar12;
    bVar10 = bVar10 + 0x1d;
  } while (iVar12 != 0x20);
  if (bVar3 == 0) {
    return 0;
  }
  local_2108 = 0;
  lVar21 = FUN_00105ae8();
  if (lVar21 < 0) {
    return 0x37;
  }
  local_20e0 = (_union_1051)pipe(&local_2104);
  if (local_20e0.sa_handler != standalone_decoder_main) {
    return 0x37;
  }
  local_20dc = local_20e0;
  local_20d8 = local_20e0;
  local_20d4 = local_20e0;
  sigemptyset((sigset_t *)&local_20d8);
  iVar4 = sigaction(0x12,(sigaction *)&local_20e0,(sigaction *)local_20a0);
  if (iVar4 != 0) {
    close(local_2104);
    close(local_2100);
    return 0x37;
  }
  __pid = fork();
  if (__pid == 0) {
    close(local_2104);
    iVar4 = dup2(local_2100,1);
    if (-1 < iVar4) {
      if (local_2100 != 1) {
        close(local_2100);
      }
      execl("/bin/sh","sh",&DAT_001073b0,
            "IPL_CONFIG_DIR=/etc/eso/production exec /eso/bin/apps/pc s:1501:30 2>/dev/null",0);
    }
                    /* WARNING: Subroutine does not return */
    _exit(0x7f);
  }
  close(local_2100);
  if (__pid < 0) {
    close(local_2104);
    sigaction(0x12,(sigaction *)local_20a0,(sigaction *)0x0);
    return 0x37;
  }
  uVar5 = fcntl(local_2104,3,0);
  if ((-1 < (int)uVar5) && (iVar4 = fcntl(local_2104,4,uVar5 | 0x80), -1 < iVar4)) {
    uVar5 = 0;
    bVar2 = false;
    bVar1 = false;
LAB_00105df0:
    do {
      lVar22 = FUN_00105ae8();
      if ((lVar22 < 0) ||
         (iVar4 = (int)((ulonglong)lVar22 >> 0x20) -
                  ((int)((ulonglong)lVar21 >> 0x20) + (uint)((uint)lVar22 < (uint)lVar21)),
         bVar20 = 2999 < (uint)lVar22 - (uint)lVar21,
         (int)-(iVar4 + (uint)bVar20) < 0 != (SBORROW4(0,iVar4) != SBORROW4(-iVar4,(uint)bVar20))))
      {
LAB_001061f4:
        close(local_2104);
        if (!bVar2) goto LAB_00106208;
        goto LAB_0010617c;
      }
      local_20fc.fd = local_2104;
      local_20fc.events = 5;
      local_20fc.revents = 0;
      if (bVar1) {
        local_20f4[0].tv_sec = 0;
        local_20f4[0].tv_nsec = 50000000;
        nanosleep(local_20f4,(timespec *)0x0);
LAB_00105e54:
        if ((bVar2) || (_Var8 = waitpid(__pid,(int *)&local_2108,0x40), __pid == _Var8)) {
          if (bVar1) {
            close(local_2104);
            iVar4 = sigaction(0x12,(sigaction *)local_20a0,(sigaction *)0x0);
            if (iVar4 != 0) {
              return 0x37;
            }
            if (((local_2108 | (int)local_2108 >> 8) & 0xff) != 0) {
              return 0x37;
            }
            pvVar7 = memchr(abStack_202d + 1,0,uVar5);
            if (pvVar7 != (void *)0x0) {
              return 0x37;
            }
            if (uVar5 < 0x11) {
              return 0x37;
            }
            uVar16 = 0x11;
            iVar4 = 0;
            bVar2 = false;
            do {
              if (((iVar4 == 0) || (iVar12 = FUN_00105b7c(abStack_202d[iVar4]), iVar12 == 0)) &&
                 ((uVar5 <= uVar16 || (iVar12 = FUN_00105b7c(abStack_202d[uVar16 + 1]), iVar12 == 0)
                  ))) {
                bVar1 = false;
                __s2 = abStack_202d + iVar4 + 1;
                uVar16 = 0;
                pbVar18 = __s2;
                do {
                  uVar17 = (uint)*pbVar18;
                  if ((9 < uVar17 - 0x30) &&
                     ((iVar12 = FUN_00105b40(uVar17), iVar12 == 0 || (0xc < uVar16))))
                  goto LAB_00105ef0;
                  uVar16 = uVar16 + 1;
                  if (uVar17 - 0x41 < 0x1a) {
                    bVar1 = true;
                  }
                  pbVar18 = pbVar18 + 1;
                } while (uVar16 != 0x11);
                if (bVar1) {
                  if ((bVar2) && (iVar12 = memcmp(local_20f4,__s2,0x11), iVar12 != 0)) {
                    return 0x37;
                  }
                  iVar4 = iVar4 + 0x10;
                  bVar2 = true;
                  memcpy(local_20f4,__s2,0x11);
                }
              }
LAB_00105ef0:
              uVar16 = iVar4 + 0x12;
              iVar4 = iVar4 + 1;
              if (uVar5 < uVar16) {
                if (!bVar2) {
                  return 0x37;
                }
                local_2068 = (undefined1)local_20e4;
                local_2039[1] = 0x12;
                local_2039[2] = 0;
                local_2039[3] = 0;
                local_2039[4] = 0;
                local_2067 = 0x80;
                lStack_2074 = local_20f4[0].tv_nsec;
                local_2078 = local_20f4[0].tv_sec;
                local_207c = 0;
                local_2080 = 0x88;
                uStack_2084 = DAT_001073a0;
                local_20a0[6] = (_union_1051)DAT_0010739c;
                local_20a0[5] = (_union_1051)DAT_00107398;
                local_20a0[4] = (_union_1051)DAT_00107394;
                local_20a0[3] = (_union_1051)DAT_00107390;
                local_20a0[2] = (_union_1051)DAT_0010738c;
                local_20a0[1] = (_union_1051)DAT_00107388;
                local_20a0[0] = DAT_00107384;
                memset(auStack_2066,0,0x26);
                pbVar18 = local_2039 + 1;
                uVar5 = 0;
                do {
                  uVar19 = 0x20 - uVar5;
                  uVar16 = uVar5 & 0xff;
                  uVar17 = uVar5 - 0x20;
                  uVar5 = uVar5 + 8;
                  pbVar18 = pbVar18 + -1;
                  *pbVar18 = (byte)(local_2080 >> uVar16) | (byte)(local_207c << (uVar19 & 0xff)) |
                             (byte)(local_207c >> (uVar17 & 0xff));
                } while (uVar5 != 0x40);
                FUN_001058d4(local_20a0,&local_2078);
                puVar11 = &uStack_20a4;
                puVar14 = (undefined1 *)((int)&local_20e0 + 1);
                do {
                  puVar11 = puVar11 + 1;
                  uVar13 = *puVar11;
                  puVar14[2] = (char)uVar13;
                  puVar14[-1] = (char)(uVar13 >> 0x18);
                  *puVar14 = (char)(uVar13 >> 0x10);
                  puVar14[1] = (char)(uVar13 >> 8);
                  puVar14 = puVar14 + 4;
                } while (puVar11 != &uStack_2084);
                pbVar18 = &bStack_20e1;
                bVar10 = 0;
                do {
                  pbVar18 = pbVar18 + 1;
                  pbVar15 = pbVar15 + 1;
                  bVar10 = bVar10 | *pbVar18 ^ *pbVar15;
                } while (pbVar18 != &bStack_20c1);
                if (bVar10 == 0) {
                  return 0;
                }
                return 0x38;
              }
            } while( true );
          }
LAB_00105dec:
          bVar2 = true;
          goto LAB_00105df0;
        }
        bVar20 = bVar2;
        if (-1 < _Var8) goto LAB_00105df0;
LAB_00106164:
        piVar9 = (int *)__get_errno_ptr();
        bVar2 = bVar20;
        if (*piVar9 != 4) {
LAB_00106174:
          close(local_2104);
          goto LAB_0010617c;
        }
      }
      else {
        iVar4 = poll(&local_20fc,1,0x32);
        if (iVar4 < 0) {
          piVar9 = (int *)__get_errno_ptr();
          if (*piVar9 != 4) goto LAB_001061f4;
          goto LAB_00105e54;
        }
        if (iVar4 == 0) goto LAB_00105e54;
        sVar6 = read(local_2104,abStack_202d + uVar5 + 1,0x2001 - uVar5);
        if (sVar6 < 1) {
          if (sVar6 == 0) {
            bVar1 = true;
            goto LAB_00105e54;
          }
          piVar9 = (int *)__get_errno_ptr();
          bVar20 = *piVar9 != 4 && *piVar9 != 0xb;
        }
        else {
          uVar5 = uVar5 + sVar6;
          bVar20 = uVar5 == 0x2001;
        }
        if ((bVar2) || (_Var8 = waitpid(__pid,(int *)&local_2108,0x40), __pid == _Var8)) {
          if (!bVar20) goto LAB_00105dec;
          goto LAB_00106174;
        }
        bVar2 = bVar20;
        if (_Var8 < 0) goto LAB_00106164;
      }
    } while (!bVar2);
  }
  close(local_2104);
LAB_00106208:
  kill(__pid,9);
  do {
    _Var8 = waitpid(__pid,(int *)&local_2108,0);
    if (-1 < _Var8) break;
    piVar9 = (int *)__get_errno_ptr();
  } while (*piVar9 == 4);
LAB_0010617c:
  sigaction(0x12,(sigaction *)local_20a0,(sigaction *)0x0);
  return 0x37;
}



/* Function: mibsi_vin_wait_allowed @ 00106280 */

void mibsi_vin_wait_allowed(void)

{
  int iVar1;
  int *piVar2;
  timespec local_10;
  
LAB_0010628c:
  iVar1 = mibsi_vin_check();
  do {
    if (iVar1 == 0) {
      return;
    }
    local_10.tv_nsec = 0;
    if (iVar1 == 0x38) {
      local_10.tv_sec = 0x1e;
    }
    else {
      local_10.tv_sec = 2;
    }
    do {
      iVar1 = nanosleep(&local_10,&local_10);
      if (iVar1 == 0) goto LAB_0010628c;
      piVar2 = (int *)__get_errno_ptr();
    } while (*piVar2 == 4);
    iVar1 = mibsi_vin_check();
  } while( true );
}



/* Function: FUN_001062e4 @ 001062e4 */

uint FUN_001062e4(uint param_1,uint param_2)

{
  uint uVar1;
  int iVar2;
  bool bVar3;
  bool bVar4;
  bool bVar5;
  bool bVar6;
  bool bVar7;
  bool bVar8;
  bool bVar9;
  bool bVar10;
  bool bVar11;
  bool bVar12;
  bool bVar13;
  bool bVar14;
  bool bVar15;
  bool bVar16;
  bool bVar17;
  bool bVar18;
  bool bVar19;
  bool bVar20;
  bool bVar21;
  bool bVar22;
  bool bVar23;
  bool bVar24;
  bool bVar25;
  bool bVar26;
  bool bVar27;
  bool bVar28;
  bool bVar29;
  bool bVar30;
  bool bVar31;
  bool bVar32;
  bool bVar33;
  
  if (param_2 - 1 == 0) {
    return param_1;
  }
  if (param_2 == 0) {
    uVar1 = 0;
    if (param_1 != 0) {
      uVar1 = 0xffffffff;
    }
    return uVar1;
  }
  if (param_1 <= param_2) {
    return (uint)(param_1 == param_2);
  }
  if ((param_2 & param_2 - 1) == 0) {
    return param_1 >> (0x1fU - LZCOUNT(param_2) & 0xff);
  }
  iVar2 = 0x1f - (LZCOUNT(param_2) - LZCOUNT(param_1));
  if (iVar2 == 0) {
    bVar3 = param_2 << 0x1f <= param_1;
    if (bVar3) {
      param_1 = param_1 + param_2 * -0x80000000;
    }
    bVar4 = param_2 << 0x1e <= param_1;
    if (bVar4) {
      param_1 = param_1 + param_2 * -0x40000000;
    }
    bVar5 = param_2 << 0x1d <= param_1;
    if (bVar5) {
      param_1 = param_1 + param_2 * -0x20000000;
    }
    bVar6 = param_2 << 0x1c <= param_1;
    if (bVar6) {
      param_1 = param_1 + param_2 * -0x10000000;
    }
    bVar7 = param_2 << 0x1b <= param_1;
    if (bVar7) {
      param_1 = param_1 + param_2 * -0x8000000;
    }
    bVar8 = param_2 << 0x1a <= param_1;
    if (bVar8) {
      param_1 = param_1 + param_2 * -0x4000000;
    }
    bVar9 = param_2 << 0x19 <= param_1;
    if (bVar9) {
      param_1 = param_1 + param_2 * -0x2000000;
    }
    bVar10 = param_2 << 0x18 <= param_1;
    if (bVar10) {
      param_1 = param_1 + param_2 * -0x1000000;
    }
    bVar11 = param_2 << 0x17 <= param_1;
    if (bVar11) {
      param_1 = param_1 + param_2 * -0x800000;
    }
    bVar12 = param_2 << 0x16 <= param_1;
    if (bVar12) {
      param_1 = param_1 + param_2 * -0x400000;
    }
    bVar13 = param_2 << 0x15 <= param_1;
    if (bVar13) {
      param_1 = param_1 + param_2 * -0x200000;
    }
    bVar14 = param_2 << 0x14 <= param_1;
    if (bVar14) {
      param_1 = param_1 + param_2 * -0x100000;
    }
    bVar15 = param_2 << 0x13 <= param_1;
    if (bVar15) {
      param_1 = param_1 + param_2 * -0x80000;
    }
    bVar16 = param_2 << 0x12 <= param_1;
    if (bVar16) {
      param_1 = param_1 + param_2 * -0x40000;
    }
    bVar17 = param_2 << 0x11 <= param_1;
    if (bVar17) {
      param_1 = param_1 + param_2 * -0x20000;
    }
    bVar18 = param_2 << 0x10 <= param_1;
    if (bVar18) {
      param_1 = param_1 + param_2 * -0x10000;
    }
    bVar19 = param_2 << 0xf <= param_1;
    if (bVar19) {
      param_1 = param_1 + param_2 * -0x8000;
    }
    bVar20 = param_2 << 0xe <= param_1;
    if (bVar20) {
      param_1 = param_1 + param_2 * -0x4000;
    }
    bVar21 = param_2 << 0xd <= param_1;
    if (bVar21) {
      param_1 = param_1 + param_2 * -0x2000;
    }
    bVar22 = param_2 << 0xc <= param_1;
    if (bVar22) {
      param_1 = param_1 + param_2 * -0x1000;
    }
    bVar23 = param_2 << 0xb <= param_1;
    if (bVar23) {
      param_1 = param_1 + param_2 * -0x800;
    }
    bVar24 = param_2 << 10 <= param_1;
    if (bVar24) {
      param_1 = param_1 + param_2 * -0x400;
    }
    bVar25 = param_2 << 9 <= param_1;
    if (bVar25) {
      param_1 = param_1 + param_2 * -0x200;
    }
    bVar26 = param_2 << 8 <= param_1;
    if (bVar26) {
      param_1 = param_1 + param_2 * -0x100;
    }
    bVar27 = param_2 << 7 <= param_1;
    if (bVar27) {
      param_1 = param_1 + param_2 * -0x80;
    }
    bVar28 = param_2 << 6 <= param_1;
    if (bVar28) {
      param_1 = param_1 + param_2 * -0x40;
    }
    bVar29 = param_2 << 5 <= param_1;
    if (bVar29) {
      param_1 = param_1 + param_2 * -0x20;
    }
    bVar30 = param_2 << 4 <= param_1;
    if (bVar30) {
      param_1 = param_1 + param_2 * -0x10;
    }
    bVar31 = param_2 << 3 <= param_1;
    if (bVar31) {
      param_1 = param_1 + param_2 * -8;
    }
    bVar32 = param_2 << 2 <= param_1;
    if (bVar32) {
      param_1 = param_1 + param_2 * -4;
    }
    bVar33 = param_2 << 1 <= param_1;
    if (bVar33) {
      param_1 = param_1 + param_2 * -2;
    }
    return (((((((((((((((((((((((((((((((uint)bVar3 * 2 + (uint)bVar4) * 2 + (uint)bVar5) * 2 +
                                      (uint)bVar6) * 2 + (uint)bVar7) * 2 + (uint)bVar8) * 2 +
                                   (uint)bVar9) * 2 + (uint)bVar10) * 2 + (uint)bVar11) * 2 +
                                (uint)bVar12) * 2 + (uint)bVar13) * 2 + (uint)bVar14) * 2 +
                             (uint)bVar15) * 2 + (uint)bVar16) * 2 + (uint)bVar17) * 2 +
                          (uint)bVar18) * 2 + (uint)bVar19) * 2 + (uint)bVar20) * 2 + (uint)bVar21)
                       * 2 + (uint)bVar22) * 2 + (uint)bVar23) * 2 + (uint)bVar24) * 2 +
                   (uint)bVar25) * 2 + (uint)bVar26) * 2 + (uint)bVar27) * 2 + (uint)bVar28) * 2 +
               (uint)bVar29) * 2 + (uint)bVar30) * 2 + (uint)bVar31) * 2 + (uint)bVar32) * 2 +
           (uint)bVar33) * 2 + (uint)(param_2 <= param_1);
  }
                    /* WARNING: Could not recover jumptable at 0x00106318. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  uVar1 = (*(code *)(iVar2 * 0xc + 0x106320))();
  return uVar1;
}



/* Function: FUN_001064d0 @ 001064d0 */

ulonglong FUN_001064d0(int param_1,int param_2)

{
  uint uVar1;
  int iVar2;
  
  if (param_2 != 0) {
    iVar2 = FUN_001062e4();
    return CONCAT44(param_1 - param_2 * iVar2,iVar2);
  }
  uVar1 = 0;
  if (param_1 != 0) {
    uVar1 = 0xffffffff;
  }
  return (ulonglong)uVar1;
}



/* Function: FUN_001064f4 @ 001064f4 */

undefined8 FUN_001064f4(int param_1,int param_2,int param_3,int param_4)

{
  undefined8 uVar1;
  
  if (param_4 != 0 || param_3 != 0) {
    uVar1 = FUN_0010656c();
    return uVar1;
  }
  if (param_2 != 0 || param_1 != 0) {
    param_2 = -1;
    param_1 = -1;
  }
  return CONCAT44(param_2,param_1);
}



/* Function: FUN_00106530 @ 00106530 */

void FUN_00106530(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_001065a8();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_0010656c @ 0010656c */

void FUN_0010656c(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_00106728();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_001065a8 @ 001065a8 */

undefined8 FUN_001065a8(uint param_1,uint param_2,uint param_3,uint param_4)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  bool bVar11;
  
  if ((int)param_2 < 0) {
    bVar11 = param_1 != 0;
    param_1 = -param_1;
    uVar10 = 0xffffffff;
    param_2 = -(param_2 + bVar11);
  }
  else {
    uVar10 = 0;
  }
  if ((int)param_4 < 0) {
    bVar11 = param_3 != 0;
    param_3 = -param_3;
    uVar10 = ~uVar10;
    param_4 = -(param_4 + bVar11);
  }
  bVar11 = param_4 <= param_2;
  if (param_2 == param_4) {
    bVar11 = param_3 <= param_1;
  }
  if (bVar11) {
    if (param_4 == 0) {
      iVar1 = LZCOUNT(param_3) + 0x20;
    }
    else {
      iVar1 = LZCOUNT(param_4);
    }
    if (param_2 == 0) {
      iVar2 = LZCOUNT(param_1) + 0x20;
    }
    else {
      iVar2 = LZCOUNT(param_2);
    }
    uVar3 = iVar1 - iVar2;
    uVar7 = param_4 << (uVar3 & 0xff) | param_3 << (uVar3 - 0x20 & 0xff) |
            param_3 >> (0x20 - uVar3 & 0xff);
    param_3 = param_3 << (uVar3 & 0xff);
    bVar11 = uVar7 <= param_2;
    if (param_2 == uVar7) {
      bVar11 = param_3 <= param_1;
    }
    if (bVar11) {
      bVar11 = param_1 < param_3;
      param_1 = param_1 - param_3;
      param_2 = param_2 - (uVar7 + bVar11);
      uVar9 = 1 << (uVar3 - 0x20 & 0xff) | 1U >> (0x20 - uVar3 & 0xff);
      uVar8 = 1 << (uVar3 & 0xff);
    }
    else {
      uVar8 = 0;
      uVar9 = 0;
    }
    if (uVar3 != 0) {
      uVar5 = uVar7 >> 1;
      uVar7 = (uint)((byte)uVar7 & 1) << 0x1f | param_3 >> 1;
      uVar4 = uVar3;
      do {
        while( true ) {
          bVar11 = param_2 <= uVar5;
          if (uVar5 == param_2) {
            bVar11 = param_1 <= uVar7;
          }
          if (!bVar11 || uVar5 == param_2 && uVar7 == param_1) break;
          bVar11 = CARRY4(param_1,param_1);
          param_1 = param_1 * 2;
          param_2 = param_2 * 2 + (uint)bVar11;
          uVar4 = uVar4 - 1;
          if (uVar4 == 0) goto LAB_001066a8;
        }
        bVar11 = param_1 < uVar7;
        uVar6 = param_1 - uVar7;
        param_1 = uVar6 * 2 + 1;
        param_2 = (param_2 - (uVar5 + bVar11)) * 2 + (uint)CARRY4(uVar6,uVar6) +
                  (uint)(0xfffffffe < uVar6 * 2);
        uVar4 = uVar4 - 1;
      } while (uVar4 != 0);
LAB_001066a8:
      uVar5 = param_1 >> (uVar3 & 0xff) | param_2 << (0x20 - uVar3 & 0xff) |
              param_2 >> (uVar3 - 0x20 & 0xff);
      bVar11 = CARRY4(uVar8,param_1);
      param_1 = uVar8 + param_1;
      uVar7 = uVar5 << (uVar3 & 0xff);
      uVar8 = param_1 - uVar7;
      uVar9 = (uVar9 + param_2 + (uint)bVar11) -
              (((param_2 >> (uVar3 & 0xff)) << (uVar3 & 0xff) | uVar5 << (uVar3 - 0x20 & 0xff) |
               uVar5 >> (0x20 - uVar3 & 0xff)) + (uint)(param_1 < uVar7));
    }
  }
  else {
    uVar8 = 0;
    uVar9 = 0;
  }
  uVar10 = (uint)(uVar10 != 0);
  return CONCAT44((uVar9 ^ -(uint)(uVar10 != 0)) + (uint)CARRY4(uVar10,uVar8 ^ -uVar10),
                  uVar10 + (uVar8 ^ -uVar10));
}



/* Function: FUN_00106728 @ 00106728 */

undefined8 FUN_00106728(uint param_1,uint param_2,uint param_3,uint param_4)

{
  uint uVar1;
  uint uVar2;
  int iVar3;
  int iVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  bool bVar10;
  
  bVar10 = param_4 <= param_2;
  if (param_2 == param_4) {
    bVar10 = param_3 <= param_1;
  }
  if (!bVar10) {
    return 0;
  }
  if (param_4 == 0) {
    iVar3 = LZCOUNT(param_3) + 0x20;
  }
  else {
    iVar3 = LZCOUNT(param_4);
  }
  if (param_2 == 0) {
    iVar4 = LZCOUNT(param_1) + 0x20;
  }
  else {
    iVar4 = LZCOUNT(param_2);
  }
  uVar5 = iVar3 - iVar4;
  uVar8 = param_4 << (uVar5 & 0xff) | param_3 << (uVar5 - 0x20 & 0xff) |
          param_3 >> (0x20 - uVar5 & 0xff);
  param_3 = param_3 << (uVar5 & 0xff);
  bVar10 = uVar8 <= param_2;
  if (param_2 == uVar8) {
    bVar10 = param_3 <= param_1;
  }
  if (bVar10) {
    bVar10 = param_1 < param_3;
    param_1 = param_1 - param_3;
    param_2 = param_2 - (uVar8 + bVar10);
    uVar2 = 1 << (uVar5 - 0x20 & 0xff) | 1U >> (0x20 - uVar5 & 0xff);
    uVar1 = 1 << (uVar5 & 0xff);
  }
  else {
    uVar1 = 0;
    uVar2 = 0;
  }
  if (uVar5 == 0) {
    return CONCAT44(uVar2,uVar1);
  }
  uVar9 = uVar8 >> 1;
  uVar8 = (uint)((byte)uVar8 & 1) << 0x1f | param_3 >> 1;
  uVar6 = uVar5;
  do {
    while( true ) {
      bVar10 = param_2 <= uVar9;
      if (uVar9 == param_2) {
        bVar10 = param_1 <= uVar8;
      }
      if (!bVar10 || uVar9 == param_2 && uVar8 == param_1) break;
      bVar10 = CARRY4(param_1,param_1);
      param_1 = param_1 * 2;
      param_2 = param_2 * 2 + (uint)bVar10;
      uVar6 = uVar6 - 1;
      if (uVar6 == 0) goto LAB_00106804;
    }
    bVar10 = param_1 < uVar8;
    uVar7 = param_1 - uVar8;
    param_1 = uVar7 * 2 + 1;
    param_2 = (param_2 - (uVar9 + bVar10)) * 2 + (uint)CARRY4(uVar7,uVar7) +
              (uint)(0xfffffffe < uVar7 * 2);
    uVar6 = uVar6 - 1;
  } while (uVar6 != 0);
LAB_00106804:
  uVar9 = param_1 >> (uVar5 & 0xff) | param_2 << (0x20 - uVar5 & 0xff) |
          param_2 >> (uVar5 - 0x20 & 0xff);
  uVar8 = uVar9 << (uVar5 & 0xff);
  return CONCAT44((uVar2 + param_2 + (uint)CARRY4(uVar1,param_1)) -
                  (((param_2 >> (uVar5 & 0xff)) << (uVar5 & 0xff) | uVar9 << (uVar5 - 0x20 & 0xff) |
                   uVar9 >> (0x20 - uVar5 & 0xff)) + (uint)(uVar1 + param_1 < uVar8)),
                  (uVar1 + param_1) - uVar8);
}



/* Function: _fini @ 0010684c */

void _fini(void)

{
  return;
}


