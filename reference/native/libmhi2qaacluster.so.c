/* RECONSTRUCTED PSEUDOCODE - not original source, not build-ready.
 * Input: libmhi2qaacluster.so
 * Language: ARM:LE:32:v8
 * Generated with Ghidra. Types, signatures and control flow may be inaccurate.
 */


/* Function: _init @ 00010d94 */

int _init(EVP_PKEY_CTX *ctx)

{
  return (int)ctx;
}



/* Function: clock_gettime @ 00010db0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int clock_gettime(clockid_t __clock_id,timespec *__tp)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_clock_gettime_0001d240)(__clock_id);
  return iVar1;
}



/* Function: waitpid @ 00010dbc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t waitpid(__pid_t __pid,int *__stat_loc,int __options)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_waitpid_0001d244)(__pid);
  return _Var1;
}



/* Function: pthread_cond_signal @ 00010dc8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_signal(pthread_cond_t *__cond)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_signal_0001d248)(__cond);
  return iVar1;
}



/* Function: dlerror @ 00010dd4 */

void dlerror(void)

{
  (*(code *)PTR_dlerror_0001d24c)();
  return;
}



/* Function: sysconf @ 00010de0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

long sysconf(int __name)

{
  long lVar1;
  
  lVar1 = (*(code *)PTR_sysconf_0001d250)(__name);
  return lVar1;
}



/* Function: pthread_create @ 00010dec */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_create(pthread_t *__newthread,pthread_attr_t *__attr,__start_routine *__start_routine,
                  void *__arg)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_create_0001d254)(__newthread);
  return iVar1;
}



/* Function: sigemptyset @ 00010df8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigemptyset(sigset_t *__set)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigemptyset_0001d258)(__set);
  return iVar1;
}



/* Function: snprintf @ 00010e04 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int snprintf(char *__s,size_t __maxlen,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_snprintf_0001d25c)(__s);
  return iVar1;
}



/* Function: bind @ 00010e10 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int bind(int __fd,sockaddr *__addr,socklen_t __len)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_bind_0001d260)(__fd);
  return iVar1;
}



/* Function: setsockopt @ 00010e1c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int setsockopt(int __fd,int __level,int __optname,void *__optval,socklen_t __optlen)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_setsockopt_0001d264)(__fd);
  return iVar1;
}



/* Function: getenv @ 00010e28 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * getenv(char *__name)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_getenv_0001d268)(__name);
  return pcVar1;
}



/* Function: usleep @ 00010e34 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int usleep(__useconds_t __useconds)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_usleep_0001d26c)(__useconds);
  return iVar1;
}



/* Function: shutdown @ 00010e40 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int shutdown(int __fd,int __how)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_shutdown_0001d270)(__fd);
  return iVar1;
}



/* Function: memcpy @ 00010e4c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memcpy(void *__dest,void *__src,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memcpy_0001d274)(__dest);
  return pvVar1;
}



/* Function: __cxa_finalize @ 00010e58 */

void __cxa_finalize(void)

{
  (*(code *)PTR___cxa_finalize_0001d278)();
  return;
}



/* Function: malloc @ 00010e64 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * malloc(size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_malloc_0001d27c)(__size);
  return pvVar1;
}



/* Function: sleep @ 00010e70 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

uint sleep(uint __seconds)

{
  uint uVar1;
  
  uVar1 = (*(code *)PTR_sleep_0001d280)(__seconds);
  return uVar1;
}



/* Function: vsnprintf @ 00010e7c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int vsnprintf(char *__s,size_t __maxlen,char *__format,__gnuc_va_list __arg)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_vsnprintf_0001d284)(__s);
  return iVar1;
}



/* Function: strtoul @ 00010e88 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ulong strtoul(char *__nptr,char **__endptr,int __base)

{
  ulong uVar1;
  
  uVar1 = (*(code *)PTR_strtoul_0001d288)(__nptr);
  return uVar1;
}



/* Function: spawnl @ 00010e94 */

void spawnl(void)

{
  (*(code *)PTR_spawnl_0001d28c)();
  return;
}



/* Function: dladdr @ 00010ea0 */

void dladdr(void)

{
  (*(code *)PTR_dladdr_0001d290)();
  return;
}



/* Function: lseek @ 00010eac */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__off_t lseek(int __fd,__off_t __offset,int __whence)

{
  __off_t _Var1;
  
  _Var1 = (*(code *)PTR_lseek_0001d294)(__fd);
  return _Var1;
}



/* Function: sigaddset @ 00010eb8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigaddset(sigset_t *__set,int __signo)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigaddset_0001d298)(__set);
  return iVar1;
}



/* Function: pthread_detach @ 00010ec4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_detach(pthread_t __th)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_detach_0001d29c)(__th);
  return iVar1;
}



/* Function: rename @ 00010ed0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int rename(char *__old,char *__new)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_rename_0001d2a0)(__old);
  return iVar1;
}



/* Function: write @ 00010edc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t write(int __fd,void *__buf,size_t __n)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_write_0001d2a4)(__fd);
  return sVar1;
}



/* Function: kill @ 00010ee8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int kill(__pid_t __pid,int __sig)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_kill_0001d2a8)(__pid);
  return iVar1;
}



/* Function: __deregister_frame_info @ 00010ef4 */

void __deregister_frame_info(void)

{
  (*(code *)PTR___deregister_frame_info_0001d2ac)();
  return;
}



/* Function: __get_errno_ptr @ 00010f00 */

void __get_errno_ptr(void)

{
  (*(code *)PTR___get_errno_ptr_0001d2b0)();
  return;
}



/* Function: read @ 00010f0c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t read(int __fd,void *__buf,size_t __nbytes)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_read_0001d2b4)(__fd);
  return sVar1;
}



/* Function: strncmp @ 00010f18 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int strncmp(char *__s1,char *__s2,size_t __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_strncmp_0001d2b8)(__s1);
  return iVar1;
}



/* Function: dlopen @ 00010f24 */

void dlopen(void)

{
  (*(code *)PTR_dlopen_0001d2bc)();
  return;
}



/* Function: unlink @ 00010f30 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int unlink(char *__name)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_unlink_0001d2c0)(__name);
  return iVar1;
}



/* Function: accept @ 00010f3c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int accept(int __fd,sockaddr *__addr,socklen_t *__addr_len)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_accept_0001d2c4)(__fd);
  return iVar1;
}



/* Function: pthread_sigmask @ 00010f48 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_sigmask(int __how,__sigset_t *__newmask,__sigset_t *__oldmask)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_sigmask_0001d2c8)(__how);
  return iVar1;
}



/* Function: realloc @ 00010f54 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * realloc(void *__ptr,size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_realloc_0001d2cc)(__ptr);
  return pvVar1;
}



/* Function: memcmp @ 00010f60 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int memcmp(void *__s1,void *__s2,size_t __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_memcmp_0001d2d0)(__s1);
  return iVar1;
}



/* Function: dlsym @ 00010f6c */

void dlsym(void)

{
  (*(code *)PTR_dlsym_0001d2d4)();
  return;
}



/* Function: memset @ 00010f78 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memset(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memset_0001d2d8)(__s);
  return pvVar1;
}



/* Function: poll @ 00010f84 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int poll(pollfd *__fds,nfds_t __nfds,int __timeout)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_poll_0001d2dc)(__fds);
  return iVar1;
}



/* Function: strcmp @ 00010f90 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int strcmp(char *__s1,char *__s2)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_strcmp_0001d2e0)(__s1);
  return iVar1;
}



/* Function: pthread_mutex_unlock @ 00010f9c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_unlock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_unlock_0001d2e4)(__mutex);
  return iVar1;
}



/* Function: socket @ 00010fa8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int socket(int __domain,int __type,int __protocol)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_socket_0001d2e8)(__domain);
  return iVar1;
}



/* Function: access @ 00010fb4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int access(char *__name,int __type)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_access_0001d2ec)(__name);
  return iVar1;
}



/* Function: pthread_mutex_lock @ 00010fc0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_lock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_lock_0001d2f0)(__mutex);
  return iVar1;
}



/* Function: pthread_cond_wait @ 00010fcc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_wait(pthread_cond_t *__cond,pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_wait_0001d2f4)(__cond);
  return iVar1;
}



/* Function: listen @ 00010fd8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int listen(int __fd,int __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_listen_0001d2f8)(__fd);
  return iVar1;
}



/* Function: strlen @ 00010fe4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t strlen(char *__s)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_strlen_0001d2fc)(__s);
  return sVar1;
}



/* Function: open @ 00010ff0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int open(char *__file,int __oflag,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_open_0001d300)(__file);
  return iVar1;
}



/* Function: mprotect @ 00010ffc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int mprotect(void *__addr,size_t __len,int __prot)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_mprotect_0001d304)(__addr);
  return iVar1;
}



/* Function: fcntl @ 00011008 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fcntl(int __fd,int __cmd,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fcntl_0001d308)(__fd);
  return iVar1;
}



/* Function: pthread_join @ 00011014 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_join(pthread_t __th,void **__thread_return)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_join_0001d30c)(__th);
  return iVar1;
}



/* Function: pthread_cond_timedwait @ 00011020 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_timedwait(pthread_cond_t *__cond,pthread_mutex_t *__mutex,timespec *__abstime)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_timedwait_0001d310)(__cond);
  return iVar1;
}



/* Function: __register_frame_info @ 0001102c */

void __register_frame_info(void)

{
  (*(code *)PTR___register_frame_info_0001d314)();
  return;
}



/* Function: close @ 00011038 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int close(int __fd)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_close_0001d318)(__fd);
  return iVar1;
}



/* Function: free @ 00011044 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void free(void *__ptr)

{
  (*(code *)PTR_free_0001d31c)(__ptr);
  return;
}



/* Function: entry @ 00011050 */

void processEntry entry(void)

{
  code *pcVar1;
  
  if ((uint)((DAT_0001108c + 0x11063) - (DAT_00011090 + 0x11068)) < 7) {
    return;
  }
  pcVar1 = *(code **)(DAT_00011094 + 0x11074 + DAT_00011098);
  if (pcVar1 == (code *)0x0) {
    return;
  }
  (*pcVar1)();
  return;
}



/* Function: _FINI_0 @ 000110ec */

void _FINI_0(void)

{
  int iVar1;
  
  iVar1 = DAT_00011160 + 0x11108;
  if (*(char *)(DAT_0001115c + 0x110fc) != '\0') {
    return;
  }
  if (*(int *)(iVar1 + DAT_00011164) != 0) {
    __cxa_finalize(*(undefined4 *)(DAT_00011168 + 0x11128));
  }
  entry();
  if (*(int *)(iVar1 + DAT_0001116c) != 0) {
    __deregister_frame_info(DAT_00011170 + 0x11148);
  }
  *(undefined1 *)(DAT_00011174 + 0x11158) = 1;
  return;
}



/* Function: _INIT_0 @ 00011178 */

void _INIT_0(void)

{
  code *pcVar1;
  int iVar2;
  
  iVar2 = DAT_000111dc + 0x1118c;
  if (*(int *)(iVar2 + DAT_000111e0) != 0) {
    __register_frame_info(DAT_000111e4 + 0x111a4,DAT_000111e8 + 0x111a8);
  }
  if ((*(int *)(DAT_000111ec + 0x111b4) != 0) &&
     (pcVar1 = *(code **)(iVar2 + DAT_000111f0), pcVar1 != (code *)0x0)) {
    (*pcVar1)();
  }
  if (((DAT_000110dc + 0x110ac) - (DAT_000110e0 + 0x110b4) >> 2) / 2 == 0) {
    return;
  }
  pcVar1 = *(code **)(DAT_000110e4 + 0x110bc + DAT_000110e8);
  if (pcVar1 == (code *)0x0) {
    return;
  }
  (*pcVar1)();
  return;
}



/* Function: FUN_0001142c @ 0001142c */

bool FUN_0001142c(int param_1,undefined4 param_2,void *param_3,size_t param_4)

{
  bool bVar1;
  int iVar2;
  code *pcVar3;
  undefined1 local_78;
  undefined1 local_77;
  undefined1 auStack_76 [98];
  
  pcVar3 = *(code **)(DAT_000114e4 + 0x11440);
  if (pcVar3 == (code *)0x0) {
    return false;
  }
  iVar2 = *(int *)(DAT_000114e8 + 0x11454);
  if (iVar2 == 0 || param_1 == 0) {
    bVar1 = false;
  }
  else {
    bVar1 = false;
    if (*(char *)(param_1 + 4) == '\0') {
      bVar1 = false;
    }
    else if (param_4 + 2 < 0x61) {
      local_77 = (undefined1)param_2;
      local_78 = (undefined1)((uint)param_2 >> 8);
      if (param_4 != 0) {
        memcpy(auStack_76,param_3,param_4);
      }
      iVar2 = (*pcVar3)(iVar2,*(undefined1 *)(param_1 + 5),&local_78,param_4 + 2);
      bVar1 = iVar2 == 0;
    }
  }
  return bVar1;
}



/* Function: FUN_000114ec @ 000114ec */

bool FUN_000114ec(int param_1,int param_2)

{
  int local_c [2];
  
  memcpy(local_c,(void *)(param_1 + (param_2 + 0x40) * 4),4);
  return local_c[0] == 0;
}



/* Function: FUN_0001151c @ 0001151c */

undefined4 FUN_0001151c(undefined4 param_1,int param_2)

{
  undefined4 uVar1;
  code *pcVar2;
  bool bVar3;
  uint local_18;
  undefined4 local_14;
  
  local_18 = 0;
  local_14 = 1;
  if (param_2 != 0) {
    pcVar2 = *(code **)(DAT_000115b4 + 0x11544);
    if (pcVar2 == (code *)0x0) {
      uVar1 = 0;
    }
    else {
      memcpy(&local_18,(void *)(param_2 + 0x20),4);
      memcpy(&local_14,(void *)(param_2 + 0x2c),4);
      bVar3 = false;
      if ((local_18 & 4) != 0) {
        bVar3 = *(char *)(param_2 + 0x30) != '\0';
      }
      (*pcVar2)(param_1,local_14,bVar3);
      uVar1 = 1;
    }
    return uVar1;
  }
  return 0;
}



/* Function: FUN_000115b8 @ 000115b8 */

undefined4 FUN_000115b8(undefined4 param_1,int param_2,int param_3)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 *local_34;
  undefined4 local_30;
  undefined4 local_2c;
  undefined1 auStack_28 [16];
  
  if (3 < param_2 - 1U) {
    param_2 = 1;
  }
  local_30 = 0;
  local_2c = 0x10;
  local_34 = auStack_28;
  iVar1 = FUN_00018334(&local_34,1,param_2,0);
  if ((iVar1 != 0) && (iVar1 = FUN_00018334(&local_34,2,param_3 != 0,0), iVar1 != 0)) {
    uVar2 = FUN_0001142c(param_1,0x8008,auStack_28,local_30);
    return uVar2;
  }
  return 0;
}



/* Function: FUN_00011654 @ 00011654 */

void FUN_00011654(int param_1)

{
  int iVar1;
  undefined4 *puVar2;
  int *piVar3;
  pthread_mutex_t *ppVar4;
  undefined4 *puVar5;
  int iVar6;
  undefined4 *puVar7;
  undefined4 *puVar8;
  undefined4 *puVar9;
  undefined4 *puVar10;
  
  ppVar4 = (pthread_mutex_t *)(DAT_000117a4 + 0x11668);
  pthread_mutex_lock(ppVar4);
  iVar1 = DAT_000117e0;
  piVar3 = (int *)(DAT_000117ac + 0x11680);
  iVar6 = *(int *)(DAT_000117a8 + 0x1167c);
  if (iVar6 == 0) {
    *piVar3 = 0;
    *(undefined4 *)(iVar1 + 0x1179c) = 0;
    pthread_mutex_unlock(ppVar4);
    return;
  }
  if (*piVar3 - 2U < 2) {
    *piVar3 = 5;
    pthread_mutex_unlock(ppVar4);
    if (*(code **)(DAT_000117dc + 0x11774) != (code *)0x0) {
      (**(code **)(DAT_000117dc + 0x11774))(iVar6);
    }
  }
  else {
    pthread_mutex_unlock(ppVar4);
  }
  ppVar4 = (pthread_mutex_t *)(DAT_000117b0 + 0x116ac);
  pthread_mutex_lock(ppVar4);
  *(undefined4 *)(DAT_000117b4 + 0x116c4) = 0;
  iVar1 = DAT_000117d4;
  if (param_1 != 0) {
    puVar8 = (undefined4 *)(DAT_000117bc + 0x116e8);
    puVar7 = (undefined4 *)(DAT_000117c0 + 0x116f0);
    puVar5 = (undefined4 *)(DAT_000117c4 + 0x116f8);
    puVar10 = (undefined4 *)(DAT_000117c8 + 0x11700);
    puVar9 = (undefined4 *)(DAT_000117cc + 0x11708);
    puVar2 = (undefined4 *)(DAT_000117d0 + 0x1170c);
    *(undefined4 *)(DAT_000117b8 + 0x116e0) = 0;
    *puVar8 = 0;
    *puVar7 = 0;
    *puVar5 = 0;
    *puVar10 = 0;
    *puVar9 = 0;
    *puVar2 = 0;
    *(undefined4 *)(iVar1 + 0x11714) = 0;
    pthread_mutex_unlock(ppVar4);
    if (*(code **)(DAT_000117d8 + 0x1173c) == (code *)0x0) {
      return;
    }
                    /* WARNING: Could not recover jumptable at 0x0001174c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(DAT_000117d8 + 0x1173c))(iVar6);
    return;
  }
  pthread_mutex_unlock(ppVar4);
  return;
}



/* Function: FUN_000117e4 @ 000117e4 */

bool FUN_000117e4(int param_1)

{
  char cVar1;
  int iVar2;
  undefined4 *puVar3;
  int iVar4;
  int iVar5;
  undefined4 *puVar6;
  undefined4 *puVar7;
  undefined1 auStack_50 [68];
  
  cVar1 = *(char *)(param_1 + 4);
  iVar4 = DAT_000118b8 + 0x1180c;
  iVar5 = DAT_000118bc + 0x11814;
  *(undefined1 *)(param_1 + 4) = 0;
  *(undefined1 *)(param_1 + 5) = 0xff;
  iVar2 = DAT_000118d8;
  if (param_1 != iVar5 && param_1 != iVar4) {
    if (param_1 == DAT_000118c0 + 0x1183c) {
      *(undefined4 *)(param_1 + 0x18) = 0;
    }
    else {
      iVar4 = *(int *)(DAT_000118c4 + 0x11854);
      puVar6 = (undefined4 *)(DAT_000118cc + 0x11868);
      puVar3 = (undefined4 *)(DAT_000118d0 + 0x11870);
      puVar7 = (undefined4 *)(DAT_000118d4 + 0x1187c);
      *(undefined4 *)(DAT_000118c8 + 0x11860) = 0;
      *puVar6 = 0;
      *(undefined4 *)(param_1 + 0x18) = 0;
      *puVar3 = 0;
      *puVar7 = 0;
      *(undefined4 *)(iVar2 + 0x11884) = 0;
      if (iVar4 != 0) {
        FUN_00011654(0);
      }
      FUN_0001a934(auStack_50);
    }
  }
  return cVar1 != '\0';
}



/* Function: FUN_00011990 @ 00011990 */

void FUN_00011990(void)

{
  pthread_mutex_t *__mutex;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_000119c8 + 0x119a0));
  __mutex = (pthread_mutex_t *)(DAT_000119d0 + 0x119b4);
  if (*(int *)(DAT_000119cc + 0x119b0) == 5) {
    *(int *)(DAT_000119cc + 0x119b0) = 0;
  }
  pthread_mutex_unlock(__mutex);
  return;
}



/* Function: FUN_00011acc @ 00011acc */

void FUN_00011acc(undefined4 param_1,undefined4 param_2,void *param_3,int param_4)

{
  int iVar1;
  
  if ((param_4 == 6 && param_3 != (void *)0x0) &&
     (iVar1 = memcmp(param_3,(void *)(DAT_00011b64 + 0x11b0c),6), iVar1 == 0)) {
    memcpy(param_3,(void *)(DAT_00011b68 + 0x11b28),6);
    if (*(int *)(DAT_00011b6c + 0x11b34) == 0) {
      *(int *)(DAT_00011b6c + 0x11b34) = 1;
    }
  }
                    /* WARNING: Could not recover jumptable at 0x00011b60. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_00011b70 + 0x11b58))(param_1,param_2,param_3,param_4);
  return;
}



/* Function: FUN_00011d38 @ 00011d38 */

undefined4 FUN_00011d38(undefined4 param_1,uint param_2,undefined1 *param_3)

{
  char *__nptr;
  int iVar1;
  ulong uVar2;
  undefined1 uVar3;
  uint uVar4;
  uint uVar5;
  char *local_1c [2];
  
  __nptr = getenv((char *)(DAT_00011e38 + 0x11d58));
  local_1c[0] = (char *)0x0;
  if ((__nptr == (char *)0x0) || (*__nptr == '\0')) {
    if (param_2 - 0x10 < 0xef) {
      uVar3 = (undefined1)param_2;
      iVar1 = FUN_000114ec(param_1,param_2 & 0xff);
      if (iVar1 != 0) {
LAB_00011e28:
        *param_3 = uVar3;
        return 1;
      }
    }
    uVar4 = 0x10;
    do {
      uVar5 = uVar4 + 1;
      iVar1 = FUN_000114ec(param_1,uVar4 & 0xff);
      if (iVar1 != 0) {
        *param_3 = (char)uVar4;
        return 1;
      }
      uVar4 = uVar5;
    } while (uVar5 != 0xff);
  }
  else {
    uVar2 = strtoul(__nptr,local_1c,10);
    if ((local_1c[0] != __nptr) && ((*local_1c[0] == '\0' && (uVar2 - 0x10 < 0xef)))) {
      uVar3 = (undefined1)uVar2;
      iVar1 = FUN_000114ec(param_1,uVar2 & 0xff);
      if (iVar1 != 0) goto LAB_00011e28;
    }
  }
  return 0;
}



/* Function: FUN_00011e3c @ 00011e3c */

undefined4 FUN_00011e3c(int *param_1,undefined4 param_2)

{
  uint __n;
  uint uVar1;
  undefined4 uVar2;
  uint __size;
  void *__ptr;
  undefined1 auStack_114 [124];
  undefined1 auStack_98 [128];
  
  if (((param_1 == (int *)0x0) || (*param_1 == 0)) || ((uint)param_1[3] < (uint)param_1[2])) {
    return 0;
  }
  uVar2 = 0x10002;
  if (*(int *)(DAT_00011f40 + 0x11e74) != 2) {
    uVar2 = 0;
  }
  FUN_00018ff8(1,*(int *)(DAT_00011f40 + 0x11e74),1,uVar2,0,auStack_114);
  __n = FUN_00018b20(param_2,auStack_114,auStack_98,0x80);
  if ((__n != 0) && (uVar1 = param_1[3], __n <= ~uVar1)) {
    __size = __n + uVar1;
    __ptr = (void *)*param_1;
    if ((uint)param_1[1] < __size) {
      __ptr = realloc(__ptr,__size);
      if (__ptr == (void *)0x0) {
        return 0;
      }
      uVar1 = param_1[3];
      param_1[1] = __size;
    }
    memcpy((void *)((int)__ptr + uVar1),auStack_98,__n);
    *param_1 = (int)__ptr;
    param_1[3] = __size;
    return 1;
  }
  return 0;
}



/* Function: FUN_00011f44 @ 00011f44 */

undefined4 FUN_00011f44(int *param_1,undefined4 param_2)

{
  uint __n;
  uint uVar1;
  uint __size;
  void *__ptr;
  undefined1 auStack_114 [124];
  undefined1 auStack_98 [128];
  
  if (((param_1 != (int *)0x0) && (*param_1 != 0)) && ((uint)param_1[2] <= (uint)param_1[3])) {
    FUN_00018ff8(0,0,0,0,0,auStack_114);
    __n = FUN_00018b20(param_2,auStack_114,auStack_98,0x80);
    if ((__n != 0) && (uVar1 = param_1[3], __n <= ~uVar1)) {
      __size = __n + uVar1;
      __ptr = (void *)*param_1;
      if ((uint)param_1[1] < __size) {
        __ptr = realloc(__ptr,__size);
        if (__ptr == (void *)0x0) {
          return 0;
        }
        uVar1 = param_1[3];
        param_1[1] = __size;
      }
      memcpy((void *)((int)__ptr + uVar1),auStack_98,__n);
      *param_1 = (int)__ptr;
      param_1[3] = __size;
      return 1;
    }
    return 0;
  }
  return 0;
}



/* Function: FUN_000120b0 @ 000120b0 */

void FUN_000120b0(int param_1)

{
  undefined4 local_20;
  undefined4 local_1c;
  undefined4 local_18;
  undefined4 local_14;
  undefined4 local_10;
  undefined4 local_c;
  
  local_20 = 0;
  local_1c = 0;
  local_18 = 0;
  local_14 = 0;
  local_10 = 0;
  local_c = 0;
  if (param_1 != 0) {
    memcpy(&local_20,(void *)(param_1 + 0x26c),0x18);
  }
  return;
}



/* Function: FUN_00012188 @ 00012188 */

void FUN_00012188(void *param_1)

{
  undefined4 local_20;
  undefined4 local_1c;
  undefined4 local_18;
  undefined4 local_14;
  undefined4 local_10;
  
  local_20 = 0;
  local_1c = 0;
  local_18 = 0;
  local_14 = 0;
  local_10 = 0;
  if (param_1 != (void *)0x0) {
    memcpy(&local_20,param_1,0x18);
  }
  return;
}



/* Function: FUN_000121c8 @ 000121c8 */

undefined4 FUN_000121c8(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  undefined4 uVar1;
  
  if (*(code **)(DAT_00012200 + 0x121dc) == (code *)0x0) {
    uVar1 = 0xffffffff;
  }
  else {
    uVar1 = (**(code **)(DAT_00012200 + 0x121dc))();
  }
  FUN_00012188(param_3);
  return uVar1;
}



/* Function: FUN_00012204 @ 00012204 */

undefined4 FUN_00012204(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  undefined4 uVar1;
  
  if (*(code **)(DAT_0001223c + 0x12218) == (code *)0x0) {
    uVar1 = 0xffffffff;
  }
  else {
    uVar1 = (**(code **)(DAT_0001223c + 0x12218))();
  }
  FUN_00012188(param_3);
  return uVar1;
}



/* Function: FUN_00012240 @ 00012240 */

void FUN_00012240(int param_1,uint *param_2)

{
  size_t __maxlen;
  uint uVar1;
  char *__format;
  int iVar2;
  byte *pbVar3;
  byte *pbVar4;
  undefined4 local_5c;
  undefined4 local_58;
  undefined1 local_54;
  char acStack_23 [7];
  
  local_5c = 0;
  local_58 = 0;
  local_54 = 0;
  if (param_2 == (uint *)0x0) {
    if (param_1 == 0) {
      return;
    }
    uVar1 = 0;
    pbVar3 = (byte *)0x0;
  }
  else {
    uVar1 = *param_2;
    pbVar3 = (byte *)param_2[1];
    if (param_1 == 0) goto LAB_00012290;
  }
  memcpy(&local_5c,(void *)(param_1 + 0x50),4);
  memcpy(&local_58,(void *)(param_1 + 0x7c),4);
LAB_00012290:
  if (pbVar3 != (byte *)0x0) {
    if (0xf < uVar1) {
      uVar1 = 0x10;
    }
    if (uVar1 != 0) {
      __format = (char *)(DAT_00012338 + 0x122c0);
      iVar2 = DAT_0001233c + 0x122c8;
      __maxlen = 0x31;
      pbVar4 = pbVar3;
      while( true ) {
        if (pbVar4 + 1 == pbVar3 + uVar1) break;
        snprintf(acStack_23 + -__maxlen,__maxlen,__format,(uint)*pbVar4,iVar2);
        __maxlen = __maxlen - 3;
        pbVar4 = pbVar4 + 1;
      }
      snprintf(acStack_23 + -__maxlen,__maxlen,(char *)(DAT_00012344 + 0x12318),(uint)*pbVar4,
               DAT_00012340 + 0x12310);
    }
  }
  return;
}



/* Function: FUN_00012348 @ 00012348 */

void FUN_00012348(int param_1)

{
  pthread_mutex_t *__mutex;
  int iVar1;
  size_t __n;
  size_t local_418;
  undefined1 *local_414;
  undefined1 auStack_410 [1024];
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_0001244c + 0x12364));
  if ((*(int *)(DAT_00012450 + 0x12370) == 0) ||
     ((param_1 == 0 && (*(int *)(DAT_00012454 + 0x1238c) != 3)))) {
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012458 + 0x123a0));
  }
  else {
    __n = *(size_t *)(DAT_0001245c + 0x123c0);
    iVar1 = *(int *)(DAT_00012460 + 0x123c8);
    memcpy(auStack_410,(void *)(DAT_00012464 + 0x123d4),__n);
    __mutex = (pthread_mutex_t *)(DAT_0001246c + 0x123f4);
    *(undefined4 *)(DAT_00012468 + 0x123f0) = 0;
    pthread_mutex_unlock(__mutex);
    if (iVar1 != 0 && __n != 0) {
      local_418 = __n;
      local_414 = auStack_410;
      if (*(code **)(DAT_00012470 + 0x12414) != (code *)0x0) {
        local_414 = auStack_410;
        (**(code **)(DAT_00012470 + 0x12414))(iVar1,&local_418);
      }
      FUN_00012240(iVar1,&local_418);
      return;
    }
  }
  return;
}



/* Function: FUN_00012474 @ 00012474 */

undefined4 FUN_00012474(void)

{
  int iVar1;
  
  iVar1 = DAT_000124bc;
  usleep(750000);
  FUN_00012348(1);
  pthread_mutex_lock((pthread_mutex_t *)(iVar1 + 0x12494));
  *(undefined4 *)(DAT_000124c0 + 0x124b0) = 0;
  pthread_mutex_unlock((pthread_mutex_t *)(iVar1 + 0x12494));
  return 0;
}



/* Function: FUN_000124c4 @ 000124c4 */

bool FUN_000124c4(int param_1)

{
  undefined4 uVar1;
  int iVar2;
  size_t __n;
  pthread_mutex_t *__mutex;
  int *piVar3;
  int *piVar4;
  bool bVar5;
  size_t local_428;
  undefined1 *puStack_424;
  undefined1 auStack_420 [1024];
  
  __mutex = (pthread_mutex_t *)(DAT_00012658 + 0x124e4);
  piVar3 = (int *)(DAT_0001265c + 0x124e8);
  pthread_mutex_lock(__mutex);
  if (((param_1 != *piVar3) || (piVar4 = (int *)(DAT_00012668 + 0x12538), *piVar4 != 2)) ||
     (__n = *(size_t *)(DAT_0001266c + 0x1254c), __n == 0)) {
    iVar2 = *(int *)(DAT_00012660 + 0x12508);
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012664 + 0x1250c));
    return iVar2 == 3;
  }
  memcpy(auStack_420,(void *)(DAT_00012670 + 0x12564),__n);
  pthread_mutex_unlock(__mutex);
  local_428 = __n;
  if (*(code **)(DAT_00012674 + 0x12580) == (code *)0x0) {
    puStack_424 = auStack_420;
    FUN_00012240(param_1,&local_428);
    pthread_mutex_lock(__mutex);
    iVar2 = 0;
    if ((param_1 != *piVar3) || (*piVar4 != 2)) {
      pthread_mutex_unlock(__mutex);
      return false;
    }
    uVar1 = 4;
  }
  else {
    puStack_424 = auStack_420;
    iVar2 = (**(code **)(DAT_00012674 + 0x12580))(param_1,&local_428);
    FUN_00012240(param_1,&local_428);
    pthread_mutex_lock(__mutex);
    if ((param_1 != *piVar3) || (*piVar4 != 2)) {
      bVar5 = false;
      if (iVar2 != 0) {
        bVar5 = true;
      }
      goto LAB_000125c4;
    }
    if (iVar2 == 0) {
      uVar1 = 4;
    }
    else {
      uVar1 = 3;
    }
  }
  bVar5 = iVar2 != 0;
  *(undefined4 *)(DAT_0001267c + 0x12638) = uVar1;
LAB_000125c4:
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012678 + 0x125d0));
  if (iVar2 != 0) {
    FUN_00012348(0);
  }
  return bVar5;
}



/* Function: FUN_00012680 @ 00012680 */

void FUN_00012680(char *param_1,int param_2,uint param_3,undefined4 param_4)

{
  int __fd;
  ssize_t sVar1;
  uint uVar2;
  
  __fd = open(param_1,0x301,0x1a4,param_4,param_4);
  if (__fd < 0) {
    return;
  }
  if (param_3 != 0) {
    uVar2 = 0;
    do {
      sVar1 = write(__fd,(void *)(param_2 + uVar2),param_3 - uVar2);
      uVar2 = uVar2 + sVar1;
      if (sVar1 < 1) break;
    } while (uVar2 < param_3);
  }
  close(__fd);
  return;
}



/* Function: FUN_000126e0 @ 000126e0 */

undefined4 FUN_000126e0(int param_1,__off_t param_2,int param_3,uint param_4)

{
  __off_t _Var1;
  ssize_t sVar2;
  uint uVar3;
  
  _Var1 = lseek(param_1,param_2,0);
  if (_Var1 != param_2) {
    return 0;
  }
  if (param_4 != 0) {
    uVar3 = 0;
    do {
      sVar2 = read(param_1,(void *)(param_3 + uVar3),param_4 - uVar3);
      uVar3 = uVar3 + sVar2;
      if (sVar2 < 1) {
        return 0;
      }
    } while (uVar3 < param_4);
  }
  return 1;
}



/* Function: FUN_00012744 @ 00012744 */

int FUN_00012744(undefined4 *param_1,char *param_2,int *param_3)

{
  uint *puVar1;
  int iVar2;
  int iVar3;
  void *__ptr;
  void *__ptr_00;
  int *__ptr_01;
  undefined4 uVar4;
  int iVar5;
  int iVar6;
  uint __size;
  uint uVar7;
  int iVar8;
  int iVar9;
  uint uVar10;
  uint uVar11;
  int *piVar12;
  uint uVar13;
  size_t __size_00;
  void *pvVar14;
  void *__ptr_02;
  uint uVar15;
  uint local_8c;
  undefined1 auStack_5c [4];
  char local_58;
  short local_4c;
  short local_4a;
  int local_3c;
  short local_2e;
  ushort local_2c;
  
  if (param_1 == (undefined4 *)0x0) {
    return 0;
  }
  if ((char *)*param_1 == (char *)0x0) {
    return 0;
  }
  if (param_3 == (int *)0x0 || param_2 == (char *)0x0) {
    return 0;
  }
  iVar2 = open((char *)*param_1,0);
  if (iVar2 < 0) {
    iVar2 = strcmp((char *)*param_1,(char *)(DAT_00012b4c + 0x12968));
    if (iVar2 != 0) {
      return 0;
    }
    iVar2 = open((char *)(DAT_00012b50 + 0x1297c),0);
    if (iVar2 < 0) {
      return 0;
    }
  }
  iVar3 = FUN_000126e0(iVar2,0,auStack_5c,0x34);
  if ((((iVar3 != 0) && (iVar3 = memcmp(auStack_5c,(void *)(DAT_00012b48 + 0x127ec),4), iVar3 == 0))
      && (local_58 == '\x01')) &&
     (((local_4a == 0x28 && (local_3c != 0)) && ((local_2c - 1 < 0x1000 && (local_2e == 0x28)))))) {
    __size_00 = (uint)local_2c * 0x28;
    __ptr_02 = malloc(__size_00);
    if (__ptr_02 != (void *)0x0) {
      iVar3 = FUN_000126e0(iVar2,local_3c,__ptr_02,__size_00);
      if (iVar3 == 0) {
        iVar3 = 0;
      }
      else {
        uVar7 = (uint)local_2c;
        iVar3 = 0;
        pvVar14 = __ptr_02;
        for (local_8c = 0; iVar3 == 0 && local_8c < uVar7; local_8c = local_8c + 1) {
          if (((*(int *)((int)pvVar14 + 4) == 4 || *(int *)((int)pvVar14 + 4) == 9) &&
              (*(int *)((int)pvVar14 + 0x24) != 0)) &&
             ((((uVar10 = *(uint *)((int)pvVar14 + 0x18), uVar10 < uVar7 &&
                (((uVar15 = *(uint *)((int)pvVar14 + 0x14), uVar15 < 0x1000001 &&
                  (iVar3 = *(int *)((int)__ptr_02 + uVar10 * 0x28 + 4), iVar3 == 2 || iVar3 == 0xb))
                 && (*(int *)((int)__ptr_02 + uVar10 * 0x28 + 0x24) != 0)))) &&
               (((uVar11 = *(uint *)((int)__ptr_02 + uVar10 * 0x28 + 0x18), uVar11 < uVar7 &&
                 (__size = *(uint *)((int)__ptr_02 + uVar10 * 0x28 + 0x14), __size < 0x1000001)) &&
                (*(int *)((int)__ptr_02 + uVar11 * 0x28 + 4) == 3)))) &&
              (uVar13 = *(uint *)((int)__ptr_02 + uVar11 * 0x28 + 0x14), uVar13 < 0x1000001)))) {
            __ptr = malloc(__size);
            __ptr_00 = malloc(uVar13 + 1);
            __ptr_01 = malloc(uVar15);
            if (((__ptr_01 == (int *)0x0 || (__ptr == (void *)0x0 || __ptr_00 == (void *)0x0)) ||
                (iVar3 = FUN_000126e0(iVar2,*(undefined4 *)((int)__ptr_02 + uVar10 * 0x28 + 0x10),
                                      __ptr,__size), iVar3 == 0)) ||
               ((iVar3 = FUN_000126e0(iVar2,*(undefined4 *)((int)__ptr_02 + uVar11 * 0x28 + 0x10),
                                      __ptr_00,*(undefined4 *)((int)__ptr_02 + uVar11 * 0x28 + 0x14)
                                     ), iVar3 == 0 ||
                (iVar3 = FUN_000126e0(iVar2,*(undefined4 *)((int)pvVar14 + 0x10),__ptr_01,
                                      *(undefined4 *)((int)pvVar14 + 0x14)), iVar3 == 0)))) {
              iVar3 = 0;
            }
            else {
              uVar7 = *(uint *)((int)__ptr_02 + uVar11 * 0x28 + 0x14);
              iVar8 = *(int *)((int)pvVar14 + 0x24);
              uVar4 = *(undefined4 *)((int)pvVar14 + 0x14);
              *(undefined1 *)((int)__ptr_00 + uVar7) = 0;
              iVar5 = FUN_0001b140(uVar4,iVar8);
              iVar3 = 0;
              piVar12 = __ptr_01;
              do {
                if (iVar3 == iVar5) {
                  iVar3 = 0;
                  goto LAB_00012a08;
                }
                puVar1 = (uint *)(piVar12 + 1);
                iVar3 = iVar3 + 1;
                iVar9 = *piVar12;
                piVar12 = (int *)((int)piVar12 + iVar8);
              } while (((((*puVar1 & 0xff) != 0x16) ||
                        (uVar15 = *puVar1 >> 8,
                        *(uint *)((int)__ptr_02 + uVar10 * 0x28 + 0x14) >> 4 <= uVar15)) ||
                       (uVar15 = *(uint *)((int)__ptr + uVar15 * 0x10), uVar7 <= uVar15)) ||
                      (iVar6 = strcmp((char *)((int)__ptr_00 + uVar15),param_2), iVar6 != 0));
              iVar3 = 1;
              if (local_4c != 2) {
                iVar9 = param_1[1] + iVar9;
              }
              *param_3 = iVar9;
            }
LAB_00012a08:
            free(__ptr);
            free(__ptr_00);
            free(__ptr_01);
            uVar7 = (uint)local_2c;
          }
          else {
            iVar3 = 0;
          }
          pvVar14 = (void *)((int)pvVar14 + 0x28);
        }
      }
      goto LAB_000127c0;
    }
  }
  iVar3 = 0;
  __ptr_02 = (void *)0x0;
LAB_000127c0:
  free(__ptr_02);
  close(iVar2);
  return iVar3;
}



/* Function: FUN_00013310 @ 00013310 */

undefined4 FUN_00013310(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 *local_34;
  undefined4 local_30;
  undefined4 local_2c;
  undefined1 auStack_28 [24];
  
  local_30 = 0;
  local_2c = 0x18;
  local_34 = auStack_28;
  iVar1 = FUN_00018334(&local_34,1,param_2,0);
  if ((iVar1 != 0) && (iVar1 = FUN_00018334(&local_34,2,1,0), iVar1 != 0)) {
    uVar2 = FUN_0001142c(param_1,0x8004,auStack_28,local_30);
    return uVar2;
  }
  return 0;
}



/* Function: FUN_00013398 @ 00013398 */

void FUN_00013398(int param_1)

{
  int *piVar1;
  int iVar2;
  ssize_t sVar3;
  int iVar4;
  uint *puVar5;
  uint uVar6;
  uint uVar7;
  undefined1 auStack_a8 [56];
  byte local_70 [80];
  
  local_70[0] = 0x4c;
  iVar2 = open((char *)(DAT_000134e4 + 0x133b8),0);
  if (-1 < iVar2) {
    sVar3 = read(iVar2,local_70,1);
    close(iVar2);
    if (sVar3 != 1) {
      return;
    }
    uVar6 = (uint)local_70[0];
    if (uVar6 != 0x4c && (uVar6 & 0xef) != 0x43) {
      return;
    }
    uVar7 = *(uint *)(DAT_000134e8 + 0x13414);
    if (uVar6 == uVar7 && param_1 == 0) {
      return;
    }
    FUN_00018f90(uVar6,1,auStack_a8);
    iVar2 = FUN_00018aa8(auStack_a8,local_70,0x50);
    if (iVar2 == 0 || param_1 != 0) {
      if (iVar2 == 0) goto LAB_00013484;
    }
    else if (uVar7 != 0) {
      FUN_0001aac4(uVar6);
    }
    iVar4 = FUN_0001142c(DAT_000134ec + 0x1347c,0x8009,local_70,iVar2);
    iVar2 = DAT_000134f4;
    if (iVar4 == 0) {
LAB_00013484:
      iVar2 = DAT_000134f0;
      uVar6 = *(uint *)(DAT_000134f0 + 0x13490);
      piVar1 = (int *)(DAT_000134f0 + 0x13494);
      *(uint *)(DAT_000134f0 + 0x13490) = uVar6 + 1;
      *(uint *)(iVar2 + 0x13494) = *piVar1 + (uint)(0xfffffffe < uVar6);
      return;
    }
    puVar5 = (uint *)(DAT_000134f4 + 0x134cc);
    uVar7 = *puVar5;
    iVar4 = *(int *)(DAT_000134f4 + 0x134d0);
    *(uint *)(DAT_000134f8 + 0x134d0) = uVar6;
    *puVar5 = uVar7 + 1;
    *(uint *)(iVar2 + 0x134d0) = iVar4 + (uint)(0xfffffffe < uVar7);
  }
  return;
}



/* Function: FUN_00013a60 @ 00013a60 */

/* WARNING: Type propagation algorithm not settling */

undefined4 FUN_00013a60(void)

{
  int iVar1;
  int *__s;
  int iVar2;
  undefined4 uVar3;
  int iVar4;
  undefined4 *puVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  undefined4 *puVar9;
  int iVar10;
  int *piVar11;
  undefined4 uVar12;
  pthread_mutex_t *ppVar13;
  undefined4 *puVar14;
  int *piVar15;
  undefined4 *puVar16;
  undefined4 uVar17;
  undefined4 uVar18;
  int local_ac [15];
  undefined8 local_70;
  undefined4 local_68;
  undefined4 local_64;
  undefined4 local_60;
  undefined4 local_5c;
  
  uVar3 = 0;
  if (*(int *)(DAT_00013d9c + 0x13a74) != 0) {
    piVar11 = (int *)(DAT_00013da0 + 0x13a84);
    uVar3 = 0;
    if ((*piVar11 != 0) && (uVar3 = 0, *(int *)(DAT_00013da4 + 0x13a98) != 0)) {
      ppVar13 = (pthread_mutex_t *)(DAT_00013da8 + 0x13aac);
      piVar15 = (int *)(DAT_00013dac + 0x13ab0);
      pthread_mutex_lock(ppVar13);
      iVar8 = DAT_00013dfc;
      iVar10 = DAT_00013db4;
      iVar7 = *piVar15;
      if (iVar7 - 2U < 2) {
        uVar12 = *(undefined4 *)(DAT_00013e00 + 0x13d0c);
        pthread_mutex_unlock(ppVar13);
        if (iVar7 == 2) {
          uVar3 = 1;
          FUN_000124c4(uVar12);
        }
        else {
          uVar3 = 1;
        }
      }
      else {
        iVar1 = iVar7;
        if (iVar7 != 1) {
          iVar1 = iVar7 + -4;
        }
        if (iVar1 == 0 || (iVar7 == 1 || iVar7 + -4 == 1)) {
          pthread_mutex_unlock(ppVar13);
          return 0;
        }
        if (*(int *)(DAT_00013db0 + 0x13ae8) == 0) {
          uVar6 = *(uint *)(DAT_00013dfc + 0x13cd0);
          piVar11 = (int *)(DAT_00013dfc + 0x13cd4);
          *(uint *)(DAT_00013dfc + 0x13cd0) = uVar6 + 1;
          *(uint *)(iVar8 + 0x13cd4) = *piVar11 + (uint)(0xfffffffe < uVar6);
          pthread_mutex_unlock(ppVar13);
          uVar3 = 0;
        }
        else {
          __s = local_ac + 1;
          memset(__s,0,0x80);
          memcpy(__s,(void *)(DAT_00013db8 + 0x13b18),0x58);
          iVar8 = *(int *)(iVar10 + 0x13b1c);
          local_ac[6] = 0;
          local_ac[7] = 0;
          if (iVar8 != 0) {
            piVar11 = local_ac;
          }
          local_ac[0xc] = 0;
          local_ac[0xd] = 0;
          local_60 = 0;
          local_5c = 0;
          local_ac[1] = 800;
          local_ac[4] = 800;
          local_70 = 0x3ff0000000000000;
          local_ac[8] = 800;
          local_ac[10] = 800;
          local_68 = 800;
          local_ac[2] = 0x1e0;
          local_ac[5] = 0x1e0;
          local_ac[9] = 0x1e0;
          local_ac[0xb] = 0x1e0;
          local_64 = 0x1e0;
          local_ac[3] = 0x1e;
          if (iVar8 == 0) {
            iVar8 = (*(code *)*piVar11)(DAT_00013e04 + 0x13d40,3);
            if (iVar8 == 0) {
              *piVar15 = 4;
              pthread_mutex_unlock(ppVar13);
              return 0;
            }
            *(int *)(iVar10 + 0x13b1c) = iVar8;
            FUN_000120b0();
            piVar11 = local_ac;
            local_ac[0] = 3;
            memcpy((void *)(iVar8 + 0x280),piVar11,4);
            *(undefined1 *)(iVar8 + 0x27c) = 0;
            FUN_000120b0(iVar8);
          }
          ppVar13 = (pthread_mutex_t *)(DAT_00013dc0 + 0x13b9c);
          *(undefined4 *)(DAT_00013dbc + 0x13b98) = 1;
          pthread_mutex_unlock(ppVar13);
          (**(code **)(DAT_00013dc4 + 0x13bb4))(iVar8,__s);
          memcpy(piVar11,(void *)(iVar8 + 0x50),4);
          if ((local_ac[0] != 0) &&
             (piVar11 = (int *)(DAT_00013dc8 + 0x13be0), local_ac[0] != *piVar11)) {
            uVar3 = *(undefined4 *)(local_ac[0] + 0x14);
            puVar5 = (undefined4 *)(DAT_00013dd0 + 0x13c04);
            puVar16 = (undefined4 *)(DAT_00013dd4 + 0x13c14);
            uVar18 = *(undefined4 *)(local_ac[0] + 0x18);
            puVar14 = (undefined4 *)(DAT_00013dd8 + 0x13c1c);
            uVar17 = *(undefined4 *)(local_ac[0] + 0x1c);
            puVar9 = (undefined4 *)(DAT_00013ddc + 0x13c24);
            *(undefined4 *)(DAT_00013dcc + 0x13bfc) = *(undefined4 *)(local_ac[0] + 0x28);
            uVar12 = *(undefined4 *)(local_ac[0] + 0x2c);
            *puVar5 = uVar3;
            iVar7 = DAT_00013de4;
            iVar10 = DAT_00013de0;
            *piVar11 = local_ac[0];
            iVar1 = DAT_00013df0;
            iVar2 = DAT_00013de8 + 0x13c54;
            iVar4 = DAT_00013dec + 0x13c58;
            *puVar16 = uVar18;
            *puVar14 = uVar17;
            *puVar9 = uVar12;
            *(int *)(local_ac[0] + 0x14) = iVar10 + 0x13c40;
            *(int *)(local_ac[0] + 0x18) = iVar7 + 0x13c48;
            *(int *)(local_ac[0] + 0x1c) = iVar2;
            *(int *)(local_ac[0] + 0x28) = iVar4;
            *(int *)(local_ac[0] + 0x2c) = iVar1 + 0x13c60;
          }
          ppVar13 = (pthread_mutex_t *)(DAT_00013df4 + 0x13c84);
          pthread_mutex_lock(ppVar13);
          iVar10 = *(int *)(DAT_00013df8 + 0x13c98);
          pthread_mutex_unlock(ppVar13);
          if (iVar10 == 2) {
            FUN_000124c4(iVar8);
          }
          if (iVar10 - 2U < 2) {
            uVar3 = 1;
          }
          else {
            uVar3 = 0;
          }
        }
      }
    }
  }
  return uVar3;
}



/* Function: FUN_00014018 @ 00014018 */

undefined4 FUN_00014018(int param_1,undefined4 param_2)

{
  int iVar1;
  int iVar2;
  int iVar3;
  undefined4 *puVar4;
  int iVar5;
  undefined4 *puVar6;
  undefined4 *puVar7;
  undefined4 *puVar8;
  undefined4 *puVar9;
  
  iVar5 = DAT_000140d8;
  *(undefined4 *)(param_1 + 0x18) = param_2;
  iVar3 = DAT_000140fc;
  iVar2 = DAT_000140f8;
  iVar1 = DAT_000140f4;
  if (param_1 != iVar5 + 0x1402c) {
    iVar5 = *(int *)(DAT_000140dc + 0x14048);
    puVar9 = (undefined4 *)(DAT_000140e4 + 0x1405c);
    puVar7 = (undefined4 *)(DAT_000140e8 + 0x14064);
    puVar8 = (undefined4 *)(DAT_000140ec + 0x14074);
    puVar4 = (undefined4 *)(DAT_000140f0 + 0x14078);
    puVar6 = (undefined4 *)(DAT_000140f4 + 0x14080);
    *(undefined4 *)(DAT_000140e0 + 0x14054) = 0;
    *puVar7 = 0;
    *puVar9 = 0;
    *puVar8 = 0;
    *puVar4 = param_2;
    *puVar6 = 0;
    *(undefined4 *)(iVar1 + 0x14084) = 0;
    if (iVar5 != 0) {
      FUN_00013a60();
    }
    FUN_00013398(1);
    return 0;
  }
  puVar4 = (undefined4 *)(DAT_000140fc + 0x140cc);
  *(undefined4 *)(DAT_000140f8 + 0x140c8) = 0;
  *(undefined4 *)(iVar2 + 0x140cc) = 0;
  *puVar4 = 0;
  *(undefined4 *)(iVar3 + 0x140d0) = 0;
  return 0;
}



/* Function: FUN_00014228 @ 00014228 */

void FUN_00014228(int param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,
                 undefined4 *param_5,uint param_6)

{
  bool bVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  pthread_mutex_t *__mutex;
  int iVar5;
  int *piVar6;
  undefined4 *puVar7;
  uint *puVar8;
  int *piVar9;
  uint uVar10;
  bool bVar11;
  code *pcVar12;
  undefined4 local_48 [2];
  undefined4 local_40;
  undefined4 uStack_3c;
  undefined4 local_38;
  int *local_34;
  int local_30;
  int local_2c;
  
  iVar2 = DAT_00014668;
  if (param_5 == (undefined4 *)0x0) {
    return;
  }
  piVar9 = (int *)param_5[1];
  if (piVar9 == (int *)0x0) {
    return;
  }
  if (*piVar9 == 0) {
    return;
  }
  if ((uint)piVar9[3] < (uint)piVar9[2]) {
    return;
  }
  if ((uint)(piVar9[3] - piVar9[2]) < param_6) {
    return;
  }
  if (param_1 == DAT_00014634 + 0x1428c) {
    uVar10 = *(uint *)(DAT_00014668 + 0x14428);
    piVar9 = (int *)(DAT_00014668 + 0x1442c);
    pcVar12 = *(code **)(DAT_0001466c + 0x1442c);
    *(uint *)(DAT_00014668 + 0x14428) = uVar10 + 1;
    *(uint *)(iVar2 + 0x1442c) = *piVar9 + (uint)(0xfffffffe < uVar10);
    if ((pcVar12 != (code *)0x0) &&
       (iVar2 = (*pcVar12)(param_1,*(undefined4 *)(param_1 + 0x18),1), iVar2 == 0)) {
      return;
    }
    iVar2 = DAT_00014670;
    uVar10 = *(uint *)(DAT_00014670 + 0x14464);
    piVar9 = (int *)(DAT_00014670 + 0x14468);
    *(uint *)(DAT_00014670 + 0x14464) = uVar10 + 1;
    *(uint *)(iVar2 + 0x14468) = *piVar9 + (uint)(0xfffffffe < uVar10);
    return;
  }
  piVar6 = (int *)(DAT_00014638 + 0x1429c);
  if (*piVar6 == -1) {
    *piVar6 = 0;
  }
  else {
    *piVar6 = 0;
    FUN_00013398();
  }
  if (((*(int *)(DAT_0001463c + 0x142b8) != 0) &&
      (piVar6 = (int *)(DAT_00014640 + 0x142cc), *piVar6 != 0)) &&
     (iVar2 = FUN_0001a8c0(), iVar2 != 0)) {
    (*(code *)*piVar6)(param_1,2,1);
    (*(code *)*piVar6)(param_1,1,1);
  }
  iVar2 = (piVar9[3] - piVar9[2]) - param_6;
  if (iVar2 == 0) {
    bVar11 = true;
    goto LAB_00014328;
  }
  if (*(int *)(DAT_00014644 + 0x14308) == 0) {
LAB_00014310:
    bVar11 = true;
  }
  else {
    piVar6 = (int *)param_5[1];
    uVar4 = *(undefined4 *)(param_1 + 0x18);
    if ((piVar6 == (int *)0x0) || (*piVar6 == 0)) goto LAB_00014310;
    if (((uint)piVar6[3] < (uint)piVar6[2]) ||
       (((uint)(piVar6[3] - piVar6[2]) < param_6 || (iVar3 = FUN_00013a60(), iVar3 == 0))))
    goto LAB_00014310;
    __mutex = (pthread_mutex_t *)(DAT_00014674 + 0x144f0);
    pthread_mutex_lock(__mutex);
    iVar5 = *(int *)(DAT_00014678 + 0x1450c);
    iVar3 = *(int *)(DAT_0001467c + 0x14510);
    pthread_mutex_unlock(__mutex);
    if (iVar3 != 3 || iVar5 == 0) goto LAB_00014310;
    memset(local_48,0,0x20);
    local_38 = *param_5;
    local_2c = (piVar6[3] - piVar6[2]) - param_6;
    local_30 = *piVar6 + param_6 + piVar6[2];
    local_48[0] = uVar4;
    local_40 = param_3;
    uStack_3c = param_4;
    local_34 = piVar6;
    if ((*(code **)(DAT_00014680 + 0x1456c) == (code *)0x0) ||
       (iVar5 = (**(code **)(DAT_00014680 + 0x1456c))(iVar5,local_48), iVar3 = DAT_00014684,
       iVar5 == 0)) {
      iVar3 = DAT_00014688;
      iVar5 = 0;
      uVar10 = *(uint *)(DAT_00014688 + 0x14624);
      piVar6 = (int *)(DAT_00014688 + 0x14628);
      *(uint *)(DAT_00014688 + 0x14624) = uVar10 + 1;
      *(uint *)(iVar3 + 0x14628) = *piVar6 + (uint)(0xfffffffe < uVar10);
    }
    else {
      uVar10 = *(uint *)(DAT_00014684 + 0x145d0);
      piVar6 = (int *)(DAT_00014684 + 0x145d4);
      *(uint *)(DAT_00014684 + 0x145d0) = uVar10 + 1;
      *(uint *)(iVar3 + 0x145d4) = *piVar6 + (uint)(0xfffffffe < uVar10);
    }
    bVar11 = iVar5 == 0;
  }
  if (*(int *)(DAT_00014648 + 0x14320) != 0) {
    FUN_0001a298(*piVar9 + param_6 + piVar9[2],iVar2,0,param_6 + piVar9[2],param_3,param_4);
  }
LAB_00014328:
  uVar4 = *(undefined4 *)(param_1 + 0x18);
  piVar9 = (int *)(DAT_00014650 + 0x14340);
  pcVar12 = *(code **)(DAT_0001464c + 0x14338);
  puVar7 = (undefined4 *)(DAT_00014654 + 0x14354);
  *piVar9 = *piVar9 + 1;
  *puVar7 = uVar4;
  if ((pcVar12 == (code *)0x0) || (iVar2 = (*pcVar12)(param_1,uVar4,1), iVar2 != 0)) {
    iVar2 = DAT_00014664;
    DataMemoryBarrier(0xf);
    puVar8 = (uint *)(DAT_00014664 + 0x143f8);
    do {
      uVar10 = *puVar8;
      piVar9 = (int *)(DAT_00014664 + 0x143fc);
      bVar1 = (bool)hasExclusiveAccess(puVar8);
    } while (!bVar1);
    *puVar8 = uVar10 + 1;
    *(uint *)(iVar2 + 0x143fc) = *piVar9 + (uint)(0xfffffffe < uVar10);
    DataMemoryBarrier(0xf);
  }
  else {
    *(int *)(DAT_00014658 + 0x14384) = *piVar9;
  }
  iVar2 = DAT_00014660;
  if (*(int *)(DAT_0001465c + 0x14390) == 0) {
    bVar11 = false;
  }
  if (bVar11) {
    uVar10 = *(uint *)(DAT_00014660 + 0x143b0);
    piVar9 = (int *)(DAT_00014660 + 0x143b4);
    *(uint *)(DAT_00014660 + 0x143b0) = uVar10 + 1;
    *(uint *)(iVar2 + 0x143b4) = *piVar9 + (uint)(0xfffffffe < uVar10);
  }
  return;
}



/* Function: _INIT_1 @ 0001468c */

void _INIT_1(void)

{
  int iVar1;
  int iVar2;
  char *pcVar3;
  ssize_t sVar4;
  int iVar5;
  size_t sVar6;
  int iVar7;
  int iVar8;
  uint *puVar9;
  undefined4 uVar10;
  int iVar11;
  uint *puVar12;
  int iVar13;
  undefined4 uVar14;
  uint *puVar15;
  undefined4 *puVar16;
  undefined4 *puVar17;
  undefined4 uVar18;
  void *__src;
  undefined4 *puVar19;
  int *piVar20;
  void *pvVar21;
  int *piVar22;
  int iVar23;
  void *__src_00;
  uint uVar24;
  int *__s;
  uint uVar25;
  int iVar26;
  void *__src_01;
  int iVar27;
  void *__src_02;
  void *__src_03;
  bool bVar28;
  int *local_98;
  int *local_94;
  int *local_90;
  int *local_8c;
  int local_88;
  int local_84;
  int local_80;
  int local_7c;
  int local_78;
  int local_74;
  int local_68;
  int local_64;
  
  iVar2 = FUN_0001b138();
  if (iVar2 == 0) {
    return;
  }
  puVar17 = (undefined4 *)(DAT_00015678 + 0x146c8);
  pcVar3 = (char *)(DAT_0001567c + 0x146d0);
  *(undefined4 *)(DAT_00015674 + 0x146c4) = 1;
  *puVar17 = 0;
  iVar2 = open(pcVar3,0);
  if (-1 < iVar2) {
    sVar4 = read(iVar2,&local_68,0x3f);
    close(iVar2);
    if (0 < sVar4) {
      *(undefined1 *)((int)&local_68 + sVar4) = 0;
      iVar2 = strncmp((char *)&local_68,(char *)(DAT_00015680 + 0x1471c),3);
      if (iVar2 == 0) {
        *(undefined4 *)(DAT_00015684 + 0x14738) = 0;
      }
      else {
        iVar2 = strncmp((char *)&local_68,(char *)(DAT_000157fc + 0x150bc),8);
        if (iVar2 == 0) {
          *(undefined4 *)(DAT_0001580c + 0x15114) = 1;
        }
        else {
          iVar2 = strncmp((char *)&local_68,(char *)(DAT_00015800 + 0x150d8),8);
          if (iVar2 == 0) {
            *(undefined4 *)(DAT_00015804 + 0x150f0) = 2;
          }
          else {
            iVar2 = strncmp((char *)&local_68,(char *)(DAT_00015814 + 0x15170),0xd);
            if (iVar2 == 0) {
              *(undefined4 *)(DAT_00015818 + 0x15184) = 3;
            }
          }
        }
      }
    }
  }
  pcVar3 = getenv((char *)(DAT_00015688 + 0x14744));
  if (((pcVar3 == (char *)0x0) || (*pcVar3 == '\0')) ||
     (iVar2 = strcmp(pcVar3,(char *)(DAT_0001568c + 0x14764)), iVar2 == 0)) {
LAB_000147c8:
    *(undefined4 *)(DAT_000156a0 + 0x147d8) = 2;
  }
  else {
    iVar2 = strcmp(pcVar3,(char *)(DAT_00015690 + 0x1477c));
    if (iVar2 == 0) {
      *(undefined4 *)(DAT_00015808 + 0x15104) = 1;
    }
    else {
      iVar2 = strcmp(pcVar3,(char *)(DAT_00015694 + 0x14794));
      if (iVar2 == 0) goto LAB_000147c8;
      iVar2 = strcmp(pcVar3,(char *)(DAT_00015698 + 0x147ac));
      if (iVar2 == 0) {
        *(undefined4 *)(DAT_00015810 + 0x15158) = 3;
      }
      else {
        *(undefined4 *)(DAT_0001569c + 0x147c4) = 0;
      }
    }
  }
  iVar8 = DAT_000156b0;
  iVar7 = DAT_000156ac;
  iVar11 = DAT_000156a8;
  iVar2 = DAT_000156a4;
  __s = (int *)(DAT_000156a4 + 0x147f0);
  piVar22 = (int *)(DAT_000156a8 + 0x147f8);
  piVar20 = (int *)(DAT_000156ac + 0x14800);
  memset(__s,0,0x40);
  iVar23 = DAT_000156b4;
  memset(piVar22,0,0x20);
  memset(piVar20,0,0x40);
  memset((int *)(iVar8 + 0x1480c),0,0x20);
  iVar26 = DAT_000156c0;
  iVar5 = DAT_000156bc;
  *(int *)(iVar2 + 0x14808) = DAT_000156b8 + 0x14850;
  iVar1 = DAT_000156cc;
  iVar27 = DAT_000156c8;
  iVar13 = DAT_000156c4 + 0x14874;
  *(int *)(iVar2 + 0x147f8) = iVar23 + 0x1485c;
  *(int *)(iVar2 + 0x147fc) = iVar5 + 0x1486c;
  *(int *)(iVar2 + 0x14800) = iVar26 + 0x14864;
  *(int *)(iVar2 + 0x14804) = iVar13;
  *(int *)(iVar2 + 0x1480c) = iVar27 + 0x1487c;
  *__s = iVar1 + 0x14884;
  *(int *)(iVar2 + 0x147f4) = iVar1 + 0x14884;
  uVar10 = *(undefined4 *)(iVar2 + 0x147f4);
  uVar14 = *(undefined4 *)(iVar2 + 0x147f8);
  uVar18 = *(undefined4 *)(iVar2 + 0x147fc);
  iVar23 = DAT_000156d0 + 0x148b4;
  *piVar22 = *__s;
  *(undefined4 *)(iVar11 + 0x147fc) = uVar10;
  *(undefined4 *)(iVar11 + 0x14800) = uVar14;
  *(undefined4 *)(iVar11 + 0x14804) = uVar18;
  uVar10 = *(undefined4 *)(iVar2 + 0x14804);
  uVar14 = *(undefined4 *)(iVar2 + 0x14808);
  uVar18 = *(undefined4 *)(iVar2 + 0x1480c);
  *(undefined4 *)(iVar11 + 0x14808) = *(undefined4 *)(iVar2 + 0x14800);
  *(undefined4 *)(iVar11 + 0x1480c) = uVar10;
  *(undefined4 *)(iVar11 + 0x14810) = uVar14;
  *(undefined4 *)(iVar11 + 0x14814) = uVar18;
  uVar10 = *(undefined4 *)(iVar2 + 0x147f4);
  uVar14 = *(undefined4 *)(iVar2 + 0x147f8);
  uVar18 = *(undefined4 *)(iVar2 + 0x147fc);
  *piVar20 = *__s;
  *(undefined4 *)(iVar7 + 0x14804) = uVar10;
  *(undefined4 *)(iVar7 + 0x14808) = uVar14;
  *(undefined4 *)(iVar7 + 0x1480c) = uVar18;
  uVar10 = *(undefined4 *)(iVar2 + 0x14804);
  uVar14 = *(undefined4 *)(iVar2 + 0x14808);
  uVar18 = *(undefined4 *)(iVar2 + 0x1480c);
  *(undefined4 *)(iVar7 + 0x14810) = *(undefined4 *)(iVar2 + 0x14800);
  *(undefined4 *)(iVar7 + 0x14814) = uVar10;
  *(undefined4 *)(iVar7 + 0x14818) = uVar14;
  *(undefined4 *)(iVar7 + 0x1481c) = uVar18;
  uVar10 = *(undefined4 *)(iVar2 + 0x14814);
  uVar14 = *(undefined4 *)(iVar2 + 0x14818);
  uVar18 = *(undefined4 *)(iVar2 + 0x1481c);
  *(undefined4 *)(iVar7 + 84000) = *(undefined4 *)(iVar2 + 0x14810);
  *(undefined4 *)(iVar7 + 0x14824) = uVar10;
  *(undefined4 *)(iVar7 + 0x14828) = uVar14;
  *(undefined4 *)(iVar7 + 0x1482c) = uVar18;
  uVar10 = *(undefined4 *)(iVar2 + 0x14824);
  uVar14 = *(undefined4 *)(iVar2 + 0x14828);
  uVar18 = *(undefined4 *)(iVar2 + 0x1482c);
  *(undefined4 *)(iVar7 + 0x14830) = *(undefined4 *)(iVar2 + 84000);
  *(undefined4 *)(iVar7 + 0x14834) = uVar10;
  *(undefined4 *)(iVar7 + 0x14838) = uVar14;
  *(undefined4 *)(iVar7 + 0x1483c) = uVar18;
  uVar10 = *(undefined4 *)(iVar11 + 0x147fc);
  uVar14 = *(undefined4 *)(iVar11 + 0x14800);
  uVar18 = *(undefined4 *)(iVar11 + 0x14804);
  *(int *)(iVar8 + 0x1480c) = *piVar22;
  *(undefined4 *)(iVar8 + 0x14810) = uVar10;
  *(undefined4 *)(iVar8 + 0x14814) = uVar14;
  *(undefined4 *)(iVar8 + 0x14818) = uVar18;
  uVar10 = *(undefined4 *)(iVar11 + 0x1480c);
  uVar14 = *(undefined4 *)(iVar11 + 0x14810);
  uVar18 = *(undefined4 *)(iVar11 + 0x14814);
  *(undefined4 *)(iVar8 + 0x1481c) = *(undefined4 *)(iVar11 + 0x14808);
  *(undefined4 *)(iVar8 + 84000) = uVar10;
  *(undefined4 *)(iVar8 + 0x14824) = uVar14;
  *(undefined4 *)(iVar8 + 0x14828) = uVar18;
  unlink((char *)(DAT_000156d4 + 0x148f8));
  unlink((char *)(DAT_000156d8 + 0x14904));
  FUN_00017d54(iVar23);
  FUN_0001909c(iVar23);
  if (*(int *)(DAT_000156dc + 0x14920) == 2) {
LAB_00014994:
    uVar10 = FUN_00019f08(0x3cc3,2,DAT_000156e8 + 0x149a8);
    *(undefined4 *)(DAT_000156ec + 0x149b4) = uVar10;
  }
  else {
    memset(&local_78,0,0x10);
    memset(&local_68,0,0x10);
    iVar2 = dladdr(&DAT_001e668c,&local_78);
    if (((((iVar2 == 0) ||
          (iVar8 = dladdr(&DAT_00251e18,&local_68), iVar7 = DAT_000157e8, iVar11 = DAT_000157e4,
          iVar2 = DAT_000157d4, iVar8 == 0)) ||
         ((local_74 != local_64 || ((DAT_001e668c != -0x16d2bf90 || (DAT_00251e20 != 0x1ef140))))))
        || (DAT_00251e24 != 0x1ef050)) ||
       (((((DAT_00251e28 != 0x1ef3a4 || (DAT_00251e2c != 0x1eed20)) || (DAT_00251e30 != 0x1eebd0))
         || ((DAT_00251e34 != 0x1eeac8 || (DAT_00251e38 != 0x1ee78c)))) ||
        (DAT_00251e3c != 0x1ee7a0)))) {
LAB_00014968:
      if ((*(int *)(DAT_000156e0 + 0x14974) - 2U < 2) || (*(int *)(DAT_000156e4 + 0x1498c) == 0))
      goto LAB_00014994;
    }
    else {
      puVar17 = (undefined4 *)(DAT_000157d0 + 0x14fc8);
      piVar20 = (int *)(DAT_000157d4 + 0x14fcc);
      iVar23 = DAT_000157dc + 0x14fe0;
      iVar8 = DAT_000157e0 + 0x14fe8;
      *(int **)(DAT_000157d8 + 0x14fd4) = piVar20;
      *puVar17 = &DAT_001e668c;
      *(int *)(iVar2 + 0x14fd4) = iVar23;
      *(int *)(iVar2 + 0x14fd8) = iVar8;
      *piVar20 = iVar11 + 86000;
      *(int *)(iVar2 + 0x14fd0) = iVar11 + 86000;
      iVar11 = DAT_000157f0;
      iVar2 = DAT_000157ec;
      *(int *)(iVar7 + 0x14ff8) = DAT_00251e28;
      iVar23 = DAT_000157f8;
      iVar8 = DAT_000157f4;
      *(int *)(iVar2 + 0x15028) = DAT_00251e2c;
      *(int *)(iVar11 + 0x15030) = DAT_00251e30;
      *(int *)(iVar8 + 0x15040) = DAT_00251e34;
      *(int *)(iVar23 + 0x15048) = DAT_00251e24;
      sVar6 = sysconf(0xb);
      if ((int)sVar6 < 1) {
        sVar6 = 0x1000;
      }
      iVar5 = mprotect((void *)(-sVar6 & 0x251e28),sVar6,0x300);
      if (iVar5 != 0) {
        *puVar17 = 0;
        *(int *)(iVar7 + 0x14ff8) = 0;
        *(int *)(iVar2 + 0x15028) = 0;
        *(int *)(iVar11 + 0x15030) = 0;
        *(int *)(iVar8 + 0x15040) = 0;
        *(int *)(iVar23 + 0x15048) = 0;
        goto LAB_00014968;
      }
      DAT_00251e28 = DAT_000158d8 + 0x15914;
      DAT_00251e2c = DAT_000158dc + 0x1591c;
      DataMemoryBarrier(0xf);
      iVar2 = *(int *)(DAT_000158d4 + 0x15910);
      puVar17 = (undefined4 *)(DAT_000158e4 + 0x1594c);
      puVar16 = (undefined4 *)(DAT_000158e8 + 0x15950);
      puVar19 = (undefined4 *)(DAT_000158ec + 0x15954);
      *(int **)(DAT_000158e0 + 0x15948) = &DAT_00251e2c;
      *puVar17 = &DAT_00251e28;
      *puVar16 = 1;
      *puVar19 = 1;
      if (iVar2 - 2U < 2) goto LAB_00014994;
    }
  }
  pcVar3 = getenv((char *)(DAT_000156f0 + 0x149c0));
  bVar28 = false;
  if (*(int *)(DAT_000156f4 + 0x149cc) != 0) {
    if ((pcVar3 == (char *)0x0) || (*pcVar3 == '\0')) {
      bVar28 = true;
    }
    else {
      iVar2 = strcmp(pcVar3,(char *)(DAT_000157cc + 0x14e80));
      bVar28 = iVar2 != 0;
    }
  }
  iVar2 = DAT_000156f8 + 0x149e0;
  FUN_0001a998(bVar28,iVar2);
  FUN_0001af88(bVar28,iVar2);
  local_94 = (int *)0x0;
  local_90 = (int *)0x0;
  local_8c = (int *)0x0;
  local_84 = 0;
  local_80 = 0;
  local_7c = 0;
  local_78 = 0;
  iVar2 = dlopen(DAT_000156fc + 0x14a04,0x102);
  if (iVar2 == 0) {
    dlerror();
    return;
  }
  uVar10 = dlsym(iVar2,DAT_00015700 + 0x14a34);
  iVar11 = DAT_00015708 + 0x14a48;
  *(undefined4 *)(DAT_00015704 + 0x14a44) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015710 + 0x14a64;
  *(undefined4 *)(DAT_0001570c + 0x14a60) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015718 + 0x14a80;
  *(undefined4 *)(DAT_00015714 + 0x14a7c) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015720 + 0x14a9c;
  *(undefined4 *)(DAT_0001571c + 0x14a98) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015728 + 0x14ab8;
  *(undefined4 *)(DAT_00015724 + 0x14ab4) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015730 + 0x14ad4;
  *(undefined4 *)(DAT_0001572c + 0x14ad0) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015738 + 0x14af0;
  *(undefined4 *)(DAT_00015734 + 0x14aec) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015740 + 0x14b0c;
  *(undefined4 *)(DAT_0001573c + 0x14b08) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  iVar11 = DAT_00015748 + 0x14b28;
  *(undefined4 *)(DAT_00015744 + 0x14b24) = uVar10;
  uVar10 = dlsym(iVar2,iVar11);
  *(undefined4 *)(DAT_0001574c + 0x14b3c) = uVar10;
  pcVar3 = getenv((char *)(DAT_00015750 + 0x14b48));
  if (pcVar3 == (char *)0x0) {
    uVar25 = 1;
  }
  else {
    iVar2 = strcmp(pcVar3,(char *)(DAT_00015754 + 0x14b60));
    uVar25 = 0;
    if (iVar2 != 0) {
      uVar25 = 1;
    }
  }
  pvVar21 = *(void **)(DAT_00015758 + 0x14b78);
  *(uint *)(DAT_0001575c + 0x14b7c) = uVar25;
  if (pvVar21 == (void *)0x0) {
    return;
  }
  __src_00 = *(void **)(DAT_00015760 + 0x14b94);
  if (__src_00 == (void *)0x0) {
    return;
  }
  __src_01 = *(void **)(DAT_00015764 + 0x14ba4);
  if (__src_01 == (void *)0x0) {
    return;
  }
  __src_02 = *(void **)(DAT_00015768 + 0x14bb4);
  if (__src_02 == (void *)0x0) {
    return;
  }
  __src_03 = *(void **)(DAT_0001576c + 0x14bc4);
  if (__src_03 == (void *)0x0) {
    return;
  }
  __src = *(void **)(DAT_00015770 + 0x14bd4);
  if (__src == (void *)0x0) {
    return;
  }
  if (uVar25 != 0) {
    if (*(int *)(DAT_00015774 + 0x14bf0) == 0) {
      return;
    }
    if (*(int *)(DAT_00015778 + 0x14c04) == 0) {
      return;
    }
    if (*(int *)(DAT_0001577c + 0x14c18) == 0) {
      return;
    }
    if (*(int *)(DAT_00015780 + 0x14c2c) == 0) {
      return;
    }
    if (__src_00 == (void *)((int)pvVar21 + -0xb0)) goto LAB_00015120;
    goto LAB_00014c4c;
  }
  if (__src_00 == (void *)((int)pvVar21 + -0xb0)) {
LAB_00015120:
    if ((((__src_01 == (void *)((int)pvVar21 + -0x24)) &&
         (__src_02 == (void *)((int)pvVar21 + 0x120))) &&
        (__src_03 == (void *)((int)pvVar21 + -0xa434))) && (__src == (void *)((int)pvVar21 + 0xe70))
       ) {
      if (uVar25 == 0) {
        memcpy(&local_68,pvVar21,4);
        if (local_68 != -0x16d2be10) goto LAB_00014cf4;
LAB_000159b8:
        memcpy(&local_68,(void *)((int)pvVar21 + 4),4);
        if (((local_68 != -0x1e5fbffd) || (memcpy(&local_68,__src_00,4), local_68 != -0x1a2ecff4))
           || ((memcpy(&local_68,__src_01,4), local_68 != -0x1ad21ffc ||
               ((memcpy(&local_68,__src_02,4), local_68 != -0x1ad21ffc ||
                (memcpy(&local_68,__src_03,4), local_68 != -0x1e5fcfff)))))) goto LAB_00015138;
        memcpy(&local_68,__src,4);
        uVar24 = (uint)(local_68 == -0x1a6ccffc);
        goto LAB_0001513c;
      }
      if ((*(int *)(DAT_00015870 + 0x15488) == (int)pvVar21 + -0xa50) &&
         (*(int *)(DAT_00015874 + 0x154a0) == (int)pvVar21 + -0xb3c)) {
        if (*(int *)(DAT_00015878 + 0x154c0) == (int)pvVar21 + -0x688) {
          if (*(int *)(DAT_000158f0 + 0x15980) != (int)pvVar21 + 0xdba8) goto LAB_00014c4c;
          memcpy(&local_68,pvVar21,4);
          if (local_68 == -0x16d2be10) goto LAB_000159b8;
        }
        uVar24 = 0;
      }
      else {
LAB_00014c4c:
        uVar24 = 0;
      }
    }
    else {
LAB_00015138:
      uVar24 = 0;
LAB_0001513c:
      bVar28 = uVar25 == 0;
      uVar25 = uVar24;
      if (bVar28) goto LAB_00014cf4;
    }
    iVar7 = DAT_00015790;
    iVar11 = DAT_0001578c;
    iVar2 = DAT_00015784;
    puVar17 = (undefined4 *)(DAT_00015784 + 0x14c64);
    *(int *)(DAT_00015784 + 0x14c9c) = DAT_00015788 + 0x14c6c;
    iVar23 = DAT_000157a8;
    iVar8 = DAT_000157a4;
    iVar27 = DAT_00015794 + 0x14c90;
    iVar26 = DAT_00015798 + 0x14c98;
    iVar5 = DAT_000157a0 + 0x14ca8;
    *(int *)(iVar2 + 0x14c90) = DAT_0001579c + 0x14ca0;
    *(int *)(iVar2 + 0x14c78) = iVar11 + 0x14c80;
    *(int *)(iVar2 + 0x14c84) = iVar7 + 0x14c88;
    *(int *)(iVar2 + 0x14c88) = iVar27;
    *(int *)(iVar2 + 0x14c8c) = iVar26;
    *(int *)(iVar2 + 0x14c94) = iVar5;
    *(int *)(iVar2 + 0x14c98) = iVar8 + 0x14cb0;
    *(int *)(iVar2 + 0x14ca0) = iVar23 + 0x14cb8;
    iVar11 = DAT_000157ac;
    uVar10 = *(undefined4 *)(iVar2 + 0x14c68);
    uVar14 = *(undefined4 *)(iVar2 + 0x14c6c);
    uVar18 = *(undefined4 *)(iVar2 + 0x14c70);
    *(undefined4 *)(DAT_000157ac + 0x14cdc) = *puVar17;
    *(undefined4 *)(iVar11 + 0x14ce0) = uVar10;
    *(undefined4 *)(iVar11 + 0x14ce4) = uVar14;
    *(undefined4 *)(iVar11 + 0x14ce8) = uVar18;
    uVar10 = *(undefined4 *)(iVar2 + 0x14c78);
    uVar14 = *(undefined4 *)(iVar2 + 0x14c7c);
    uVar18 = *(undefined4 *)(iVar2 + 0x14c80);
    *(undefined4 *)(iVar11 + 0x14cec) = *(undefined4 *)(iVar2 + 0x14c74);
    *(undefined4 *)(iVar11 + 0x14cf0) = uVar10;
    *(undefined4 *)(iVar11 + 0x14cf4) = uVar14;
    *(undefined4 *)(iVar11 + 0x14cf8) = uVar18;
    uVar10 = *(undefined4 *)(iVar2 + 0x14c88);
    uVar14 = *(undefined4 *)(iVar2 + 0x14c8c);
    uVar18 = *(undefined4 *)(iVar2 + 0x14c90);
    *(undefined4 *)(iVar11 + 0x14cfc) = *(undefined4 *)(iVar2 + 0x14c84);
    *(undefined4 *)(iVar11 + 0x14d00) = uVar10;
    *(undefined4 *)(iVar11 + 0x14d04) = uVar14;
    *(undefined4 *)(iVar11 + 0x14d08) = uVar18;
    uVar10 = *(undefined4 *)(iVar2 + 0x14c98);
    uVar14 = *(undefined4 *)(iVar2 + 0x14c9c);
    uVar18 = *(undefined4 *)(iVar2 + 0x14ca0);
    *(undefined4 *)(iVar11 + 0x14d0c) = *(undefined4 *)(iVar2 + 0x14c94);
    *(undefined4 *)(iVar11 + 0x14d10) = uVar10;
    *(undefined4 *)(iVar11 + 0x14d14) = uVar14;
    *(undefined4 *)(iVar11 + 0x14d18) = uVar18;
    uVar25 = uVar24;
  }
LAB_00014cf4:
  memset(&local_68,0,0x10);
  iVar2 = dladdr(pvVar21,&local_68);
  if (iVar2 == 0) {
    return;
  }
  iVar2 = FUN_00012744(&local_68,DAT_000157b0 + 0x14d2c,&local_98);
  if (iVar2 == 0) {
    return;
  }
  if (uVar25 == 0) {
    memcpy(&local_88,local_98,4);
    if (local_88 != *(int *)(DAT_000157b4 + 0x14d5c) && local_88 != DAT_000157b8 + 0x14d64) {
      return;
    }
    if (local_88 == DAT_000157b8 + 0x14d64) {
LAB_000153fc:
      iVar2 = DAT_0001586c;
      puVar9 = (uint *)(DAT_00015860 + 0x1541c);
      puVar12 = (uint *)(DAT_00015864 + 0x15424);
      puVar15 = (uint *)(DAT_00015868 + 0x15428);
      *(undefined4 *)(DAT_0001585c + 0x15414) = 1;
      *puVar9 = uVar25;
      *puVar12 = uVar25;
      *puVar15 = uVar25;
      *(uint *)(iVar2 + 0x15430) = uVar25;
      return;
    }
    piVar20 = (int *)0x0;
    iVar2 = local_88;
  }
  else {
    iVar2 = FUN_00012744(&local_68,DAT_0001581c + 0x1519c,&local_94);
    if (iVar2 == 0) {
      return;
    }
    iVar2 = FUN_00012744(&local_68,DAT_00015820 + 0x151b8,&local_90);
    if (iVar2 == 0) {
      return;
    }
    iVar2 = FUN_00012744(&local_68,DAT_00015824 + 0x151d4,&local_8c);
    if (iVar2 == 0) {
      return;
    }
    if ((int *)((int)pvVar21 + 0x170d8) != local_98) {
      return;
    }
    if ((int *)((int)pvVar21 + 0x16d28) != local_94) {
      return;
    }
    if ((int *)((int)pvVar21 + 0x16a1c) != local_90) {
      return;
    }
    if ((int *)((int)pvVar21 + 0x161b8) != local_8c) {
      return;
    }
    iVar2 = *(int *)(DAT_00015828 + 0x15250);
    if (*(int *)((int)pvVar21 + 0x15cc4) != iVar2) {
      return;
    }
    memcpy(&local_88,(int *)((int)pvVar21 + 0x170d8),4);
    memcpy(&local_84,(int *)((int)pvVar21 + 0x16d28),4);
    piVar20 = (int *)((int)pvVar21 + 0x15cc4);
    memcpy(&local_80,(int *)((int)pvVar21 + 0x16a1c),4);
    memcpy(&local_7c,(int *)((int)pvVar21 + 0x161b8),4);
    memcpy(&local_78,piVar20,4);
    if ((local_88 != DAT_00015830 + 0x152d0 && local_88 != *(int *)(DAT_0001582c + 0x152c8)) &&
       (local_88 != (int)pvVar21 + -0x8e93c)) {
      return;
    }
    if ((local_84 != DAT_00015838 + 0x15314 && local_84 != *(int *)(DAT_00015834 + 0x1530c)) &&
       (local_84 != (int)pvVar21 + -0x8e93c)) {
      return;
    }
    if ((local_80 != *(int *)(DAT_0001583c + 0x15344) && local_80 != DAT_00015840 + 0x1534c) &&
       (local_80 != (int)pvVar21 + -0x8e93c)) {
      return;
    }
    if ((local_7c != *(int *)(DAT_00015844 + 0x1537c) && local_7c != DAT_00015848 + 0x15384) &&
       (local_7c != (int)pvVar21 + -0x8e93c)) {
      return;
    }
    if (iVar2 != local_78 && local_78 != DAT_0001584c + 0x153b4) {
      return;
    }
    iVar2 = local_88;
    if ((((local_88 == DAT_00015830 + 0x152d0) && (local_84 == DAT_00015850 + 0x153d0)) &&
        (local_80 == DAT_00015854 + 0x153e0)) &&
       ((local_7c == DAT_00015858 + 0x153f0 && (local_78 == DAT_0001584c + 0x153b4))))
    goto LAB_000153fc;
  }
  sVar6 = sysconf(0xb);
  if ((int)sVar6 < 1) {
    sVar6 = 0x1000;
  }
  uVar24 = -sVar6;
  pvVar21 = (void *)((uint)local_98 & uVar24);
  iVar11 = mprotect(pvVar21,sVar6,0x300);
  if (iVar11 != 0) {
    return;
  }
  if (uVar25 == 0) {
    if (iVar2 != DAT_0001587c + 0x154e0) {
      piVar20 = (int *)(DAT_00015880 + 0x154f4);
      *local_98 = DAT_0001587c + 0x154e0;
      *piVar20 = iVar2;
    }
  }
  else {
    if ((pvVar21 != (void *)((uint)local_8c & uVar24)) &&
       (iVar11 = mprotect((void *)((uint)local_8c & uVar24),sVar6,0x300), iVar11 != 0)) {
      return;
    }
    iVar11 = local_84;
    if (iVar2 != DAT_000157bc + 0x14dec) {
      piVar22 = (int *)(DAT_000157c0 + 0x14e00);
      *local_98 = DAT_000157bc + 0x14dec;
      *piVar22 = iVar2;
    }
    if (local_84 != DAT_000157c4 + 0x14e14) {
      if ((pvVar21 != (void *)((uint)local_94 & uVar24)) &&
         (iVar7 = mprotect((void *)((uint)local_94 & uVar24),sVar6,0x300), iVar7 != 0)) {
        if (*local_98 != DAT_000157c8 + 0x14e54) {
          return;
        }
        goto LAB_00014e58;
      }
      iVar8 = DAT_00015894;
      iVar7 = DAT_00015890;
      *local_94 = DAT_0001588c + 0x15534;
      puVar17 = (undefined4 *)(DAT_00015898 + 0x15550);
      *(int *)(iVar8 + 0x15548) = iVar11;
      *(int **)(iVar7 + 0x15540) = local_94;
      *puVar17 = 1;
    }
    iVar11 = local_80;
    if (local_80 != DAT_0001589c + 0x15568) {
      if ((pvVar21 != (void *)((uint)local_90 & uVar24)) &&
         (iVar7 = mprotect((void *)((uint)local_90 & uVar24),sVar6,0x300), iVar7 != 0)) {
        if (*local_98 != DAT_000158a0 + 0x155a8) {
          return;
        }
LAB_00014e58:
        *local_98 = iVar2;
        return;
      }
      iVar7 = DAT_000158ac;
      iVar2 = DAT_000158a8;
      *local_90 = DAT_000158a4 + 0x155c4;
      puVar17 = (undefined4 *)(DAT_000158b0 + 0x155e0);
      *(int *)(iVar7 + 0x155d8) = iVar11;
      *(int **)(iVar2 + 0x155d0) = local_90;
      *puVar17 = 1;
    }
    iVar11 = DAT_000158bc;
    iVar2 = DAT_000158b8;
    if (local_7c != DAT_000158b4 + 0x155f8) {
      *local_8c = DAT_000158b4 + 0x155f8;
      puVar17 = (undefined4 *)(DAT_000158c0 + 0x15628);
      *(int *)(iVar11 + 0x15620) = local_7c;
      *(int **)(iVar2 + 0x15618) = local_8c;
      *puVar17 = 1;
    }
    iVar2 = DAT_000158d0;
    if (local_78 != DAT_000158c4 + 0x15640) {
      puVar17 = (undefined4 *)(DAT_000158c8 + 0x1565c);
      piVar22 = (int *)(DAT_000158cc + 0x15660);
      *piVar20 = DAT_000158c4 + 0x15640;
      *puVar17 = piVar20;
      *piVar22 = local_78;
      *(undefined4 *)(iVar2 + 0x15668) = 1;
    }
  }
  DataMemoryBarrier(0xf);
  puVar17 = (undefined4 *)(DAT_00015888 + 0x15518);
  *(int **)(DAT_00015884 + 0x15514) = local_98;
  *puVar17 = 1;
  return;
}



/* Function: _FINI_1 @ 00015ad8 */

void _FINI_1(void)

{
  size_t sVar1;
  int iVar2;
  undefined4 *puVar3;
  undefined4 extraout_r1;
  undefined4 *puVar4;
  uint uVar5;
  uint *puVar6;
  int *piVar7;
  undefined4 *puVar8;
  undefined1 auStack_80 [48];
  undefined1 auStack_50 [68];
  
  if (*(int *)(DAT_00015e98 + 0x15ae8) != 0) {
    FUN_00011654(1);
  }
  if (((*(int *)(DAT_00015e9c + 0x15b08) != 0) &&
      (puVar6 = (uint *)(DAT_00015ea0 + 0x15b1c), *puVar6 != 0)) &&
     (piVar7 = (int *)(DAT_00015ea4 + 0x15b30), *piVar7 != 0)) {
    sVar1 = sysconf(0xb);
    if ((int)sVar1 < 1) {
      sVar1 = 0x1000;
    }
    iVar2 = mprotect((void *)(-sVar1 & *puVar6),sVar1,0x300);
    if (iVar2 == 0) {
      if (*(int *)*puVar6 == DAT_00015ea8 + 0x15b7c) {
        *(int *)*puVar6 = *piVar7;
      }
      piVar7 = *(int **)(DAT_00015eac + 0x15b90);
      if ((piVar7 != (int *)0x0) && (*piVar7 == DAT_00015eb0 + 0x15ba4)) {
        *piVar7 = *(int *)(DAT_00015f28 + 0x15e90);
      }
      DataMemoryBarrier(0xf);
    }
    puVar4 = (undefined4 *)(DAT_00015eb8 + 0x15bc4);
    *(undefined4 *)(DAT_00015eb4 + 0x15bc0) = 0;
    *puVar4 = 0;
  }
  sVar1 = sysconf(0xb);
  if ((int)sVar1 < 1) {
    sVar1 = 0x1000;
  }
  if (*(int *)(DAT_00015ebc + 0x15bdc) != 0) {
    puVar6 = (uint *)(DAT_00015ec0 + 0x15bfc);
    uVar5 = *puVar6;
    if (((uVar5 != 0) && (iVar2 = mprotect((void *)(-sVar1 & uVar5),sVar1,0x300), iVar2 == 0)) &&
       (piVar7 = (int *)*puVar6, *piVar7 == DAT_00015f14 + 0x15e34)) {
      *piVar7 = *(int *)(DAT_00015f18 + 0x15e40);
    }
  }
  if (*(int *)(DAT_00015ec4 + 0x15c2c) != 0) {
    puVar6 = (uint *)(DAT_00015ec8 + 0x15c40);
    uVar5 = *puVar6;
    if (((uVar5 != 0) && (iVar2 = mprotect((void *)(-sVar1 & uVar5),sVar1,0x300), iVar2 == 0)) &&
       (piVar7 = (int *)*puVar6, *piVar7 == DAT_00015f04 + 0x15de4)) {
      *piVar7 = *(int *)(DAT_00015f08 + 0x15df0);
    }
  }
  if (*(int *)(DAT_00015ecc + 0x15c70) != 0) {
    puVar6 = (uint *)(DAT_00015ed0 + 0x15c84);
    uVar5 = *puVar6;
    if (((uVar5 != 0) && (iVar2 = mprotect((void *)(-sVar1 & uVar5),sVar1,0x300), iVar2 == 0)) &&
       (piVar7 = (int *)*puVar6, *piVar7 == DAT_00015f0c + 0x15e0c)) {
      *piVar7 = *(int *)(DAT_00015f10 + 0x15e18);
    }
  }
  if (*(int *)(DAT_00015ed4 + 0x15cb4) != 0) {
    puVar6 = (uint *)(DAT_00015ed8 + 0x15cc8);
    uVar5 = *puVar6;
    if (((uVar5 != 0) && (iVar2 = mprotect((void *)(-sVar1 & uVar5),sVar1,0x300), iVar2 == 0)) &&
       (piVar7 = (int *)*puVar6, *piVar7 == DAT_00015f1c + 0x15e5c)) {
      *piVar7 = *(int *)(DAT_00015f20 + 0x15e68);
    }
  }
  if (*(int *)(DAT_00015edc + 0x15cf8) != 0) {
    puVar6 = (uint *)(DAT_00015ee0 + 0x15d0c);
    uVar5 = *puVar6;
    if (((uVar5 != 0) && (iVar2 = mprotect((void *)(-sVar1 & uVar5),sVar1,0x300), iVar2 == 0)) &&
       (piVar7 = (int *)*puVar6, *piVar7 == DAT_00015ee4 + 0x15d44)) {
      *piVar7 = *(int *)(DAT_00015f24 + 0x15e7c);
    }
  }
  iVar2 = DAT_00015ef8;
  DataMemoryBarrier(0xf);
  puVar8 = (undefined4 *)(DAT_00015eec + 0x15d6c);
  puVar4 = (undefined4 *)(DAT_00015ef0 + 0x15d74);
  puVar3 = (undefined4 *)(DAT_00015ef4 + 0x15d78);
  *(undefined4 *)(DAT_00015ee8 + 0x15d64) = 0;
  *puVar8 = 0;
  *puVar3 = 0;
  *puVar4 = 0;
  *(undefined4 *)(iVar2 + 0x15d80) = 0;
  FUN_000190a4();
  FUN_00017e70();
  FUN_0001a1f0();
  FUN_0001ab58();
  FUN_0001b084();
  FUN_0001a934(auStack_50);
  FUN_00018224(auStack_80,extraout_r1,*(undefined4 *)(DAT_00015efc + 0x15dbc),
               *(undefined4 *)(DAT_00015f00 + 0x15dc0));
  return;
}



/* Function: FUN_00015f2c @ 00015f2c */

undefined4 FUN_00015f2c(int param_1,uint param_2,uint *param_3,uint *param_4)

{
  char cVar1;
  char *pcVar2;
  uint uVar3;
  byte *pbVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  bool bVar10;
  
  if ((param_1 != 0 && (param_4 != (uint *)0x0 && param_3 != (uint *)0x0)) && (*param_3 < param_2))
  {
    memset(param_4,0,0x18);
    param_4[2] = *param_3;
    uVar3 = *param_3;
    if (uVar3 < param_2) {
      uVar5 = uVar3 + 1;
      *param_3 = uVar5;
      pbVar4 = (byte *)(param_1 + uVar3);
      uVar8 = 0;
      uVar7 = *(byte *)(param_1 + uVar3) & 0x7f;
      if ((*(byte *)(param_1 + uVar3) & 0x80) != 0) {
        uVar3 = 0;
        do {
          uVar9 = uVar3 + 7;
          bVar10 = param_2 <= uVar5;
          uVar5 = uVar5 + 1;
          if (0x3f < uVar9 || bVar10) {
            return 0;
          }
          *param_3 = uVar5;
          pbVar4 = pbVar4 + 1;
          uVar6 = *pbVar4 & 0x7f;
          uVar7 = uVar7 | uVar6 << (uVar9 & 0xff);
          uVar8 = uVar8 | uVar6 << (uVar3 - 0x19 & 0xff) | uVar6 >> (0x20 - uVar9 & 0xff);
          uVar3 = uVar9;
        } while ((*pbVar4 & 0x80) != 0);
      }
      uVar3 = uVar7 >> 3 | uVar8 << 0x1d;
      if (uVar3 != 0 || uVar8 >> 3 != 0) {
        uVar7 = uVar7 & 7;
        *param_4 = uVar3;
        param_4[1] = uVar7;
        if (uVar7 == 0) {
          uVar3 = *param_3;
          if (uVar3 < param_2) {
            *param_3 = uVar3 + 1;
            pcVar2 = (char *)(param_1 + uVar3);
            uVar5 = 0;
            cVar1 = *(char *)(param_1 + uVar3);
            uVar3 = uVar3 + 1;
            while (cVar1 < '\0') {
              uVar5 = uVar5 + 7;
              if (0x3f < uVar5 || param_2 <= uVar3) {
                return 0;
              }
              *param_3 = uVar3 + 1;
              pcVar2 = pcVar2 + 1;
              uVar3 = uVar3 + 1;
              cVar1 = *pcVar2;
            }
LAB_000160fc:
            param_4[5] = uVar3;
            return 1;
          }
        }
        else if (uVar7 == 1) {
          if (7 < param_2 - *param_3) {
            uVar3 = *param_3 + 8;
            *param_3 = uVar3;
            goto LAB_000160fc;
          }
        }
        else if (uVar7 == 2) {
          uVar5 = *param_3;
          if (uVar5 < param_2) {
            uVar8 = uVar5 + 1;
            *param_3 = uVar8;
            pbVar4 = (byte *)(param_1 + uVar5);
            uVar3 = *(byte *)(param_1 + uVar5) & 0x7f;
            uVar7 = 0;
            if ((*(byte *)(param_1 + uVar5) & 0x80) != 0) {
              uVar7 = 0;
              uVar5 = 0;
              do {
                uVar9 = uVar5 + 7;
                bVar10 = 0x3e < uVar9;
                if (uVar9 < 0x40) {
                  bVar10 = param_2 <= uVar8;
                }
                uVar8 = uVar8 + 1;
                if (bVar10) {
                  return 0;
                }
                *param_3 = uVar8;
                pbVar4 = pbVar4 + 1;
                uVar6 = *pbVar4 & 0x7f;
                uVar3 = uVar3 | uVar6 << (uVar9 & 0xff);
                uVar7 = uVar7 | uVar6 << (uVar5 - 0x19 & 0xff) | uVar6 >> (0x20 - uVar9 & 0xff);
                uVar5 = uVar9;
              } while ((*pbVar4 & 0x80) != 0);
            }
            bVar10 = uVar7 == 0;
            if (uVar7 == 0) {
              bVar10 = uVar3 <= param_2 - uVar8;
            }
            if (bVar10) {
              param_4[3] = uVar8;
              param_4[4] = uVar3;
              uVar3 = *param_3 + uVar3;
              *param_3 = uVar3;
              goto LAB_000160fc;
            }
          }
        }
        else if ((uVar7 == 5) && (3 < param_2 - *param_3)) {
          uVar3 = *param_3 + 4;
          *param_3 = uVar3;
          goto LAB_000160fc;
        }
      }
    }
  }
  return 0;
}



/* Function: FUN_000161e8 @ 000161e8 */

bool FUN_000161e8(undefined4 param_1,uint param_2)

{
  int iVar1;
  uint uVar2;
  uint local_34;
  int local_30;
  int local_2c;
  
  uVar2 = 0;
  local_34 = 0;
  if (param_2 != 0) {
    while (((iVar1 = FUN_00015f2c(param_1,param_2,&local_34,&local_30), iVar1 != 0 &&
            (local_2c == 0)) && (local_30 - 1U < 7))) {
      if (local_30 == 2) {
        uVar2 = uVar2 | 1;
      }
      else if (local_30 == 3) {
        uVar2 = uVar2 | 2;
      }
      if (param_2 <= local_34) {
        return uVar2 == 3;
      }
    }
  }
  return false;
}



/* Function: FUN_00016294 @ 00016294 */

undefined4 FUN_00016294(int param_1,uint param_2,undefined4 *param_3)

{
  int iVar1;
  uint local_3c;
  int local_38;
  int local_34;
  int local_2c;
  undefined4 local_28;
  
  *param_3 = 0;
  local_3c = 0;
  if (param_2 != 0) {
    do {
      while( true ) {
        iVar1 = FUN_00015f2c(param_1,param_2,&local_3c,&local_38);
        if (iVar1 == 0) {
          return 0;
        }
        if ((local_38 != 1) || (local_34 != 2)) break;
        iVar1 = FUN_000161e8(param_1 + local_2c,local_28);
        if (iVar1 != 0) {
          *param_3 = 1;
        }
        if (param_2 <= local_3c) {
          return 1;
        }
      }
    } while (local_3c < param_2);
  }
  return 1;
}



/* Function: FUN_00016338 @ 00016338 */

undefined4 FUN_00016338(int *param_1,void *param_2,uint param_3)

{
  if (param_1 == (int *)0x0) {
    return 0;
  }
  if ((uint)(param_1[2] - param_1[1]) < param_3) {
    return 0;
  }
  memcpy((void *)(*param_1 + param_1[1]),param_2,param_3);
  param_1[1] = param_1[1] + param_3;
  return 1;
}



/* Function: FUN_00016388 @ 00016388 */

void FUN_00016388(undefined4 param_1,undefined4 param_2,uint param_3,uint param_4)

{
  byte *pbVar1;
  uint uVar2;
  uint uVar3;
  byte abStack_1c [12];
  
  pbVar1 = abStack_1c;
  uVar2 = 0;
  do {
    uVar3 = uVar2;
    uVar2 = param_3 >> 7 | param_4 << 0x19;
    if (uVar2 == 0 && param_4 >> 7 == 0) {
      abStack_1c[uVar3] = (byte)param_3 & 0x7f;
      break;
    }
    *pbVar1 = ~((byte)~(byte)((param_3 << 0x19) >> 0x18) >> 1);
    param_3 = uVar2;
    param_4 = param_4 >> 7;
    pbVar1 = pbVar1 + 1;
    uVar2 = uVar3 + 1;
  } while (uVar3 + 1 < 10);
  FUN_00016338(param_1,abStack_1c,uVar3 + 1);
  return;
}



/* Function: FUN_000163f8 @ 000163f8 */

bool FUN_000163f8(undefined4 param_1,uint param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  undefined8 uVar2;
  
  uVar2 = FUN_00016388(param_1,param_2,param_2 << 3,param_2 >> 0x1d,param_4);
  if ((int)uVar2 == 0) {
    return false;
  }
  iVar1 = FUN_00016388(param_1,(int)((ulonglong)uVar2 >> 0x20),param_3,0,param_4);
  return iVar1 != 0;
}



/* Function: FUN_00016434 @ 00016434 */

bool FUN_00016434(undefined4 param_1,uint param_2,int param_3,undefined4 param_4)

{
  int iVar1;
  undefined8 uVar2;
  
  uVar2 = FUN_00016388(param_1,param_2,param_2 << 3,param_2 >> 0x1d,param_4);
  if ((int)uVar2 == 0) {
    return false;
  }
  iVar1 = FUN_00016388(param_1,(int)((ulonglong)uVar2 >> 0x20),param_3,param_3 >> 0x1f,param_4);
  return iVar1 != 0;
}



/* Function: FUN_00016470 @ 00016470 */

bool FUN_00016470(undefined4 param_1,uint param_2,undefined4 *param_3,undefined4 *param_4)

{
  int iVar1;
  undefined8 uVar2;
  undefined4 *puVar3;
  
  puVar3 = param_4;
  uVar2 = FUN_00016388(param_1,param_2,param_2 << 3 | 2,param_2 >> 0x1d,param_4);
  if (((int)uVar2 != 0) &&
     (iVar1 = FUN_00016388(param_1,(int)((ulonglong)uVar2 >> 0x20),*param_4,0,puVar3), iVar1 != 0))
  {
    iVar1 = FUN_00016338(param_1,*param_3,*param_4);
    return iVar1 != 0;
  }
  return false;
}



/* Function: FUN_000164d8 @ 000164d8 */

undefined4 FUN_000164d8(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 *local_2c;
  undefined4 local_28;
  undefined4 local_24;
  undefined1 auStack_20 [20];
  
  local_2c = auStack_20;
  local_28 = 0;
  local_24 = 0x10;
  iVar1 = FUN_00016434(&local_2c,1,param_2);
  uVar2 = 0;
  if ((iVar1 != 0) && (iVar1 = FUN_00016470(param_1,2,&local_2c,&local_28), uVar2 = 0, iVar1 != 0))
  {
    uVar2 = 1;
  }
  return uVar2;
}



/* Function: FUN_0001653c @ 0001653c */

undefined4 FUN_0001653c(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 *local_2c;
  undefined4 local_28;
  undefined4 local_24;
  undefined1 auStack_20 [20];
  
  local_2c = auStack_20;
  local_28 = 0;
  local_24 = 0x10;
  iVar1 = FUN_00016434(&local_2c,1,param_2);
  uVar2 = 0;
  if ((iVar1 != 0) && (iVar1 = FUN_00016470(param_1,3,&local_2c,&local_28), uVar2 = 0, iVar1 != 0))
  {
    uVar2 = 1;
  }
  return uVar2;
}



/* Function: FUN_000165a0 @ 000165a0 */

undefined4 FUN_000165a0(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 *local_2c;
  undefined4 local_28;
  undefined4 local_24;
  undefined1 auStack_20 [20];
  
  local_2c = auStack_20;
  local_28 = 0;
  local_24 = 0x10;
  iVar1 = FUN_000163f8(&local_2c,2,param_2);
  uVar2 = 0;
  if ((iVar1 != 0) && (iVar1 = FUN_00016470(param_1,5,&local_2c,&local_28), uVar2 = 0, iVar1 != 0))
  {
    uVar2 = 1;
  }
  return uVar2;
}



/* Function: FUN_00016604 @ 00016604 */

undefined4
FUN_00016604(int param_1,uint param_2,undefined4 *param_3,int param_4,undefined4 param_5,
            undefined4 *param_6,int *param_7,int param_8)

{
  uint uVar1;
  uint uVar2;
  bool bVar3;
  int iVar4;
  int iVar5;
  uint uVar6;
  uint uVar7;
  bool bVar8;
  uint local_fc;
  int local_f8;
  uint local_f4;
  int local_f0;
  undefined4 local_ec;
  undefined4 local_e8;
  undefined1 *local_e4;
  undefined4 local_e0;
  undefined4 local_dc;
  uint local_d8;
  int local_d4;
  uint local_d0;
  int local_cc;
  uint local_c8;
  uint local_c4;
  int local_c0 [2];
  uint local_b8;
  uint local_ac;
  undefined1 auStack_a8 [132];
  
  local_e8 = param_5;
  local_ec = 0;
  local_fc = 0;
  if ((param_3 != (undefined4 *)0x0 && param_1 != 0) &&
     (param_7 != (int *)0x0 && (param_6 != (undefined4 *)0x0 && param_4 != 0))) {
    *param_7 = 0;
    local_f0 = param_4;
    iVar4 = FUN_00016294(param_1,param_2,&local_f8);
    if (iVar4 != 0) {
      if (param_8 != 0 || local_f8 != 0) {
        uVar7 = 0;
        bVar3 = false;
LAB_00016694:
        do {
          if (param_2 <= local_fc) goto LAB_00016894;
          iVar4 = FUN_00015f2c(param_1,param_2,&local_fc,&local_d8);
          if (iVar4 == 0) {
            return 0;
          }
          if (local_d8 == 1) {
            if (local_d4 == 2) {
              iVar4 = FUN_000161e8(param_1 + local_cc,local_c8);
              uVar2 = local_c8;
              if (iVar4 == 0) goto LAB_000166c8;
              local_e4 = auStack_a8;
              local_dc = 0x80;
              iVar4 = param_1 + local_cc;
              uVar6 = 0;
              local_e0 = 0;
              local_f4 = 0;
LAB_000167a8:
              if (local_f4 < uVar2) {
                iVar5 = FUN_00015f2c(iVar4,uVar2,&local_f4,local_c0);
                if (iVar5 == 0) {
                  return 0;
                }
                if (local_c0[0] - 2U < 6) goto code_r0x000167dc;
                if (local_ac < local_b8) {
                  return 0;
                }
                iVar5 = FUN_00016338(&local_e4,iVar4 + local_b8,local_ac - local_b8);
                goto LAB_000168b4;
              }
              if (((uVar6 & 1) == 0) && (iVar4 = FUN_00016434(&local_e4,2,*param_3), iVar4 == 0)) {
                return 0;
              }
              if (((uVar6 & 2) == 0) && (iVar4 = FUN_00016434(&local_e4,3,param_3[1]), iVar4 == 0))
              {
                return 0;
              }
              if (((uVar6 & 4) == 0) && (iVar4 = FUN_000163f8(&local_e4,4,param_3[2]), iVar4 == 0))
              {
                return 0;
              }
              if (((uVar6 & 8) == 0) && (iVar4 = FUN_00016434(&local_e4,5,param_3[3]), iVar4 == 0))
              {
                return 0;
              }
              if (((uVar6 & 0x10) == 0) &&
                 (iVar4 = FUN_00016434(&local_e4,6,param_3[4]), iVar4 == 0)) {
                return 0;
              }
              if (((uVar6 & 0x20) == 0) &&
                 (iVar4 = FUN_00016434(&local_e4,7,param_3[5]), iVar4 == 0)) {
                return 0;
              }
              iVar4 = FUN_00016470(&local_f0,1,&local_e4,&local_e0);
              if (iVar4 == 0) {
                return 0;
              }
              *param_7 = *param_7 + 1;
              if (!bVar3) {
                iVar4 = FUN_000164d8(&local_f0,param_3[5]);
                if (iVar4 == 0) {
                  return 0;
                }
                iVar4 = FUN_0001653c(&local_f0,param_3[4]);
                if (iVar4 == 0) {
                  return 0;
                }
                iVar4 = FUN_000165a0(&local_f0,param_3[6]);
                if (iVar4 == 0) {
                  return 0;
                }
                bVar3 = true;
              }
              goto LAB_00016694;
            }
          }
          else {
LAB_000166c8:
            if (local_f8 != 0) {
              bVar8 = 4 < local_d8;
              if (local_d8 != 5) {
                bVar8 = local_d8 != 2;
              }
              if (!bVar8 || (local_d8 == 5 || local_d8 == 3)) goto LAB_00016694;
            }
            if ((param_8 != 0) && (local_d4 == 2)) {
              bVar8 = 4 < local_d8;
              if (local_d8 != 5) {
                bVar8 = local_d8 != 2;
              }
              if (bVar8 && (local_d8 != 5 && local_d8 != 3)) {
                bVar8 = 0xd < local_d8;
                if (local_d8 != 0xe) {
                  bVar8 = 1 < local_d8 - 0x13;
                }
                if (bVar8 && (local_d8 != 0xe && local_d8 - 0x13 != 2)) goto LAB_000166fc;
              }
              else {
                uVar2 = 1 << (local_d8 & 0xff);
                if ((uVar7 & uVar2) != 0) goto LAB_00016694;
                uVar7 = uVar7 | uVar2;
                if ((local_d8 == 2) && (iVar4 = FUN_000164d8(&local_f0,param_3[5]), iVar4 == 0)) {
                  return 0;
                }
                if ((local_d8 == 3) && (iVar4 = FUN_0001653c(&local_f0,param_3[4]), iVar4 == 0)) {
                  return 0;
                }
                if ((local_d8 == 5) && (iVar4 = FUN_000165a0(&local_f0,param_3[6]), iVar4 == 0)) {
                  return 0;
                }
              }
              *param_7 = *param_7 + 1;
              goto LAB_00016694;
            }
          }
LAB_000166fc:
          if (local_c4 < local_d0) {
            return 0;
          }
          iVar4 = FUN_00016338(&local_f0,param_1 + local_d0,local_c4 - local_d0);
          if (iVar4 == 0) {
            return 0;
          }
        } while( true );
      }
      iVar4 = FUN_00016338(&local_f0,param_1,param_2);
      if (iVar4 != 0) {
LAB_00016894:
        *param_6 = local_ec;
        return 1;
      }
    }
  }
  return 0;
code_r0x000167dc:
  uVar1 = 1 << (local_c0[0] - 2U & 0xff);
  if ((uVar6 & uVar1) == 0) {
    uVar6 = uVar6 | uVar1;
    switch(local_c0[0]) {
    case 3:
      iVar5 = FUN_00016434(&local_e4,3,param_3[1]);
      break;
    case 4:
      iVar5 = FUN_000163f8(&local_e4,4,param_3[2]);
      break;
    case 5:
      iVar5 = FUN_00016434(&local_e4,5,param_3[3]);
      break;
    case 6:
      iVar5 = FUN_00016434(&local_e4,6,param_3[4]);
      break;
    case 7:
      iVar5 = FUN_00016434(&local_e4,7,param_3[5]);
      break;
    default:
      iVar5 = FUN_00016434(&local_e4,2,*param_3);
    }
LAB_000168b4:
    if (iVar5 == 0) {
      return 0;
    }
  }
  goto LAB_000167a8;
}



/* Function: FUN_00016b24 @ 00016b24 */

undefined4 FUN_00016b24(int param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 uVar2;
  int local_c [2];
  
  if (param_1 == 0) {
    return 0;
  }
  iVar1 = FUN_00016294(param_1,param_2,local_c);
  if (iVar1 == 0) {
    uVar2 = 0;
  }
  else {
    uVar2 = 0;
    if (local_c[0] != 0) {
      uVar2 = 1;
    }
  }
  return uVar2;
}



/* Function: FUN_00016b64 @ 00016b64 */

void FUN_00016b64(void)

{
  FUN_00016604();
  return;
}



/* Function: FUN_00016b94 @ 00016b94 */

undefined4 FUN_00016b94(int param_1,uint param_2,uint *param_3,uint *param_4)

{
  uint uVar1;
  uint uVar2;
  byte *pbVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  bool bVar8;
  
  memset(param_4,0,0x18);
  uVar2 = *param_3;
  if (uVar2 < param_2) {
    uVar4 = uVar2 + 1;
    *param_3 = uVar4;
    pbVar3 = (byte *)(param_1 + uVar2);
    uVar7 = 0;
    uVar5 = *(byte *)(param_1 + uVar2) & 0x7f;
    if ((*(byte *)(param_1 + uVar2) & 0x80) != 0) {
      uVar2 = 0;
      do {
        uVar6 = uVar2 + 7;
        bVar8 = param_2 <= uVar4;
        uVar4 = uVar4 + 1;
        if (0x3f < uVar6 || bVar8) {
          return 0;
        }
        *param_3 = uVar4;
        pbVar3 = pbVar3 + 1;
        uVar1 = *pbVar3 & 0x7f;
        uVar5 = uVar5 | uVar1 << (uVar6 & 0xff);
        uVar7 = uVar7 | uVar1 << (uVar2 - 0x19 & 0xff) | uVar1 >> (0x20 - uVar6 & 0xff);
        uVar2 = uVar6;
      } while ((*pbVar3 & 0x80) != 0);
    }
    uVar2 = uVar5 >> 3 | uVar7 << 0x1d;
    if (uVar2 != 0 || uVar7 >> 3 != 0) {
      uVar5 = uVar5 & 7;
      *param_4 = uVar2;
      param_4[1] = uVar5;
      if (uVar5 == 0) {
        uVar2 = *param_3;
        if (uVar2 < param_2) {
          uVar4 = uVar2 + 1;
          *param_3 = uVar4;
          uVar7 = 0;
          pbVar3 = (byte *)(param_1 + uVar2);
          uVar5 = *(byte *)(param_1 + uVar2) & 0x7f;
          if ((*(byte *)(param_1 + uVar2) & 0x80) != 0) {
            uVar2 = 0;
            do {
              uVar6 = uVar2 + 7;
              bVar8 = param_2 <= uVar4;
              uVar4 = uVar4 + 1;
              if (0x3f < uVar6 || bVar8) {
                return 0;
              }
              *param_3 = uVar4;
              pbVar3 = pbVar3 + 1;
              uVar1 = *pbVar3 & 0x7f;
              uVar5 = uVar5 | uVar1 << (uVar6 & 0xff);
              uVar7 = uVar7 | uVar1 << (uVar2 - 0x19 & 0xff) | uVar1 >> (0x20 - uVar6 & 0xff);
              uVar2 = uVar6;
            } while ((*pbVar3 & 0x80) != 0);
          }
          param_4[2] = uVar5;
          param_4[3] = uVar7;
          return 1;
        }
      }
      else if (uVar5 == 1) {
        if (7 < param_2 - *param_3) {
          *param_3 = *param_3 + 8;
          return 1;
        }
      }
      else if (uVar5 == 2) {
        uVar2 = *param_3;
        if (uVar2 < param_2) {
          uVar4 = uVar2 + 1;
          *param_3 = uVar4;
          pbVar3 = (byte *)(param_1 + uVar2);
          uVar7 = 0;
          uVar5 = *(byte *)(param_1 + uVar2) & 0x7f;
          if ((*(byte *)(param_1 + uVar2) & 0x80) != 0) {
            uVar7 = 0;
            uVar2 = 0;
            do {
              uVar6 = uVar2 + 7;
              bVar8 = param_2 <= uVar4;
              uVar4 = uVar4 + 1;
              if (0x3f < uVar6 || bVar8) {
                return 0;
              }
              *param_3 = uVar4;
              pbVar3 = pbVar3 + 1;
              uVar1 = *pbVar3 & 0x7f;
              uVar5 = uVar5 | uVar1 << (uVar6 & 0xff);
              uVar7 = uVar7 | uVar1 << (uVar2 - 0x19 & 0xff) | uVar1 >> (0x20 - uVar6 & 0xff);
              uVar2 = uVar6;
            } while ((*pbVar3 & 0x80) != 0);
          }
          bVar8 = uVar7 == 0;
          if (uVar7 == 0) {
            bVar8 = uVar5 <= param_2 - uVar4;
          }
          if (bVar8) {
            param_4[5] = uVar5;
            uVar2 = *param_3;
            param_4[4] = param_1 + uVar4;
            *param_3 = uVar2 + uVar5;
            return 1;
          }
        }
      }
      else if ((uVar5 == 5) && (3 < param_2 - *param_3)) {
        *param_3 = *param_3 + 4;
        return 1;
      }
    }
  }
  return 0;
}



/* Function: FUN_00016e78 @ 00016e78 */

undefined4 FUN_00016e78(undefined4 param_1,uint param_2,int param_3,int param_4,int *param_5)

{
  int iVar1;
  uint local_3c;
  int local_38;
  int local_34;
  int iStack_30;
  int iStack_2c;
  int local_28;
  int iStack_24;
  
  local_3c = 0;
  do {
    if ((param_2 <= local_3c) ||
       (iVar1 = FUN_00016b94(param_1,param_2,&local_3c,&local_38), iVar1 == 0)) {
      return 0;
    }
  } while ((local_38 != param_3) || (local_34 != param_4));
  *param_5 = local_38;
  param_5[1] = local_34;
  param_5[2] = iStack_30;
  param_5[3] = iStack_2c;
  param_5[4] = local_28;
  param_5[5] = iStack_24;
  return 1;
}



/* Function: FUN_00016f1c @ 00016f1c */

bool FUN_00016f1c(void)

{
  int iVar1;
  undefined4 *in_r3;
  undefined4 local_18;
  
  iVar1 = FUN_00016e78();
  if (iVar1 != 0) {
    *in_r3 = local_18;
  }
  return iVar1 != 0;
}



/* Function: FUN_00016f50 @ 00016f50 */

undefined4 FUN_00016f50(char *param_1,int param_2,uint param_3)

{
  int iVar1;
  ssize_t sVar2;
  int *piVar3;
  uint uVar4;
  
  iVar1 = open(param_1,0x301,0x1a4);
  if (iVar1 < 0) {
    return 0;
  }
  uVar4 = 0;
  do {
    while( true ) {
      if (param_3 <= uVar4) {
        iVar1 = close(iVar1);
        if (iVar1 != 0) {
          unlink(param_1);
          return 0;
        }
        return 1;
      }
      sVar2 = write(iVar1,(void *)(param_2 + uVar4),param_3 - uVar4);
      if (-1 < sVar2) break;
      piVar3 = (int *)__get_errno_ptr();
      if (*piVar3 != 4) goto LAB_00016fb4;
    }
    uVar4 = uVar4 + sVar2;
  } while (sVar2 != 0);
LAB_00016fb4:
  close(iVar1);
  unlink(param_1);
  return 0;
}



/* Function: FUN_00016ff8 @ 00016ff8 */

undefined4 FUN_00016ff8(int param_1,uint *param_2,byte *param_3)

{
  uint uVar1;
  uint uVar2;
  int iVar3;
  
  if (*param_3 == 0) {
    uVar1 = *param_2;
    uVar2 = uVar1 + 1;
    if (uVar2 < 0x640) {
      *param_2 = uVar2;
      *(undefined1 *)(param_1 + uVar1) = 0x2d;
      return 1;
    }
  }
  else {
    uVar1 = *param_2;
    if (uVar1 + 2 < 0x640) {
      iVar3 = DAT_000170bc + 0x17024;
      do {
        *param_2 = uVar1 + 1;
        *(undefined1 *)(param_1 + uVar1) = *(undefined1 *)(iVar3 + (uint)(*param_3 >> 4));
        uVar1 = *param_2;
        *param_2 = uVar1 + 1;
        *(undefined1 *)(param_1 + uVar1) = *(undefined1 *)(iVar3 + (*param_3 & 0xf));
        param_3 = param_3 + 1;
        if (*param_3 == 0) {
          return 1;
        }
        uVar1 = *param_2;
      } while (uVar1 + 2 < 0x640);
      return 0;
    }
  }
  return 0;
}



/* Function: FUN_000170c0 @ 000170c0 */

undefined4 FUN_000170c0(undefined4 param_1,undefined4 param_2,void *param_3)

{
  int iVar1;
  undefined4 uVar2;
  uint __n;
  undefined1 auStack_28 [16];
  void *local_18;
  uint local_14;
  
  iVar1 = FUN_00016e78(param_1,param_2,1,2,auStack_28);
  uVar2 = 0;
  if (iVar1 != 0) {
    __n = local_14;
    if (0xbe < local_14) {
      __n = 0xbf;
    }
    if (__n != 0) {
      memcpy(param_3,local_18,__n);
    }
    uVar2 = 1;
    *(undefined1 *)((int)param_3 + __n) = 0;
  }
  return uVar2;
}



/* Function: FUN_00017124 @ 00017124 */

void FUN_00017124(void *param_1)

{
  memset(param_1,0,0x2b0);
  *(undefined4 *)((int)param_1 + 0x1c) = 0xffffffff;
  *(undefined4 *)((int)param_1 + 0x20) = 0xffffffff;
  *(undefined4 *)((int)param_1 + 0x24) = 0xffffffff;
  *(undefined4 *)((int)param_1 + 0x28) = 0xffffffff;
  *(undefined4 *)((int)param_1 + 0x2c) = 0xffffffff;
  *(undefined4 *)((int)param_1 + 0x30) = 0xffffffff;
  return;
}



/* Function: FUN_00017158 @ 00017158 */

undefined4 FUN_00017158(int *param_1,int param_2,int param_3,uint param_4,int param_5,int param_6)

{
  byte bVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  int iVar4;
  int iVar5;
  size_t sVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  uint uVar11;
  bool bVar12;
  uint local_90;
  uint local_88;
  uint local_70;
  uint local_6c;
  uint local_68;
  uint local_64;
  undefined4 local_60;
  undefined2 local_5c;
  undefined1 local_5a;
  uint local_58;
  int local_54;
  int local_48;
  uint local_44;
  uint local_40;
  int local_3c;
  undefined4 local_30;
  uint local_2c;
  
  if (param_3 != 0 && param_1 != (int *)0x0) {
    if (param_2 == 0x8003) {
      iVar4 = FUN_00016f1c(param_3,param_4,1,&local_40);
      if (iVar4 != 0) {
        param_1[5] = local_40;
        bVar12 = (local_40 & 0xfffffffd) == 1;
        param_1[4] = (uint)bVar12;
        if (bVar12 == 0) {
          *(bool *)(param_1 + 0xd) = bVar12;
          *(bool *)(param_1 + 0x3d) = bVar12;
          *(bool *)(param_1 + 0x6d) = bVar12;
          param_1[9] = -1;
          param_1[10] = -1;
          param_1[0xb] = -1;
          param_1[0xc] = -1;
          memset(param_1 + 0x9d,0,0x38);
          *(bool *)(param_1 + 0xab) = bVar12;
        }
LAB_00017248:
        param_1[2] = param_5;
        param_1[3] = param_6;
        *param_1 = *param_1 + 1;
        return 1;
      }
    }
    else if (param_2 == 0x8005) {
      local_60 = 0;
      do {
        if (param_4 <= local_60) {
          iVar4 = FUN_00016f1c(param_3,param_4,1,&local_58);
          bVar12 = iVar4 != 0;
          if (bVar12) {
            param_1[9] = local_58 | (int)local_58 >> 0x1f;
          }
          iVar4 = FUN_00016f1c(param_3,param_4,2,&local_58);
          if (iVar4 == 0) goto LAB_000177bc;
          param_1[10] = local_58 | (int)local_58 >> 0x1f;
          goto LAB_00017248;
        }
        iVar4 = FUN_00016b94(param_3,param_4,&local_60,&local_40);
      } while (iVar4 != 0);
    }
    else {
      if (param_2 == 0x8006) {
        iVar4 = FUN_00016e78(param_3,param_4,1,2,&local_40);
        uVar11 = local_2c;
        uVar2 = local_30;
        if (iVar4 == 0) {
          return 0;
        }
        local_70 = 0;
        memset(param_1 + 0x9d,0,0x38);
        *(undefined1 *)(param_1 + 0xab) = 0;
        uVar8 = 0;
        while ((uVar8 < uVar11 &&
               (iVar5 = FUN_00016b94(uVar2,uVar11,&local_70,&local_58), uVar10 = local_44,
               iVar4 = local_48, iVar5 != 0))) {
          uVar8 = local_70;
          if ((local_58 == 3) && ((local_54 == 2 && (*(byte *)(param_1 + 0xab) < 8)))) {
            local_6c = 0;
            local_60 = 0;
            local_90 = 0;
            local_5c = 0;
            local_88 = 0;
            local_5a = 0;
            while (local_6c < uVar10) {
              local_64 = 0;
              iVar5 = FUN_00016b94(iVar4,uVar10,&local_6c,&local_40);
              uVar9 = local_2c;
              uVar3 = local_30;
              uVar8 = local_40;
              if (iVar5 == 0) break;
              if ((((local_40 == 1) && (local_3c == 2)) && (local_90 < 3)) &&
                 ((iVar5 = FUN_00016f1c(local_30,local_2c,1,&local_68), iVar5 != 0 &&
                  (local_68 < 10)))) {
                FUN_00016f1c(uVar3,uVar9,2,&local_64);
                if (local_64 == 0) {
                  uVar9 = 0;
                }
                else {
                  uVar9 = (local_88 ^ 1) & 1;
                }
                uVar7 = local_90 + 1;
                iVar5 = local_90 * 2;
                if (uVar9 != 0) {
                  local_88 = uVar8;
                }
                local_90 = uVar7 & 0xff;
                local_5a = (undefined1)uVar7;
                *(char *)((int)&local_60 + iVar5) = (char)local_68;
                if (uVar9 != 0) {
                  *(undefined1 *)((int)&local_60 + iVar5 + 1) = 1;
                }
                else {
                  *(undefined1 *)((int)&local_60 + iVar5 + 1) = 0;
                }
              }
            }
            uVar8 = local_70;
            if (local_90 != 0) {
              bVar1 = *(byte *)(param_1 + 0xab);
              *(byte *)(param_1 + 0xab) = bVar1 + 1;
              memcpy((void *)((int)param_1 + (uint)bVar1 * 7 + 0x274),&local_60,7);
              uVar8 = local_70;
            }
          }
        }
        iVar4 = FUN_00016e78(uVar2,uVar11,1,2,&local_40);
        uVar8 = local_2c;
        uVar3 = local_30;
        if (iVar4 == 0) {
          bVar12 = false;
        }
        else {
          iVar4 = FUN_00016f1c(local_30,local_2c,1,&local_58);
          bVar12 = iVar4 != 0;
          if (bVar12) {
            param_1[6] = local_58;
          }
          iVar4 = FUN_00016f1c(uVar3,uVar8,2,&local_58);
          uVar10 = local_58;
          if (iVar4 == 0) {
            uVar10 = 0xffffffff;
          }
          param_1[7] = uVar10;
          iVar4 = FUN_00016f1c(uVar3,uVar8,3,&local_58);
          if (iVar4 == 0) {
            local_58 = 0xffffffff;
          }
          param_1[8] = local_58;
        }
        iVar4 = FUN_00016e78(uVar2,uVar11,2,2,&local_40);
        if (iVar4 != 0) {
          *(undefined1 *)(param_1 + 0x6d) = 0;
          iVar4 = FUN_000170c0(local_30,local_2c);
          if (iVar4 != 0) {
            bVar12 = true;
          }
        }
        iVar4 = FUN_00016e78(uVar2,uVar11,4,2,&local_40);
        if (iVar4 != 0) {
          *(undefined1 *)(param_1 + 0x3d) = 0;
          iVar4 = FUN_000170c0(local_30,local_2c);
          if (iVar4 != 0) {
            bVar12 = true;
          }
        }
        if (((char)param_1[0x3d] == '\0') && ((char)param_1[0x6d] != '\0')) {
          sVar6 = strlen((char *)(param_1 + 0x6d));
          memcpy(param_1 + 0x3d,param_1 + 0x6d,sVar6 + 1);
        }
      }
      else {
        if (param_2 != 0x8007) {
          return 0;
        }
        local_64 = 0;
        iVar4 = FUN_00016e78(param_3,param_4,1,2,&local_40);
        uVar11 = local_2c;
        uVar2 = local_30;
        if (iVar4 == 0) {
          bVar12 = false;
        }
        else {
          iVar4 = FUN_00016e78(local_30,local_2c,1,2,&local_40);
          if (iVar4 == 0) {
            bVar12 = false;
          }
          else {
            iVar4 = FUN_00016f1c(local_30,local_2c,1,&local_60);
            if (iVar4 == 0) {
              bVar12 = false;
            }
            else {
              bVar12 = true;
              param_1[9] = local_60 | (int)local_60 >> 0x1f;
            }
          }
          iVar4 = FUN_00016f1c(uVar2,uVar11,2,&local_60);
          if (iVar4 != 0) {
            bVar12 = true;
            param_1[10] = local_60 | (int)local_60 >> 0x1f;
          }
        }
        iVar4 = FUN_00016e78(param_3,param_4,3,2,&local_40);
        if (iVar4 != 0) {
          *(undefined1 *)(param_1 + 0xd) = 0;
          iVar4 = FUN_000170c0(local_30,local_2c);
          if (iVar4 != 0) {
            bVar12 = true;
          }
        }
        local_90 = 0;
        uVar11 = 0;
        while ((local_64 < param_4 &&
               (iVar4 = FUN_00016b94(param_3,param_4,&local_64,&local_58), iVar4 != 0))) {
          if ((local_58 == 2) && (local_54 == 2)) {
            local_90 = local_48;
            uVar11 = local_44;
          }
        }
        if (local_90 != 0) {
          iVar4 = FUN_00016e78(local_90,uVar11,1,2,&local_40);
          if ((iVar4 != 0) && (iVar4 = FUN_00016f1c(local_30,local_2c,1,&local_60), iVar4 != 0)) {
            bVar12 = true;
            param_1[0xb] = local_60 | (int)local_60 >> 0x1f;
          }
          iVar4 = FUN_00016f1c(local_90,uVar11,3,&local_60);
          if (iVar4 != 0) {
            param_1[0xc] = local_60 | (int)local_60 >> 0x1f;
            goto LAB_00017248;
          }
        }
      }
LAB_000177bc:
      if (bVar12) goto LAB_00017248;
    }
  }
  return 0;
}



/* Function: FUN_00017824 @ 00017824 */

undefined4 FUN_00017824(undefined4 *param_1,char *param_2)

{
  byte *pbVar1;
  char cVar2;
  int iVar3;
  int *piVar4;
  undefined4 *puVar5;
  uint uVar6;
  byte bVar7;
  char *pcVar8;
  undefined4 *puVar9;
  byte *pbVar10;
  uint uVar11;
  uint local_7c8;
  byte local_7c4 [36];
  char acStack_7a0 [320];
  char acStack_660 [1600];
  
  if (param_2 == (char *)0x0 || param_1 == (undefined4 *)0x0) {
    return 0;
  }
  local_7c8 = snprintf(acStack_660,0x640,(char *)(DAT_00017b80 + 0x17874),*param_1,param_1[2],
                       param_1[3],param_1[4],param_1[5],param_1[6],param_1[7],param_1[8],param_1[9],
                       param_1[10],param_1[0xb],param_1[0xc]);
  if (local_7c8 < 0x640) {
    iVar3 = FUN_00016ff8(acStack_660,&local_7c8,param_1 + 0xd);
    if ((iVar3 != 0) && (local_7c8 + 1 < 0x640)) {
      acStack_660[local_7c8] = '\t';
      local_7c8 = local_7c8 + 1;
      iVar3 = FUN_00016ff8(acStack_660,&local_7c8,param_1 + 0x3d);
      if ((iVar3 != 0) && (local_7c8 + 1 < 0x640)) {
        acStack_660[local_7c8] = '\t';
        local_7c8 = local_7c8 + 1;
        iVar3 = FUN_00016ff8(acStack_660,&local_7c8,param_1 + 0x6d);
        if ((iVar3 != 0) && (local_7c8 + 1 < 0x640)) {
          bVar7 = *(byte *)(param_1 + 0xab);
          acStack_660[local_7c8] = '\t';
          if (bVar7 != 0) {
            uVar11 = 1;
            local_7c4[0] = bVar7;
            puVar9 = (undefined4 *)((int)param_1 + (uint)bVar7 * 7 + -7);
            do {
              bVar7 = *(byte *)((int)param_1 + 0x27a);
              uVar6 = uVar11 + 1;
              pbVar10 = local_7c4 + uVar11;
              local_7c4[uVar11] = bVar7;
              uVar11 = uVar6;
              if (bVar7 != 0) {
                if (0x20 < uVar6) break;
                uVar11 = bVar7 + uVar6;
                puVar5 = param_1;
                while( true ) {
                  pcVar8 = (char *)((int)puVar5 + 0x275);
                  uVar6 = uVar6 + 1;
                  pbVar1 = (byte *)(puVar5 + 0x9d);
                  puVar5 = (undefined4 *)((int)puVar5 + 2);
                  bVar7 = 0;
                  if (*pcVar8 != '\0') {
                    bVar7 = 0x80;
                  }
                  pbVar10 = pbVar10 + 1;
                  *pbVar10 = bVar7 | *pbVar1;
                  if (uVar6 == uVar11) break;
                  if (uVar6 == 0x21) goto LAB_00017a2c;
                }
              }
              if (param_1 == puVar9) {
                uVar6 = local_7c8 + 1;
                if (uVar11 != 0) {
                  uVar6 = local_7c8 + 3;
                  if (0x63f < uVar6) {
                    return 0;
                  }
                  pcVar8 = acStack_660 + local_7c8;
                  pbVar10 = local_7c4;
                  while( true ) {
                    cVar2 = *(char *)(DAT_00017b88 + 0x17b20 + (*pbVar10 & 0xf));
                    pcVar8[1] = *(char *)(DAT_00017b88 + 0x17b20 + (uint)(*pbVar10 >> 4));
                    pcVar8[2] = cVar2;
                    if (pbVar10 + 1 == local_7c4 + uVar11) break;
                    uVar6 = uVar6 + 2;
                    pcVar8 = pcVar8 + 2;
                    pbVar10 = pbVar10 + 1;
                    if (0x63f < uVar6) {
                      return 0;
                    }
                  }
                }
                goto LAB_00017a50;
              }
              param_1 = (undefined4 *)((int)param_1 + 7);
            } while (uVar11 < 0x21);
          }
LAB_00017a2c:
          if (local_7c8 + 2 != 0x640) {
            acStack_660[local_7c8 + 1] = '-';
            uVar6 = local_7c8 + 2;
LAB_00017a50:
            local_7c8 = uVar6;
            if (local_7c8 + 1 < 0x640) {
              acStack_660[local_7c8] = '\n';
              local_7c8 = local_7c8 + 1;
              iVar3 = snprintf(acStack_7a0,0x140,(char *)(DAT_00017b84 + 0x17a88),param_2);
              if ((-1 < iVar3) &&
                 (iVar3 = FUN_00016f50(acStack_7a0,acStack_660,local_7c8), iVar3 != 0)) {
                iVar3 = rename(acStack_7a0,param_2);
                if (iVar3 == 0) {
                  return 1;
                }
                piVar4 = (int *)__get_errno_ptr(iVar3);
                if ((*piVar4 == 0x12) &&
                   (iVar3 = FUN_00016f50(param_2,acStack_660,local_7c8), iVar3 != 0)) {
                  unlink(acStack_7a0);
                  return 1;
                }
                unlink(acStack_7a0);
                return 0;
              }
            }
          }
        }
      }
    }
  }
  return 0;
}



/* Function: FUN_00017b8c @ 00017b8c */

undefined4 FUN_00017b8c(void)

{
  int iVar1;
  int iVar2;
  int *piVar3;
  uint uVar4;
  pthread_mutex_t *__mutex;
  pthread_cond_t *__cond;
  int *piVar5;
  pthread_mutex_t *__mutex_00;
  int *piVar6;
  uint *puVar7;
  int iVar8;
  undefined2 local_1050;
  byte local_104d;
  undefined4 local_104c;
  undefined4 local_1038;
  undefined4 uStack_1034;
  undefined1 auStack_102c [4104];
  
  __mutex_00 = (pthread_mutex_t *)(DAT_00017d14 + 0x17bac);
  piVar5 = (int *)(DAT_00017d18 + 0x17bb4);
  __cond = (pthread_cond_t *)(DAT_00017d1c + 0x17bb8);
  __mutex = (pthread_mutex_t *)(DAT_00017d20 + 0x17bbc);
  do {
    pthread_mutex_lock(__mutex_00);
    iVar2 = *piVar5;
    if (iVar2 == 0) {
      if (*(int *)(DAT_00017d24 + 0x17bd8) != 0) {
LAB_00017cdc:
        pthread_mutex_unlock((pthread_mutex_t *)(DAT_00017d4c + 0x17ce8));
        return 0;
      }
      piVar3 = (int *)(DAT_00017d28 + 0x17bf0);
      piVar6 = (int *)(DAT_00017d2c + 0x17bf4);
      while( true ) {
        pthread_cond_wait(__cond,__mutex);
        iVar2 = *piVar3;
        if (iVar2 != 0) break;
        if (*piVar6 != 0) goto LAB_00017cdc;
      }
    }
    puVar7 = (uint *)(DAT_00017d30 + 0x17c30);
    uVar4 = *puVar7;
    memcpy(&local_1050,(void *)(DAT_00017d34 + 0x17c38 + uVar4 * 0x1028),0x1028);
    iVar8 = DAT_00017d3c;
    piVar3 = (int *)(DAT_00017d38 + 0x17c64);
    *puVar7 = uVar4 + 1 & 0x3f;
    *piVar3 = iVar2 + -1;
    pthread_mutex_unlock((pthread_mutex_t *)(iVar8 + 0x17c6c));
    if ((local_104d & 1) == 0) {
      iVar8 = DAT_00017d40 + 0x17c90;
      iVar2 = FUN_00017158(iVar8,local_1050,auStack_102c,local_104c,local_1038,uStack_1034);
      if (iVar2 != 0) {
        iVar1 = FUN_00017824(iVar8,DAT_00017d44 + 0x17cb8);
        iVar8 = DAT_00017d50;
        iVar2 = DAT_00017d48;
        if (iVar1 == 0) {
          uVar4 = *(uint *)((int)&DAT_00017d24 + DAT_00017d50);
          piVar3 = (int *)((int)&DAT_00017d28 + DAT_00017d50);
          *(uint *)((int)&DAT_00017d24 + DAT_00017d50) = uVar4 + 1;
          *(uint *)((int)&DAT_00017d28 + iVar8) = *piVar3 + (uint)(0xfffffffe < uVar4);
        }
        else {
          uVar4 = *(uint *)(DAT_00017d48 + 0x17ce4);
          piVar3 = (int *)(DAT_00017d48 + 0x17ce8);
          *(uint *)(DAT_00017d48 + 0x17ce4) = uVar4 + 1;
          *(uint *)(iVar2 + 0x17ce8) = *piVar3 + (uint)(0xfffffffe < uVar4);
        }
      }
    }
  } while( true );
}



/* Function: FUN_00017d54 @ 00017d54 */

undefined4 FUN_00017d54(undefined4 param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  pthread_t *__newthread;
  int iVar5;
  __start_routine *__start_routine;
  undefined4 *puVar6;
  
  iVar3 = DAT_00017e50;
  iVar2 = DAT_00017e4c;
  iVar1 = DAT_00017e48;
  iVar5 = DAT_00017e40;
  puVar6 = (undefined4 *)(DAT_00017e44 + 0x17d74);
  *(undefined4 *)(DAT_00017e40 + 0x17d6c) = 0;
  *puVar6 = param_1;
  iVar4 = DAT_00017e54;
  *(undefined4 *)(iVar5 + 0x17d70) = 0;
  *(undefined4 *)(iVar5 + 0x17d74) = 0;
  *(undefined4 *)(iVar5 + 0x17d78) = 0;
  *(undefined4 *)(iVar5 + 0x17d7c) = 0;
  *(undefined4 *)(iVar5 + 0x17d80) = 0;
  *(undefined4 *)(iVar5 + 0x17d84) = 0;
  *(undefined4 *)(iVar5 + 0x17d88) = 0;
  *(undefined4 *)(iVar5 + 0x17d8c) = 0;
  *(undefined4 *)(iVar5 + 0x17d90) = 0;
  *(undefined4 *)(iVar5 + 0x17d94) = 0;
  *(undefined4 *)(iVar5 + 0x17d98) = 0;
  *(undefined4 *)(iVar1 + 0x17d90) = 0;
  *(undefined4 *)(iVar2 + 0x17d98) = 0;
  *(undefined4 *)(iVar3 + 0x17d9c) = 0;
  FUN_00017124(iVar4 + 0x17da4);
  unlink((char *)(DAT_00017e58 + 0x17de4));
  unlink((char *)(DAT_00017e5c + 0x17df0));
  __newthread = (pthread_t *)(DAT_00017e64 + 0x17e10);
  __start_routine = (__start_routine *)(DAT_00017e68 + 0x17e14);
  *(undefined4 *)(DAT_00017e60 + 0x17e08) = 0;
  iVar5 = pthread_create(__newthread,(pthread_attr_t *)0x0,__start_routine,(void *)0x0);
  if (iVar5 == 0) {
    *(undefined4 *)(DAT_00017e6c + 0x17e34) = 1;
    return 1;
  }
  return 0;
}



/* Function: FUN_00017e70 @ 00017e70 */

void FUN_00017e70(void)

{
  pthread_cond_t *__cond;
  int *piVar1;
  pthread_mutex_t *__mutex;
  
  piVar1 = (int *)(DAT_00017edc + 0x17e80);
  if (*piVar1 == 0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_00017ee0 + 0x17e94);
  pthread_mutex_lock(__mutex);
  __cond = (pthread_cond_t *)(DAT_00017ee8 + 0x17eb0);
  *(undefined4 *)(DAT_00017ee4 + 0x17eac) = 1;
  pthread_cond_signal(__cond);
  pthread_mutex_unlock(__mutex);
  pthread_join(*(pthread_t *)(DAT_00017eec + 0x17ecc),(void **)0x0);
  *piVar1 = 0;
  return;
}



/* Function: FUN_00017ef0 @ 00017ef0 */

void FUN_00017ef0(undefined1 param_1,undefined2 param_2,void *param_3,uint param_4)

{
  longlong lVar1;
  __time_t _Var2;
  pthread_cond_t *__cond;
  pthread_mutex_t *__mutex;
  uint extraout_r1;
  uint uVar3;
  int *piVar4;
  undefined4 extraout_r2;
  undefined4 uVar5;
  int iVar6;
  uint uVar7;
  int iVar8;
  undefined4 extraout_r3;
  undefined4 uVar9;
  undefined1 uVar10;
  int iVar11;
  void *pvVar12;
  timespec local_30;
  
  if (*(int *)(DAT_000181d4 + 0x17f00) != 0 && param_3 != (void *)0x0) {
    uVar10 = 0;
    if (param_4 < 0x1001) {
      pthread_mutex_lock((pthread_mutex_t *)(DAT_000181d8 + 0x17f3c));
      if (*(int *)(DAT_000181dc + 0x17f48) == 0x40) {
LAB_00018194:
        iVar6 = DAT_0001821c;
        __mutex = (pthread_mutex_t *)(DAT_00018220 + 0x181a8);
        uVar7 = *(uint *)(DAT_0001821c + 0x181ac);
        piVar4 = (int *)(DAT_0001821c + 0x181b0);
        *(uint *)(DAT_0001821c + 0x181ac) = uVar7 + 1;
        *(uint *)(iVar6 + 0x181b0) = *piVar4 + (uint)(0xfffffffe < uVar7);
        pthread_mutex_unlock(__mutex);
        return;
      }
      iVar8 = DAT_000181e4 + 0x17f70;
      iVar11 = *(int *)(DAT_000181e0 + 0x17f68);
      iVar6 = iVar11 << 7;
      pvVar12 = (void *)(iVar8 + iVar11 * 0x1028);
      memset(pvVar12,0,0x1028);
      *(undefined2 *)(iVar8 + iVar11 * 0x1028) = param_2;
      *(undefined1 *)((int)pvVar12 + 2) = param_1;
      uVar7 = param_4;
    }
    else {
      pthread_mutex_lock((pthread_mutex_t *)(DAT_000181e8 + 0x17fbc));
      if (*(int *)(DAT_000181ec + 0x17fc8) == 0x40) goto LAB_00018194;
      uVar7 = 0x1000;
      iVar8 = DAT_000181f4 + 0x17ff0;
      iVar11 = *(int *)(DAT_000181f0 + 0x17fe8);
      iVar6 = iVar11 * 0x80;
      pvVar12 = (void *)(iVar8 + iVar11 * 0x1028);
      memset(pvVar12,0,0x1028);
      uVar10 = 1;
      *(undefined1 *)((int)pvVar12 + 2) = param_1;
      *(undefined2 *)(iVar8 + iVar11 * 0x1028) = param_2;
    }
    iVar8 = DAT_000181f8 + 0x18044 + iVar6 * 0x20 + iVar11 * 0x28;
    *(undefined1 *)(iVar8 + 3) = uVar10;
    *(uint *)(iVar8 + 4) = uVar7;
    *(uint *)(iVar8 + 8) = param_4;
    iVar8 = clock_gettime(2,&local_30);
    _Var2 = local_30.tv_sec;
    uVar9 = extraout_r3;
    uVar5 = extraout_r2;
    if (iVar8 != 0) {
      uVar5 = 0;
      uVar9 = 0;
    }
    lVar1 = CONCAT44(uVar9,uVar5);
    if (iVar8 == 0) {
      lVar1 = FUN_0001b350(local_30.tv_nsec,local_30.tv_nsec >> 0x1f,&DAT_000f4240,0);
      lVar1 = (ulonglong)(uint)_Var2 * 1000 + lVar1;
    }
    *(longlong *)(DAT_000181fc + iVar6 * 0x20 + iVar11 * 0x28 + 0x180c0) = lVar1;
    iVar8 = clock_gettime(0,&local_30);
    uVar3 = extraout_r1;
    if (iVar8 != 0) {
      uVar3 = 0;
    }
    lVar1 = (ulonglong)uVar3 << 0x20;
    if (iVar8 == 0) {
      lVar1 = FUN_0001b350(local_30.tv_nsec,local_30.tv_nsec >> 0x1f,&DAT_000f4240,0);
      lVar1 = (ulonglong)(uint)local_30.tv_sec * 1000 + lVar1;
    }
    iVar8 = DAT_00018200;
    iVar6 = DAT_00018204 + 0x1810c + (iVar6 * 4 + iVar11 * 5) * 8;
    iVar11 = *(int *)(DAT_00018200 + 0x18130) + 1;
    *(longlong *)(iVar6 + 0x18) = lVar1;
    *(int *)(iVar8 + 0x18130) = iVar11;
    *(int *)(iVar6 + 0x20) = iVar11;
    if (uVar7 != 0) {
      memcpy((void *)(iVar6 + 0x24),param_3,uVar7);
    }
    iVar6 = DAT_00018208;
    if (uVar7 != param_4) {
      uVar7 = *(uint *)(DAT_00018208 + 0x1814c);
      piVar4 = (int *)(DAT_00018208 + 0x18150);
      *(uint *)(DAT_00018208 + 0x1814c) = uVar7 + 1;
      *(uint *)(iVar6 + 0x18150) = *piVar4 + (uint)(0xfffffffe < uVar7);
    }
    piVar4 = (int *)(DAT_00018210 + 0x18160);
    __cond = (pthread_cond_t *)(DAT_00018214 + 0x18168);
    iVar6 = *piVar4;
    *(uint *)(DAT_0001820c + 0x18158) = *(uint *)(DAT_0001820c + 0x18158) + 1 & 0x3f;
    *piVar4 = iVar6 + 1;
    pthread_cond_signal(__cond);
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_00018218 + 0x1818c));
  }
  return;
}



/* Function: FUN_00018224 @ 00018224 */

void FUN_00018224(undefined4 *param_1)

{
  int iVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  pthread_mutex_t *__mutex;
  undefined4 *puVar5;
  
  if (param_1 == (undefined4 *)0x0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_00018274 + 0x18240);
  pthread_mutex_lock(__mutex);
  iVar1 = DAT_00018278;
  uVar2 = *(undefined4 *)(DAT_00018278 + 0x18258);
  uVar3 = *(undefined4 *)(DAT_00018278 + 0x1825c);
  uVar4 = *(undefined4 *)(DAT_00018278 + 0x18260);
  puVar5 = (undefined4 *)(DAT_00018278 + 0x18264);
  *param_1 = *(undefined4 *)(DAT_00018278 + 0x18254);
  param_1[1] = uVar2;
  param_1[2] = uVar3;
  param_1[3] = uVar4;
  uVar2 = *(undefined4 *)(iVar1 + 0x18268);
  uVar3 = *(undefined4 *)(iVar1 + 0x1826c);
  uVar4 = *(undefined4 *)(iVar1 + 0x18270);
  param_1[4] = *puVar5;
  param_1[5] = uVar2;
  param_1[6] = uVar3;
  param_1[7] = uVar4;
  uVar2 = *(undefined4 *)((int)&DAT_00018278 + iVar1);
  uVar3 = *(undefined4 *)(FUN_0001827c + iVar1);
  uVar4 = *(undefined4 *)(iVar1 + 0x18280);
  param_1[8] = *(undefined4 *)((int)&DAT_00018274 + iVar1);
  param_1[9] = uVar2;
  param_1[10] = uVar3;
  param_1[0xb] = uVar4;
  pthread_mutex_unlock(__mutex);
  return;
}



/* Function: FUN_0001827c @ 0001827c */

undefined4 FUN_0001827c(int *param_1,void *param_2,uint param_3)

{
  if ((uint)(param_1[2] - param_1[1]) < param_3) {
    return 0;
  }
  memcpy((void *)(*param_1 + param_1[1]),param_2,param_3);
  param_1[1] = param_1[1] + param_3;
  return 1;
}



/* Function: FUN_000182c4 @ 000182c4 */

void FUN_000182c4(undefined4 param_1,undefined4 param_2,uint param_3,uint param_4)

{
  byte *pbVar1;
  uint uVar2;
  uint uVar3;
  byte abStack_1c [12];
  
  pbVar1 = abStack_1c;
  uVar2 = 0;
  do {
    uVar3 = uVar2;
    uVar2 = param_3 >> 7 | param_4 << 0x19;
    if (uVar2 == 0 && param_4 >> 7 == 0) {
      abStack_1c[uVar3] = (byte)param_3 & 0x7f;
      break;
    }
    *pbVar1 = ~((byte)~(byte)((param_3 << 0x19) >> 0x18) >> 1);
    param_3 = uVar2;
    param_4 = param_4 >> 7;
    pbVar1 = pbVar1 + 1;
    uVar2 = uVar3 + 1;
  } while (uVar3 + 1 < 10);
  FUN_0001827c(param_1,abStack_1c,uVar3 + 1);
  return;
}



/* Function: FUN_00018334 @ 00018334 */

bool FUN_00018334(undefined4 param_1,uint param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  undefined8 uVar2;
  
  uVar2 = FUN_000182c4(param_1,param_2,param_2 << 3,param_2 >> 0x1d);
  if ((int)uVar2 == 0) {
    return false;
  }
  iVar1 = FUN_000182c4(param_1,(int)((ulonglong)uVar2 >> 0x20),param_3,param_4);
  return iVar1 != 0;
}



/* Function: FUN_00018374 @ 00018374 */

bool FUN_00018374(undefined4 param_1,uint param_2,undefined4 *param_3,undefined4 param_4)

{
  int iVar1;
  undefined8 uVar2;
  
  uVar2 = FUN_000182c4(param_1,param_2,param_2 << 3 | 2,param_2 >> 0x1d,param_4);
  if (((int)uVar2 != 0) &&
     (iVar1 = FUN_000182c4(param_1,(int)((ulonglong)uVar2 >> 0x20),param_3[1],0,param_4), iVar1 != 0
     )) {
    iVar1 = FUN_0001827c(param_1,*param_3,param_3[1]);
    return iVar1 != 0;
  }
  return false;
}



/* Function: FUN_000183d4 @ 000183d4 */

undefined4 FUN_000183d4(int param_1,uint param_2,uint param_3,uint *param_4)

{
  uint uVar1;
  uint uVar2;
  byte *pbVar3;
  uint uVar4;
  uint uVar5;
  uint uVar6;
  uint uVar7;
  bool bVar8;
  
  if (param_2 != 0) {
    uVar2 = 0;
    do {
      if (param_2 <= uVar2) {
        return 0;
      }
      uVar7 = uVar2 + 1;
      uVar6 = 0;
      pbVar3 = (byte *)(param_1 + uVar2);
      uVar5 = *(byte *)(param_1 + uVar2) & 0x7f;
      if ((*(byte *)(param_1 + uVar2) & 0x80) != 0) {
        uVar2 = 0;
        do {
          uVar4 = uVar2 + 7;
          bVar8 = param_2 <= uVar7;
          uVar7 = uVar7 + 1;
          if (0x3f < uVar4 || bVar8) {
            return 0;
          }
          pbVar3 = pbVar3 + 1;
          uVar1 = *pbVar3 & 0x7f;
          uVar5 = uVar5 | uVar1 << (uVar4 & 0xff);
          uVar6 = uVar6 | uVar1 << (uVar2 - 0x19 & 0xff) | uVar1 >> (0x20 - uVar4 & 0xff);
          uVar2 = uVar4;
        } while ((*pbVar3 & 0x80) != 0);
      }
      uVar2 = uVar5 & 7;
      if (uVar2 == 0) {
        if (param_2 <= uVar7) {
          return 0;
        }
        uVar2 = uVar7 + 1;
        pbVar3 = (byte *)(param_1 + uVar7);
        uVar4 = *(byte *)(param_1 + uVar7) & 0x7f;
        if ((*(byte *)(param_1 + uVar7) & 0x80) != 0) {
          uVar7 = 0;
          do {
            uVar7 = uVar7 + 7;
            bVar8 = param_2 <= uVar2;
            uVar2 = uVar2 + 1;
            if (0x3f < uVar7 || bVar8) {
              return 0;
            }
            pbVar3 = pbVar3 + 1;
            uVar4 = uVar4 | (*pbVar3 & 0x7f) << (uVar7 & 0xff);
          } while ((*pbVar3 & 0x80) != 0);
        }
        if (param_3 == (uVar5 >> 3 | uVar6 << 0x1d)) {
          *param_4 = uVar4;
          return 1;
        }
      }
      else if (uVar2 == 1) {
        if (param_2 - uVar7 < 8) {
          return 0;
        }
        uVar2 = uVar7 + 8;
      }
      else if (uVar2 == 2) {
        if (param_2 <= uVar7) {
          return 0;
        }
        uVar2 = uVar7 + 1;
        uVar6 = 0;
        pbVar3 = (byte *)(param_1 + uVar7);
        uVar5 = *(byte *)(param_1 + uVar7) & 0x7f;
        if ((*(byte *)(param_1 + uVar7) & 0x80) != 0) {
          uVar7 = 0;
          do {
            uVar4 = uVar7 + 7;
            bVar8 = param_2 <= uVar2;
            uVar2 = uVar2 + 1;
            if (0x3f < uVar4 || bVar8) {
              return 0;
            }
            pbVar3 = pbVar3 + 1;
            uVar1 = *pbVar3 & 0x7f;
            uVar5 = uVar5 | uVar1 << (uVar4 & 0xff);
            uVar6 = uVar6 | uVar1 << (uVar7 - 0x19 & 0xff) | uVar1 >> (0x20 - uVar4 & 0xff);
            uVar7 = uVar4;
          } while ((*pbVar3 & 0x80) != 0);
        }
        bVar8 = uVar6 == 0;
        if (uVar6 == 0) {
          bVar8 = uVar5 <= param_2 - uVar2;
        }
        if (!bVar8) {
          return 0;
        }
        uVar2 = uVar2 + uVar5;
      }
      else {
        if (uVar2 != 5) {
          return 0;
        }
        if (param_2 - uVar7 < 4) {
          return 0;
        }
        uVar2 = uVar7 + 4;
      }
    } while (uVar2 < param_2);
  }
  return 0;
}



/* Function: FUN_00018668 @ 00018668 */

undefined4 FUN_00018668(int param_1,uint param_2,uint *param_3)

{
  char cVar1;
  bool bVar2;
  uint uVar3;
  int iVar4;
  uint uVar5;
  char *pcVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  byte *pbVar11;
  bool bVar12;
  uint local_60;
  uint uStack_5c;
  uint local_3c;
  uint local_2c [2];
  
  if (param_2 == 0) {
    return 0;
  }
  uVar9 = 0;
  bVar2 = false;
  local_3c = 0;
  do {
    while( true ) {
      if (param_2 <= uVar9) {
        return 0;
      }
      uVar5 = uVar9 + 1;
      pbVar11 = (byte *)(param_1 + uVar9);
      uVar10 = 0;
      uVar7 = *(byte *)(param_1 + uVar9) & 0x7f;
      if ((*(byte *)(param_1 + uVar9) & 0x80) != 0) {
        uVar9 = 0;
        do {
          uVar8 = uVar9 + 7;
          bVar12 = param_2 <= uVar5;
          uVar5 = uVar5 + 1;
          if (0x3f < uVar8 || bVar12) {
            return 0;
          }
          pbVar11 = pbVar11 + 1;
          uVar3 = *pbVar11 & 0x7f;
          uVar7 = uVar7 | uVar3 << (uVar8 & 0xff);
          uVar10 = uVar10 | uVar3 << (uVar9 - 0x19 & 0xff) | uVar3 >> (0x20 - uVar8 & 0xff);
          uVar9 = uVar8;
        } while ((*pbVar11 & 0x80) != 0);
      }
      uVar9 = uVar7 & 7;
      if (uVar9 != 0) break;
      if (param_2 <= uVar5) {
        return 0;
      }
      pcVar6 = (char *)(param_1 + uVar5);
      uVar7 = 0;
      cVar1 = *(char *)(param_1 + uVar5);
      uVar9 = uVar5;
      while (uVar9 = uVar9 + 1, cVar1 < '\0') {
        uVar7 = uVar7 + 7;
        if (0x3f < uVar7 || param_2 <= uVar9) {
          return 0;
        }
        pcVar6 = pcVar6 + 1;
        cVar1 = *pcVar6;
      }
joined_r0x000187f8:
      if (param_2 <= uVar9) goto LAB_000187c0;
    }
    if (uVar9 == 1) {
      if (param_2 - uVar5 < 8) {
        return 0;
      }
      uVar9 = uVar5 + 8;
      goto joined_r0x000187f8;
    }
    if (uVar9 != 2) {
      if (uVar9 != 5) {
        return 0;
      }
      if (param_2 - uVar5 < 4) {
        return 0;
      }
      uVar9 = uVar5 + 4;
      goto joined_r0x000187f8;
    }
    if (param_2 <= uVar5) {
      return 0;
    }
    uVar9 = uVar5 + 1;
    pbVar11 = (byte *)(param_1 + uVar5);
    local_60 = *(byte *)(param_1 + uVar5) & 0x7f;
    uStack_5c = 0;
    if ((*(byte *)(param_1 + uVar5) & 0x80) != 0) {
      uStack_5c = 0;
      uVar5 = 0;
      do {
        uVar8 = uVar5 + 7;
        bVar12 = param_2 <= uVar9;
        uVar9 = uVar9 + 1;
        if (0x3f < uVar8 || bVar12) {
          return 0;
        }
        pbVar11 = pbVar11 + 1;
        uVar3 = *pbVar11 & 0x7f;
        local_60 = local_60 | uVar3 << (uVar8 & 0xff);
        uStack_5c = uStack_5c | uVar3 << (uVar5 - 0x19 & 0xff) | uVar3 >> (0x20 - uVar8 & 0xff);
        uVar5 = uVar8;
      } while ((*pbVar11 & 0x80) != 0);
    }
    bVar12 = uStack_5c == 0;
    if (uStack_5c == 0) {
      bVar12 = local_60 <= param_2 - uVar9;
    }
    if (!bVar12) {
      return 0;
    }
    if (((uVar7 >> 3 | uVar10 << 0x1d) == 1) &&
       (iVar4 = FUN_000183d4(param_1 + uVar9,local_60,1,local_2c), iVar4 != 0)) {
      if ((bVar2) && (local_2c[0] <= local_3c)) {
        bVar2 = true;
      }
      else {
        bVar2 = true;
        local_3c = local_2c[0];
      }
    }
    uVar9 = uVar9 + local_60;
  } while (uVar9 < param_2);
LAB_000187c0:
  if (!bVar2) {
    return 0;
  }
  *param_3 = local_3c;
  return 1;
}



/* Function: FUN_0001893c @ 0001893c */

bool FUN_0001893c(undefined4 param_1,undefined4 param_2,undefined4 *param_3)

{
  int iVar1;
  bool bVar2;
  undefined1 *local_44;
  undefined4 local_40;
  undefined4 local_3c;
  undefined1 auStack_38 [32];
  
  local_44 = auStack_38;
  local_40 = 0;
  local_3c = 0x20;
  iVar1 = FUN_00018334(&local_44,1,*param_3,0);
  if ((((iVar1 == 0) || (iVar1 = FUN_00018334(&local_44,2,param_3[1],0), iVar1 == 0)) ||
      (iVar1 = FUN_00018334(&local_44,3,param_3[2],0), iVar1 == 0)) ||
     (iVar1 = FUN_00018334(&local_44,4,param_3[3],0), iVar1 == 0)) {
    bVar2 = false;
  }
  else {
    iVar1 = FUN_00018374(param_1,param_2,&local_44);
    bVar2 = iVar1 != 0;
  }
  return bVar2;
}



/* Function: FUN_00018a04 @ 00018a04 */

bool FUN_00018a04(undefined4 param_1,int param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  
  iVar1 = FUN_0001893c(param_1,1,param_2,param_4,param_4);
  if ((((iVar1 != 0) && (iVar1 = FUN_0001893c(param_1,2,param_2 + 0x10), iVar1 != 0)) &&
      (iVar1 = FUN_0001893c(param_1,3,param_2 + 0x20), iVar1 != 0)) &&
     (iVar1 = FUN_00018334(param_1,4,*(undefined4 *)(param_2 + 0x30),0), iVar1 != 0)) {
    if (*(int *)(param_2 + 0x34) == 0) {
      return true;
    }
    iVar1 = FUN_00018334(param_1,5,*(int *)(param_2 + 0x34),0);
    return iVar1 != 0;
  }
  return false;
}



/* Function: FUN_00018aa8 @ 00018aa8 */

undefined4 FUN_00018aa8(int param_1,int param_2,undefined4 param_3)

{
  int iVar1;
  undefined1 *local_70;
  undefined4 local_6c;
  undefined4 local_68;
  int local_64;
  undefined4 local_60;
  undefined4 local_5c;
  undefined1 auStack_58 [80];
  
  local_70 = auStack_58;
  local_6c = 0;
  local_60 = 0;
  local_68 = 0x50;
  if (((param_1 == 0 || param_2 == 0) ||
      (local_64 = param_2, local_5c = param_3, iVar1 = FUN_00018a04(&local_70,param_1), iVar1 == 0))
     || (iVar1 = FUN_00018374(&local_64,1,&local_70), iVar1 == 0)) {
    local_60 = 0;
  }
  return local_60;
}



/* Function: FUN_00018b20 @ 00018b20 */

undefined4 FUN_00018b20(undefined4 param_1,undefined4 *param_2,int param_3,undefined4 param_4)

{
  bool bVar1;
  int iVar2;
  undefined1 *local_284;
  undefined4 local_280;
  undefined4 local_27c;
  undefined1 *local_278;
  undefined4 local_274;
  undefined4 local_270;
  undefined1 *local_26c;
  undefined4 local_268;
  undefined4 local_264;
  undefined1 *local_260;
  undefined4 local_25c;
  undefined4 local_258;
  int local_254;
  undefined4 local_250;
  undefined4 local_24c;
  undefined1 auStack_248 [96];
  undefined1 auStack_1e8 [128];
  undefined1 auStack_168 [160];
  undefined1 auStack_c8 [180];
  
  local_284 = auStack_1e8;
  bVar1 = param_2 == (undefined4 *)0x0 || param_3 == 0;
  local_280 = 0;
  local_274 = 0;
  local_268 = 0;
  local_25c = 0;
  local_250 = 0;
  local_278 = auStack_248;
  local_27c = 0x80;
  local_26c = auStack_168;
  local_270 = 0x60;
  local_260 = auStack_c8;
  local_264 = 0xa0;
  local_258 = 0xb0;
  if (param_2 != (undefined4 *)0x0 && param_3 != 0) {
    local_254 = param_3;
    local_24c = param_4;
    iVar2 = FUN_00018334(&local_284,1,*param_2,bVar1);
    if (((((iVar2 != 0) && (iVar2 = FUN_00018334(&local_284,2,param_2[1],bVar1), iVar2 != 0)) &&
         (iVar2 = FUN_00018334(&local_284,3,param_2[2],bVar1), iVar2 != 0)) &&
        ((iVar2 = FUN_00018334(&local_284,4,param_2[3],bVar1), iVar2 != 0 &&
         (iVar2 = FUN_00018334(&local_284,5,param_2[4],bVar1), iVar2 != 0)))) &&
       ((((iVar2 = FUN_00018334(&local_284,6,param_2[5],bVar1), iVar2 != 0 &&
          ((param_2[6] == 0 || (iVar2 = FUN_00018334(&local_284,7,param_2[7],bVar1), iVar2 != 0))))
         && (iVar2 = FUN_00018334(&local_284,8,param_2[8],0), iVar2 != 0)) &&
        ((param_2[9] == 0 ||
         ((iVar2 = FUN_00018334(&local_284,9,param_2[10],0), iVar2 != 0 &&
          (iVar2 = FUN_00018334(&local_284,10,param_2[0xb],0), iVar2 != 0)))))))) {
      if (param_2[0xc] != 0) {
        iVar2 = FUN_00018a04(&local_278,param_2 + 0xd);
        if (iVar2 == 0) {
          return 0;
        }
        iVar2 = FUN_00018374(&local_284,0xb,&local_278);
        if (iVar2 == 0) {
          return 0;
        }
      }
      iVar2 = FUN_00018334(&local_26c,1,param_2[0x1b],0);
      if ((((iVar2 != 0) && (iVar2 = FUN_00018374(&local_26c,4,&local_284), iVar2 != 0)) &&
          (iVar2 = FUN_00018334(&local_26c,6,param_2[0x1c],0), iVar2 != 0)) &&
         ((iVar2 = FUN_00018334(&local_26c,7,param_2[0x1d],0), iVar2 != 0 &&
          ((param_2[0x1e] == 0 || (iVar2 = FUN_00018334(&local_26c,8,param_2[0x1e],0), iVar2 != 0)))
          ))) {
        iVar2 = FUN_00018334(&local_260,1,param_1,0);
        if ((iVar2 != 0) &&
           ((iVar2 = FUN_00018374(&local_260,3,&local_26c), iVar2 != 0 &&
            (iVar2 = FUN_00018374(&local_254,1,&local_260), iVar2 != 0)))) {
          return local_250;
        }
      }
    }
  }
  return 0;
}



/* Function: FUN_00018e14 @ 00018e14 */

undefined4
FUN_00018e14(undefined4 param_1,undefined4 param_2,int param_3,int param_4,int param_5,
            undefined4 param_6)

{
  int iVar1;
  undefined4 unaff_r5;
  bool bVar2;
  bool bVar3;
  undefined1 *local_98;
  undefined4 local_94;
  undefined4 local_90;
  int local_8c;
  int local_88;
  int local_84;
  undefined1 *local_80;
  undefined4 local_7c;
  undefined4 local_78;
  int local_74;
  undefined4 local_70;
  undefined4 local_6c;
  undefined1 auStack_68 [32];
  undefined1 auStack_48 [52];
  
  bVar2 = param_3 != 0;
  bVar3 = param_5 != 0;
  local_74 = param_5;
  local_6c = param_6;
  if (!bVar2 || !bVar3) {
    unaff_r5 = 1;
  }
  local_98 = auStack_68;
  local_94 = 0;
  if (bVar2 && bVar3) {
    unaff_r5 = 0;
  }
  local_7c = 0;
  local_70 = 0;
  local_80 = auStack_48;
  local_90 = 0x20;
  local_78 = 0x30;
  if ((((bVar2 && bVar3) && (param_4 - 1U < 0x10)) &&
      (local_8c = param_3, local_88 = param_4, local_84 = param_4,
      iVar1 = FUN_00018374(&local_98,1,&local_8c), iVar1 != 0)) &&
     (iVar1 = FUN_00018334(&local_98,5,param_2,unaff_r5), iVar1 != 0)) {
    iVar1 = FUN_00018334(&local_80,1,param_1,0);
    if (((iVar1 != 0) && (iVar1 = FUN_00018374(&local_80,4,&local_98), iVar1 != 0)) &&
       (iVar1 = FUN_00018374(&local_74,1,&local_80), iVar1 != 0)) {
      return local_70;
    }
  }
  return 0;
}



/* Function: FUN_00018f28 @ 00018f28 */

undefined4 * FUN_00018f28(undefined4 *param_1,int param_2)

{
  undefined4 uVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  
  if (param_2 == 0x53) {
    uVar1 = *(undefined4 *)(DAT_00018f88 + 0x18f64);
    uVar2 = *(undefined4 *)(DAT_00018f88 + 0x18f68);
    uVar3 = *(undefined4 *)(DAT_00018f88 + 0x18f6c);
    *param_1 = *(undefined4 *)(DAT_00018f88 + 0x18f60);
    param_1[1] = uVar1;
    param_1[2] = uVar2;
    param_1[3] = uVar3;
    return param_1;
  }
  if (param_2 != 0x43) {
    uVar1 = *(undefined4 *)(DAT_00018f84 + 0x18f4c);
    uVar2 = *(undefined4 *)(DAT_00018f84 + 0x18f50);
    uVar3 = *(undefined4 *)(DAT_00018f84 + 0x18f54);
    *param_1 = *(undefined4 *)(DAT_00018f84 + 0x18f48);
    param_1[1] = uVar1;
    param_1[2] = uVar2;
    param_1[3] = uVar3;
    return param_1;
  }
  uVar1 = *(undefined4 *)(DAT_00018f8c + 0x18f7c);
  uVar2 = *(undefined4 *)(DAT_00018f8c + 0x18f80);
  uVar3 = *(undefined4 *)((int)&DAT_00018f84 + DAT_00018f8c);
  *param_1 = *(undefined4 *)(DAT_00018f8c + 0x18f78);
  param_1[1] = uVar1;
  param_1[2] = uVar2;
  param_1[3] = uVar3;
  return param_1;
}



/* Function: FUN_00018f90 @ 00018f90 */

void FUN_00018f90(undefined4 param_1,int param_2,undefined4 *param_3)

{
  undefined4 uVar1;
  
  memset(param_3,0,0x38);
  param_3[2] = 0xf0;
  param_3[3] = 0xf0;
  *param_3 = 0x10e;
  param_3[1] = 0x10e;
  FUN_00018f28(param_3 + 4,param_1);
  FUN_00018f28(param_3 + 8,param_1);
  uVar1 = 0;
  param_3[0xc] = 0;
  if (param_2 == 0) {
    uVar1 = 5;
  }
  param_3[0xd] = uVar1;
  return;
}



/* Function: FUN_00018ff8 @ 00018ff8 */

void FUN_00018ff8(undefined4 param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,
                 int param_5,undefined4 *param_6)

{
  undefined4 uVar1;
  
  uVar1 = param_4;
  memset(param_6,0,0x7c);
  param_6[0xc] = param_1;
  param_6[1] = 2;
  param_6[2] = 0x1e0;
  param_6[3] = 0x21c;
  param_6[4] = 0x90;
  param_6[7] = 500;
  param_6[8] = 10000;
  *param_6 = 3;
  param_6[10] = 0x80;
  param_6[0xb] = 3;
  param_6[6] = 1;
  param_6[9] = 1;
  FUN_00018f90(0x4c,param_5 == 0,param_6 + 0xd,0x80,uVar1);
  param_6[0x1b] = 3;
  param_6[0x1c] = param_3;
  param_6[0x1d] = param_2;
  param_6[0x1e] = param_4;
  return;
}



/* Function: FUN_0001909c @ 0001909c */

undefined4 FUN_0001909c(void)

{
  return 1;
}



/* Function: FUN_000190a4 @ 000190a4 */

void FUN_000190a4(void)

{
  return;
}



/* Function: FUN_000190a8 @ 000190a8 */

undefined4 FUN_000190a8(void)

{
  return 0;
}



/* Function: FUN_000190b0 @ 000190b0 */

void FUN_000190b0(void)

{
  int iVar1;
  
  if (-1 < *(int *)(DAT_000190f4 + 0x190c0)) {
    close(*(int *)(DAT_000190f4 + 0x190c0));
  }
  iVar1 = DAT_000190f8;
  *(undefined4 *)(DAT_000190f8 + 0x190e0) = 0xffffffff;
  *(undefined4 *)(iVar1 + 0x1b514) = 0;
  *(undefined4 *)(iVar1 + 0x1b50c) = 1;
  *(undefined4 *)(iVar1 + 0x1b518) = 0;
  return;
}



/* Function: FUN_000190fc @ 000190fc */

void FUN_000190fc(void)

{
  int iVar1;
  uint *puVar2;
  int *piVar3;
  uint uVar4;
  bool bVar5;
  
  iVar1 = DAT_00019184;
  DataMemoryBarrier(0xf);
  piVar3 = (int *)(DAT_00019184 + 0x1b550);
  do {
    bVar5 = *piVar3 == 0;
    if (!bVar5) goto LAB_00019130;
    bVar5 = (bool)hasExclusiveAccess(piVar3);
  } while (!bVar5);
  *piVar3 = 1;
  bVar5 = true;
LAB_00019130:
  DataMemoryBarrier(0xf);
  if (bVar5) {
    puVar2 = (uint *)(iVar1 + 0x1b5b4);
    DataMemoryBarrier(0xf);
    do {
      uVar4 = *puVar2;
      bVar5 = (bool)hasExclusiveAccess(puVar2);
    } while (!bVar5);
    *puVar2 = uVar4 + 1;
    *(uint *)(iVar1 + 0x1b5b8) = *(int *)(iVar1 + 0x1b5b8) + (uint)(0xfffffffe < uVar4);
    DataMemoryBarrier(0xf);
  }
  pthread_cond_signal((pthread_cond_t *)(DAT_00019188 + 0x1b5ac));
  return;
}



/* Function: FUN_0001918c @ 0001918c */

void FUN_0001918c(void)

{
  int iVar1;
  ssize_t sVar2;
  int iVar3;
  char *pcVar4;
  int *piVar5;
  char cStack_39;
  char local_38 [20];
  
  piVar5 = (int *)(DAT_00019244 + 0x191a0);
  if (((1 < *(int *)(DAT_00019244 + 0x1b5c8) - 2U) || (iVar1 = *piVar5, iVar1 < 0)) ||
     (sVar2 = read(iVar1,local_38,0x10), iVar1 = DAT_00019248, sVar2 < 1)) {
    return;
  }
  do {
    iVar3 = 0;
    pcVar4 = &cStack_39;
    do {
      while( true ) {
        pcVar4 = pcVar4 + 1;
        iVar3 = iVar3 + 1;
        if (*pcVar4 != 'K') break;
        *(undefined4 *)(iVar1 + 0x1b634) = 1;
        FUN_000190fc();
        if (sVar2 <= iVar3) goto LAB_00019224;
      }
    } while (iVar3 < sVar2);
LAB_00019224:
    sVar2 = read(*piVar5,local_38,0x10);
    if (sVar2 < 1) {
      return;
    }
  } while( true );
}



/* Function: FUN_0001924c @ 0001924c */

void FUN_0001924c(char *param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  char acStack_2a0 [644];
  undefined4 uStack_c;
  undefined4 uStack_8;
  undefined4 uStack_4;
  
  iVar1 = DAT_000192b4;
  if (*(int *)("_ZN24NavigationStatusEndpoint12routeMessageEhtRK10shared_ptrI8IoBufferE" +
              DAT_000192b4 + 0x18) != 0) {
    uStack_c = param_2;
    uStack_8 = param_3;
    uStack_4 = param_4;
    vsnprintf(acStack_2a0,0x280,param_1,&uStack_c);
    (**(code **)("_ZN24NavigationStatusEndpoint12routeMessageEhtRK10shared_ptrI8IoBufferE" +
                iVar1 + 0x18))(DAT_000192b8 + 0x192a4,acStack_2a0);
  }
  return;
}



/* Function: FUN_000192bc @ 000192bc */

undefined4 FUN_000192bc(void)

{
  char *pcVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  int *piVar6;
  
  iVar2 = DAT_00019398;
  piVar6 = (int *)(DAT_00019398 + 0x192cc);
  if (-1 < *piVar6) {
    return 1;
  }
  iVar3 = accept(*(int *)(DAT_00019398 + 0x192d0),(sockaddr *)0x0,(socklen_t *)0x0);
  *piVar6 = iVar3;
  if (iVar3 < 0) {
    return 0;
  }
  uVar4 = fcntl(iVar3,3,0);
  if ((-1 < (int)uVar4) && (iVar3 = fcntl(*piVar6,4,uVar4 | 0x80), -1 < iVar3)) {
    fcntl(*piVar6,2);
    iVar3 = DAT_0001939c;
    iVar5 = *(int *)("_ZN24NavigationStatusEndpoint12routeMessageEhtRK10shared_ptrI8IoBufferE" +
                    iVar2 + 0x3c);
    pcVar1 = "_ZN13MessageRouter15registerServiceEP20ProtocolEndpointBase" + iVar2;
    pcVar1[0] = '\x01';
    pcVar1[1] = '\0';
    pcVar1[2] = '\0';
    pcVar1[3] = '\0';
    pcVar1 = "_ZN24NavigationStatusEndpoint12routeMessageEhtRK10shared_ptrI8IoBufferE" +
             iVar2 + 0x40;
    pcVar1[0] = '\x01';
    pcVar1[1] = '\0';
    pcVar1[2] = '\0';
    pcVar1[3] = '\0';
    *(uint *)("_ZN13MessageRouter15registerServiceEP20ProtocolEndpointBase" + iVar2 + 4) =
         (uint)(iVar5 == 0 || iVar5 == 3);
    FUN_0001924c(iVar3 + 0x19358);
    return 1;
  }
  piVar6 = (int *)(DAT_000193a0 + 0x19384);
  close(*piVar6);
  *piVar6 = -1;
  return 0;
}



/* Function: FUN_000193a4 @ 000193a4 */

undefined4 FUN_000193a4(uint param_1,int param_2,uint param_3)

{
  longlong lVar1;
  longlong lVar2;
  int iVar3;
  int iVar4;
  uint uVar5;
  int iVar6;
  ssize_t sVar7;
  int *piVar8;
  int iVar9;
  uint uVar10;
  uint extraout_r1;
  uint uVar11;
  uint uVar12;
  undefined *extraout_r3;
  undefined *puVar13;
  uint uVar14;
  uint unaff_r8;
  bool bVar15;
  undefined *local_40;
  pollfd local_30;
  
  iVar4 = clock_gettime(2,(timespec *)&local_30);
  iVar3 = local_30.fd;
  if (iVar4 == 0) {
    lVar1 = FUN_0001b350(local_30._4_4_,(int)local_30._4_4_ >> 0x1f,1000,0);
    lVar1 = (ulonglong)(uint)iVar3 * 1000000 + lVar1;
  }
  else {
    lVar1 = 0;
  }
  iVar4 = DAT_000195f0;
  iVar3 = DAT_000195ec;
  uVar10 = (uint)((ulonglong)lVar1 >> 0x20);
  uVar5 = (uint)lVar1;
  if (param_3 == 0) {
    return 1;
  }
  if ((*(int *)("AA_CLUSTER_OUTPUT" + DAT_000195e8 + 0xc) != 0) ||
     (*(int *)("AA_CLUSTER_OUTPUT" + DAT_000195e8 + 4) != 0)) {
    return 0;
  }
  if (lVar1 != 0) {
    unaff_r8 = 1;
  }
  local_40 = &DAT_000f4240;
  if (lVar1 == 0) {
    unaff_r8 = 0;
  }
  uVar14 = 0;
  while( true ) {
    FUN_0001918c();
    iVar6 = clock_gettime(2,(timespec *)&local_30);
    iVar9 = local_30.fd;
    uVar11 = extraout_r1;
    if (iVar6 != 0) {
      uVar11 = 0;
    }
    lVar2 = (ulonglong)uVar11 << 0x20;
    puVar13 = extraout_r3;
    if (iVar6 == 0) {
      lVar2 = FUN_0001b350(local_30._4_4_,(int)local_30._4_4_ >> 0x1f,1000,0);
      lVar2 = (ulonglong)(uint)iVar9 * 1000000 + lVar2;
      puVar13 = local_40;
    }
    uVar12 = (uint)((ulonglong)lVar2 >> 0x20);
    uVar11 = (uint)lVar2;
    bVar15 = uVar12 <= uVar10;
    if (uVar10 == uVar12) {
      bVar15 = uVar11 <= uVar5;
    }
    if (!bVar15 || lVar1 == lVar2) {
      puVar13 = (undefined *)(unaff_r8 & 1);
    }
    if (bVar15 && (uVar10 != uVar12 || uVar5 != uVar11)) {
      puVar13 = (undefined *)0x0;
    }
    if ((puVar13 != (undefined *)0x0) &&
       (uVar12 != uVar10 + (uVar11 < uVar5) || 4999999 < uVar11 - uVar5)) {
      return 0;
    }
    sVar7 = write(param_1,(void *)(param_2 + uVar14),param_3 - uVar14);
    if (sVar7 < 0) {
      piVar8 = (int *)__get_errno_ptr();
      if (*piVar8 != 4) {
        if (*piVar8 != 0xb) {
          return 0;
        }
        if (*(int *)(&DAT_0001b870 + iVar3) - 2U < 2) {
          local_30.events = 7;
        }
        else {
          local_30.events = 2;
        }
        local_30.revents = 0;
        local_30.fd = param_1;
        iVar9 = poll(&local_30,1,0x32);
        if ((iVar9 < 0) && (*piVar8 != 4)) {
          return 0;
        }
      }
    }
    else {
      if (sVar7 == 0) {
        return 0;
      }
      uVar14 = uVar14 + sVar7;
    }
    if (param_3 <= uVar14) {
      return 1;
    }
    if (*(int *)("_ZN13MessageRouter13queueOutgoingEhPvj" + iVar4) != 0) break;
    if (*(int *)("legacy" + iVar4) != 0) {
      return 0;
    }
  }
  return 0;
}



/* Function: FUN_000195f4 @ 000195f4 */

bool FUN_000195f4(int param_1,uint *param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  uint uVar2;
  uint uVar3;
  int iVar4;
  undefined4 *puVar5;
  
  uVar2 = 0x800000 - param_1;
  uVar3 = *param_2;
  puVar5 = (undefined4 *)(DAT_00019668 + 0x19618);
  if (uVar3 <= uVar2) {
    uVar2 = uVar3;
  }
  iVar4 = DAT_0001966c + 0x19624;
  iVar1 = FUN_000193a4(*puVar5,iVar4 + param_1,uVar2,uVar3,param_4);
  if (iVar1 != 0) {
    if (*param_2 == uVar2) {
      return true;
    }
    iVar1 = FUN_000193a4(*puVar5,iVar4,*param_2 - uVar2);
    return iVar1 != 0;
  }
  return false;
}



/* Function: FUN_00019670 @ 00019670 */

bool FUN_00019670(undefined4 *param_1,int param_2,byte param_3)

{
  char cVar1;
  undefined4 uVar2;
  int iVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  undefined4 *puVar6;
  undefined4 uVar7;
  undefined4 uVar8;
  undefined4 uVar9;
  undefined4 uVar10;
  bool bVar11;
  undefined1 local_48;
  undefined1 local_47;
  undefined1 local_46;
  undefined1 local_45;
  undefined1 local_44;
  undefined1 local_43;
  byte local_42;
  undefined1 local_41;
  undefined1 local_40;
  undefined1 local_3f;
  undefined1 local_3e;
  undefined1 local_3d;
  undefined1 local_3c;
  undefined1 local_3b;
  undefined1 local_3a;
  undefined1 local_39;
  undefined1 local_38;
  undefined1 local_37;
  undefined1 local_36;
  undefined1 local_35;
  undefined1 local_34;
  undefined1 local_33;
  undefined1 local_32;
  undefined1 local_31;
  undefined1 local_30;
  undefined1 local_2f;
  undefined1 local_2e;
  undefined1 local_2d;
  undefined1 local_2c;
  undefined1 local_2b;
  undefined1 local_2a;
  undefined1 local_29;
  
  if (*(int *)("0123456789abcdef" + DAT_000198ec + 4) - 2U < 2) {
    if (*(char *)(param_1 + 7) == '\0') {
      cVar1 = *(char *)((int)param_1 + 0x1d);
      local_43 = 2;
    }
    else {
      cVar1 = *(char *)((int)param_1 + 0x1d);
      local_43 = 1;
    }
    uVar7 = param_1[1];
    uVar9 = param_1[2];
    uVar2 = param_1[3];
    uVar4 = 0x20;
    uVar5 = param_1[5];
    if (cVar1 != '\0') {
      param_3 = param_3 | 1;
    }
    uVar8 = param_1[6];
    uVar10 = param_1[4];
    local_3d = (undefined1)uVar8;
    local_39 = (undefined1)uVar7;
    local_35 = (undefined1)uVar2;
    local_31 = (undefined1)uVar9;
    local_2c = (undefined1)((uint)uVar10 >> 0x18);
    local_2b = (undefined1)((uint)uVar10 >> 0x10);
    local_2a = (undefined1)((uint)uVar10 >> 8);
    local_2d = (undefined1)uVar5;
    local_30 = (undefined1)((uint)uVar5 >> 0x18);
    local_2f = (undefined1)((uint)uVar5 >> 0x10);
    local_2e = (undefined1)((uint)uVar5 >> 8);
    local_41 = 0x20;
    local_40 = (undefined1)((uint)uVar8 >> 0x18);
    local_3e = (undefined1)((uint)uVar8 >> 8);
    local_3c = (undefined1)((uint)uVar7 >> 0x18);
    local_3a = (undefined1)((uint)uVar7 >> 8);
    local_3f = (undefined1)((uint)uVar8 >> 0x10);
    local_3b = (undefined1)((uint)uVar7 >> 0x10);
    local_38 = (undefined1)((uint)uVar2 >> 0x18);
    local_37 = (undefined1)((uint)uVar2 >> 0x10);
    local_36 = (undefined1)((uint)uVar2 >> 8);
    local_34 = (undefined1)((uint)uVar9 >> 0x18);
    local_33 = (undefined1)((uint)uVar9 >> 0x10);
    local_32 = (undefined1)((uint)uVar9 >> 8);
    local_44 = 2;
    local_29 = (undefined1)uVar10;
    local_42 = param_3;
  }
  else {
    if (*(char *)(param_1 + 7) == '\0') {
      cVar1 = *(char *)((int)param_1 + 0x1d);
      local_43 = 2;
    }
    else {
      cVar1 = *(char *)((int)param_1 + 0x1d);
      local_43 = 1;
    }
    uVar9 = param_1[6];
    uVar7 = param_1[1];
    if (cVar1 != '\0') {
      param_3 = param_3 | 1;
    }
    uVar2 = param_1[2];
    uVar4 = 0x18;
    uVar5 = param_1[3];
    local_3d = (undefined1)uVar9;
    local_39 = (undefined1)uVar7;
    local_35 = (undefined1)uVar5;
    local_31 = (undefined1)uVar2;
    local_41 = 0x18;
    local_40 = (undefined1)((uint)uVar9 >> 0x18);
    local_3f = (undefined1)((uint)uVar9 >> 0x10);
    local_3e = (undefined1)((uint)uVar9 >> 8);
    local_3b = (undefined1)((uint)uVar7 >> 0x10);
    local_44 = 1;
    local_3c = (undefined1)((uint)uVar7 >> 0x18);
    local_3a = (undefined1)((uint)uVar7 >> 8);
    local_38 = (undefined1)((uint)uVar5 >> 0x18);
    local_37 = (undefined1)((uint)uVar5 >> 0x10);
    local_36 = (undefined1)((uint)uVar5 >> 8);
    local_34 = (undefined1)((uint)uVar2 >> 0x18);
    local_33 = (undefined1)((uint)uVar2 >> 0x10);
    local_32 = (undefined1)((uint)uVar2 >> 8);
    local_42 = param_3;
  }
  local_45 = 0x31;
  local_46 = 0x46;
  local_47 = 0x41;
  local_48 = 0x41;
  puVar6 = (undefined4 *)(DAT_000198f0 + 0x19788);
  iVar3 = FUN_000193a4(*puVar6,&local_48,uVar4);
  bVar11 = false;
  if (iVar3 != 0) {
    if (param_2 == 0) {
      iVar3 = FUN_000195f4(*param_1,param_1 + 1);
      bVar11 = iVar3 != 0;
    }
    else {
      iVar3 = FUN_000193a4(*puVar6,param_2,param_1[1]);
      bVar11 = false;
      if (iVar3 != 0) {
        bVar11 = true;
      }
    }
  }
  return bVar11;
}



/* Function: FUN_000198f4 @ 000198f4 */

undefined4 FUN_000198f4(int param_1)

{
  bool bVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  uint __n;
  pthread_mutex_t *__mutex;
  undefined4 *puVar5;
  uint *puVar6;
  undefined4 local_440;
  uint local_43c;
  undefined4 uStack_438;
  undefined4 uStack_434;
  undefined4 uStack_430;
  undefined4 uStack_42c;
  undefined4 uStack_428;
  undefined4 local_424;
  undefined1 auStack_420 [1028];
  
  iVar2 = DAT_00019a50;
  puVar5 = (undefined4 *)(DAT_00019a50 + 0x19910);
  __mutex = (pthread_mutex_t *)
            ("stream: oversized access unit length=%u max=%u" + DAT_00019a50 + 0x1c);
  pthread_mutex_lock(__mutex);
  __n = *(uint *)(iVar2 + 0x19d18);
  if (__n == 0) {
    pthread_mutex_unlock(__mutex);
    return 0;
  }
  memcpy(auStack_420,(void *)(iVar2 + 0x19918),__n);
  pthread_mutex_unlock(__mutex);
  local_440 = 0;
  uStack_438 = 0;
  uStack_434 = 0;
  uStack_430 = 0;
  uStack_42c = 0;
  uStack_428 = 0;
  local_424 = 1;
  local_43c = __n;
  if (param_1 == 0) {
    iVar3 = FUN_000193a4(*puVar5,auStack_420,__n);
    if (iVar3 == 0) {
      return 0;
    }
    puVar6 = (uint *)(
                     "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
                     + iVar2);
    DataMemoryBarrier(0xf);
    do {
      uVar4 = *puVar6;
      bVar1 = (bool)hasExclusiveAccess(puVar6);
    } while (!bVar1);
    *puVar6 = uVar4 + __n;
    *(uint *)(
             "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
             + iVar2 + 4) =
         *(int *)(
                 "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
                 + iVar2 + 4) + (uint)CARRY4(uVar4,__n);
  }
  else {
    iVar3 = FUN_00019670(&local_440);
    if (iVar3 == 0) {
      return 0;
    }
    puVar6 = (uint *)(
                     "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
                     + iVar2);
    DataMemoryBarrier(0xf);
    if (*(int *)("stream: loopback ready port=%u mode=%s queue=%u bytes packets=%u max=%u" +
                iVar2 + 0x30) - 2U < 2) {
      iVar3 = 0x20;
    }
    else {
      iVar3 = 0x18;
    }
    do {
      uVar4 = *puVar6;
      bVar1 = (bool)hasExclusiveAccess(puVar6);
    } while (!bVar1);
    *puVar6 = uVar4 + iVar3 + __n;
    *(uint *)(
             "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
             + iVar2 + 4) =
         *(int *)(
                 "stream: queue saturated after %u ms; marking discontinuity packets=%u bytes=%u length=%u"
                 + iVar2 + 4) + (uint)CARRY4(uVar4,iVar3 + __n);
  }
  DataMemoryBarrier(0xf);
  return 1;
}



/* Function: FUN_00019f08 @ 00019f08 */

undefined4 FUN_00019f08(undefined4 param_1,uint param_2,undefined4 param_3)

{
  ushort uVar1;
  int iVar2;
  int iVar3;
  undefined4 *puVar4;
  __start_routine *__start_routine;
  undefined4 uVar5;
  pthread_mutex_t *__mutex;
  pthread_mutex_t *__mutex_00;
  uint uVar6;
  bool bVar7;
  undefined4 local_3c;
  sockaddr local_38;
  
  iVar2 = DAT_0001a1c4;
  bVar7 = param_2 == 3;
  if (param_2 < 4) {
    bVar7 = *(int *)(DAT_0001a1c4 + 0x1c34c) == 0;
  }
  if (bVar7) {
    __mutex_00 = (pthread_mutex_t *)(DAT_0001a1c4 + 0x1c378);
    __mutex = (pthread_mutex_t *)(DAT_0001a1c4 + 0x1c370);
    pthread_mutex_lock(__mutex);
    *(undefined4 *)(iVar2 + 0x1c368) = param_1;
    *(uint *)(iVar2 + 0x1c344) = param_2;
    *(undefined4 *)(iVar2 + 0x1c388) = param_3;
    pthread_mutex_lock(__mutex_00);
    *(undefined4 *)(iVar2 + 0x1a324) = 0;
    pthread_mutex_unlock(__mutex_00);
    *(undefined4 *)(iVar2 + 0x1c350) = 0;
    *(undefined4 *)(iVar2 + 0x1c32c) = 0;
    *(undefined4 *)(iVar2 + 0x1c354) = 0;
    *(undefined4 *)(iVar2 + 0x1c330) = 0;
    *(undefined4 *)(iVar2 + 0x1c334) = 0;
    *(undefined4 *)(iVar2 + 0x1c338) = 0;
    *(undefined4 *)(iVar2 + 0x1c33c) = 0;
    *(undefined4 *)(iVar2 + 0x1c358) = 0;
    *(undefined4 *)(iVar2 + 0x1c35c) = 0;
    *(undefined4 *)(iVar2 + 0x1c360) = 0;
    *(undefined4 *)(iVar2 + 0x1c364) = 0;
    *(undefined4 *)(iVar2 + 0x1c340) = 1;
    *(undefined4 *)(iVar2 + 0x1c348) = 1;
    memset((void *)(iVar2 + 0x1c38c),0,0x40);
    pthread_mutex_unlock(__mutex);
    local_3c = 1;
    iVar3 = socket(2,1,0);
    *(int *)(iVar2 + 0x19f20) = iVar3;
    if (iVar3 < 0) {
      puVar4 = (undefined4 *)__get_errno_ptr();
      FUN_0001924c(DAT_0001a1d0 + 0x1a0c4,*puVar4);
    }
    else {
      setsockopt(iVar3,0xffff,4,&local_3c,4);
      uVar6 = *(uint *)(iVar2 + 0x1c368) & 0xffff;
      local_38.sa_data[6] = '\0';
      local_38.sa_data[7] = '\0';
      local_38.sa_data[8] = '\0';
      local_38.sa_data[9] = '\0';
      local_38.sa_data[2] = '\x7f';
      local_38.sa_data[3] = '\0';
      local_38.sa_data[4] = '\0';
      local_38.sa_data[5] = '\x01';
      local_38.sa_data[10] = '\0';
      local_38.sa_data[0xb] = '\0';
      local_38.sa_data[0xc] = '\0';
      local_38.sa_data[0xd] = '\0';
      uVar1 = (ushort)(uVar6 << 8) | (ushort)(uVar6 >> 8);
      local_38.sa_data[0] = (char)uVar1;
      local_38.sa_data[1] = (char)(uVar1 >> 8);
      local_38.sa_family = 0x200;
      iVar3 = bind(*(int *)(iVar2 + 0x19f20),&local_38,0x10);
      if ((iVar3 == 0) && (iVar3 = listen(*(int *)(iVar2 + 0x19f20),1), iVar3 == 0)) {
        uVar6 = fcntl(*(int *)(iVar2 + 0x19f20),3,0);
        if (-1 < (int)uVar6) {
          fcntl(*(int *)(iVar2 + 0x19f20),4,uVar6 | 0x80);
        }
        iVar2 = DAT_0001a1d4;
        fcntl(*(int *)(DAT_0001a1d4 + 0x1a108),2,1);
        __start_routine = (__start_routine *)(DAT_0001a1d8 + 0x1a128);
        *(undefined4 *)(iVar2 + 0x1c534) = 1;
        iVar3 = pthread_create((pthread_t *)(iVar2 + 0x1c554),(pthread_attr_t *)0x0,__start_routine,
                               (void *)0x0);
        if (iVar3 == 0) {
          if (*(int *)(iVar2 + 0x1c52c) - 2U < 2) {
            iVar3 = DAT_0001a1e4 + 0x1a190;
          }
          else if (param_2 == 1) {
            iVar3 = (int)&DAT_0001a1c4 + DAT_0001a1ec;
          }
          else {
            iVar3 = DAT_0001a1dc + 0x1a160;
          }
          FUN_0001924c(DAT_0001a1e0 + 0x1a17c,*(undefined4 *)(iVar2 + 0x1c550),iVar3,0x800000,0x100,
                       0x200000);
          return 1;
        }
        FUN_0001924c(DAT_0001a1e8 + 0x1a19c);
        *(undefined4 *)(iVar2 + 0x1c534) = 0;
        close(*(int *)(iVar2 + 0x1a108));
        *(undefined4 *)(iVar2 + 0x1a108) = 0xffffffff;
        return 0;
      }
      iVar2 = DAT_0001a1c8;
      uVar5 = *(undefined4 *)(DAT_0001a1c8 + 0x1c4cc);
      puVar4 = (undefined4 *)__get_errno_ptr();
      FUN_0001924c(DAT_0001a1cc + 0x1a09c,uVar5,*puVar4);
      close(*(int *)(iVar2 + 0x1a084));
      *(undefined4 *)(iVar2 + 0x1a084) = 0xffffffff;
    }
  }
  return 0;
}



/* Function: FUN_0001a1f0 @ 0001a1f0 */

void FUN_0001a1f0(void)

{
  int iVar1;
  int *piVar2;
  pthread_mutex_t *__mutex;
  
  iVar1 = DAT_0001a28c;
  piVar2 = (int *)(DAT_0001a28c + 0x1a200);
  if (*(int *)(DAT_0001a28c + 0x1c630) == 0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_0001a28c + 0x1c654);
  *(undefined4 *)(DAT_0001a28c + 0x1c630) = 0;
  pthread_mutex_lock(__mutex);
  *(undefined4 *)(iVar1 + 0x1c648) = 1;
  pthread_cond_signal((pthread_cond_t *)(iVar1 + 0x1c664));
  pthread_mutex_unlock(__mutex);
  if (-1 < *piVar2) {
    shutdown(*piVar2,2);
  }
  iVar1 = DAT_0001a290;
  pthread_join(*(pthread_t *)(DAT_0001a290 + 0x1c6b0),(void **)0x0);
  if (-1 < *(int *)(iVar1 + 0x1a264)) {
    close(*(int *)(iVar1 + 0x1a264));
  }
  *(undefined4 *)((int)&DAT_0001a28c + DAT_0001a294) = 0xffffffff;
  return;
}



/* Function: FUN_0001a298 @ 0001a298 */

void FUN_0001a298(char *param_1,uint param_2,int param_3,undefined4 param_4,undefined4 param_5,
                 undefined4 param_6)

{
  int *piVar1;
  bool bVar2;
  longlong lVar3;
  __time_t _Var4;
  int iVar5;
  pthread_mutex_t *ppVar6;
  uint *puVar7;
  int iVar8;
  undefined4 uVar9;
  char *pcVar10;
  pthread_cond_t *__cond;
  undefined1 uVar11;
  int iVar12;
  uint uVar13;
  void *__dest;
  undefined4 unaff_r8;
  uint uVar14;
  undefined4 unaff_r9;
  int iVar15;
  uint *puVar16;
  uint uVar17;
  int iVar18;
  int iVar19;
  timespec local_30;
  
  iVar5 = clock_gettime(2,&local_30);
  _Var4 = local_30.tv_sec;
  if (iVar5 != 0) {
    unaff_r8 = 0;
    unaff_r9 = 0;
  }
  lVar3 = CONCAT44(unaff_r9,unaff_r8);
  if (iVar5 == 0) {
    lVar3 = FUN_0001b350(local_30.tv_nsec,local_30.tv_nsec >> 0x1f,1000,0);
    lVar3 = (ulonglong)(uint)_Var4 * 1000000 + lVar3;
  }
  iVar5 = DAT_0001a888;
  if (param_2 == 0 || param_1 == (char *)0x0) {
    return;
  }
  if (*(int *)(DAT_0001a888 + 0x1c740) == 0) {
    return;
  }
  if (param_3 == 0) {
    if (*(int *)(DAT_0001a888 + 0x1c744) == 0) {
      puVar7 = (uint *)(DAT_0001a888 + 0x1c788);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        piVar1 = (int *)(DAT_0001a888 + 0x1c78c);
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1c78c) = *piVar1 + (uint)CARRY4(uVar17,param_2);
LAB_0001a724:
      DataMemoryBarrier(0xf);
      return;
    }
    pthread_mutex_lock((pthread_mutex_t *)(DAT_0001a888 + 0x1c764));
    if (0x200000 < param_2) goto LAB_0001a344;
  }
  else {
    if (0x400 < param_2) {
      puVar16 = (uint *)(DAT_0001a888 + 0x1c7a0);
      puVar7 = (uint *)(DAT_0001a888 + 0x1c798);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        piVar1 = (int *)(DAT_0001a888 + 0x1c79c);
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + 1;
      *(uint *)(iVar5 + 0x1c79c) = *piVar1 + (uint)(0xfffffffe < uVar17);
      DataMemoryBarrier(0xf);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar16;
        iVar8 = *(int *)(iVar5 + 0x1c7a4);
        bVar2 = (bool)hasExclusiveAccess(puVar16);
      } while (!bVar2);
      *puVar16 = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1c7a4) = iVar8 + (uint)CARRY4(uVar17,param_2);
      puVar7 = (uint *)(iVar5 + 0x1c7a8);
      DataMemoryBarrier(0xf);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + 1;
      *(uint *)(iVar5 + 0x1c7ac) = *(int *)(iVar5 + 0x1c7ac) + (uint)(0xfffffffe < uVar17);
      goto LAB_0001a724;
    }
    ppVar6 = (pthread_mutex_t *)(DAT_0001a888 + 0x1c76c);
    pthread_mutex_lock(ppVar6);
    memcpy((void *)(iVar5 + 0x1a318),param_1,param_2);
    *(uint *)(iVar5 + 0x1a718) = param_2;
    pthread_mutex_unlock(ppVar6);
    if (*(int *)(iVar5 + 0x1c744) == 0) {
      return;
    }
    pthread_mutex_lock((pthread_mutex_t *)(iVar5 + 0x1c764));
  }
  if (((0xff < *(uint *)(DAT_0001a8a0 + 0x1c9b8)) ||
      (0x800000U - *(int *)(DAT_0001a8a0 + 0x1c9bc) < param_2)) &&
     (iVar19 = clock_gettime(0,&local_30), iVar8 = DAT_0001a8a8, iVar5 = DAT_0001a8a4, iVar19 == 0))
  {
    iVar19 = local_30.tv_nsec + 200000000;
    if (999999999 < local_30.tv_nsec + 200000000) {
      local_30.tv_sec = local_30.tv_sec + 1;
      iVar19 = local_30.tv_nsec + -800000000;
    }
    local_30.tv_nsec = iVar19;
    ppVar6 = (pthread_mutex_t *)(DAT_0001a8a4 + 0x1ca7c);
    __cond = (pthread_cond_t *)(DAT_0001a8a4 + 0x1ca8c);
    iVar19 = 0;
    while (((0xff < *(uint *)(iVar8 + 0x1ca4c) || (0x800000U - *(int *)(iVar8 + 0x1ca50) < param_2))
           && ((*(int *)(iVar5 + 0x1ca58) != 0 &&
               (*(int *)(iVar5 + 0x1ca70) == 0 && iVar19 != 0x104))))) {
      iVar19 = pthread_cond_timedwait(__cond,ppVar6,&local_30);
    }
  }
LAB_0001a344:
  iVar8 = DAT_0001a8b4;
  iVar5 = DAT_0001a88c;
  iVar19 = DAT_0001a88c + 0x1a350;
  if ((*(int *)(DAT_0001a88c + 0x1c780) == 0) || (*(int *)(DAT_0001a88c + 0x1c798) != 0)) {
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_0001a8ac + 0x1cb90));
  }
  else {
    if (param_2 < 0x200001) {
      uVar17 = *(uint *)(DAT_0001a88c + 0x1c768);
      iVar12 = *(int *)(DAT_0001a88c + 0x1c76c);
      if ((uVar17 < 0x100) && (param_2 <= 0x800000U - iVar12)) {
        iVar15 = *(int *)(DAT_0001a88c + 0x1c764);
        iVar18 = *(int *)(DAT_0001a88c + 0x1c770);
        *(longlong *)(iVar19 + (iVar15 + 0x21) * 0x20) = lVar3;
        iVar19 = iVar19 + iVar15 * 0x20;
        *(uint *)(iVar19 + 0x414) = param_2;
        *(undefined4 *)(iVar19 + 0x418) = param_5;
        *(undefined4 *)(iVar19 + 0x41c) = param_6;
        iVar8 = *(int *)(iVar5 + 0x1c774);
        *(int *)(iVar19 + 0x410) = iVar18;
        *(bool *)(iVar19 + 0x42c) = param_3 != 0;
        uVar9 = *(undefined4 *)(iVar5 + 0x1c774);
        *(int *)(iVar5 + 0x1c774) = iVar8 + 1;
        *(undefined4 *)(iVar19 + 0x428) = uVar9;
        uVar11 = 0;
        if (param_3 == 0) {
          if (param_2 < 4) {
            uVar11 = 0;
          }
          else {
            pcVar10 = param_1;
            iVar5 = 0;
            do {
              iVar8 = iVar5 + 1;
              if ((*pcVar10 == '\0') && (pcVar10[1] == '\0')) {
                if (pcVar10[2] == '\x01') {
                  iVar19 = 3;
                }
                else {
                  if ((pcVar10[2] != '\0') || (pcVar10[3] != '\x01')) goto LAB_0001a414;
                  iVar19 = 4;
                }
                if (((uint)(iVar19 + iVar5) < param_2) && ((param_1[iVar19 + iVar5] & 0x1fU) == 5))
                {
                  uVar11 = 1;
                  goto LAB_0001a46c;
                }
              }
LAB_0001a414:
              pcVar10 = pcVar10 + 1;
              iVar5 = iVar8;
            } while (iVar8 != param_2 - 3);
            uVar11 = 0;
          }
        }
LAB_0001a46c:
        uVar14 = 0x800000 - iVar18;
        *(undefined1 *)((int)&DAT_0001a8ac + DAT_0001a890 + iVar15 * 0x20 + 1) = uVar11;
        if (param_2 < uVar14) {
          memcpy((void *)(DAT_0001a8bc + 0x1a864 + iVar18),param_1,param_2);
        }
        else {
          __dest = (void *)(DAT_0001a894 + 0x1a4a0);
          memcpy((void *)((int)__dest + iVar18),param_1,uVar14);
          if (uVar14 < param_2) {
            memcpy(__dest,param_1 + uVar14,param_2 - uVar14);
          }
        }
        iVar8 = DAT_0001a89c;
        iVar5 = DAT_0001a898;
        uVar13 = param_2 + iVar12;
        puVar7 = (uint *)(DAT_0001a89c + 0x1c968);
        uVar14 = *(uint *)(DAT_0001a898 + 0x1c984);
        *(uint *)(DAT_0001a898 + 0x1c8f8) = uVar13;
        *(uint *)(iVar5 + 0x1c8fc) = param_2 + iVar18 & 0x7fffff;
        if (uVar14 < uVar13) {
          *(uint *)(iVar5 + 0x1c984) = uVar13;
        }
        *(uint *)(iVar5 + 0x1c8f0) = iVar15 + 1U & 0xff;
        *(uint *)(iVar5 + 0x1c8f4) = uVar17 + 1;
        uVar17 = *puVar7;
        iVar5 = *(int *)(iVar8 + 0x1c96c);
        *puVar7 = uVar17 + 1;
        *(uint *)(iVar8 + 0x1c96c) = iVar5 + (uint)(0xfffffffe < uVar17);
        pthread_cond_signal((pthread_cond_t *)(iVar8 + 0x1c94c));
        pthread_mutex_unlock((pthread_mutex_t *)(iVar8 + 0x1c93c));
        return;
      }
      uVar14 = *(uint *)(DAT_0001a8b4 + 0x1cc80);
      piVar1 = (int *)(DAT_0001a8b4 + 0x1cc84);
      ppVar6 = (pthread_mutex_t *)(DAT_0001a8b4 + 0x1cc44);
      uVar13 = *(uint *)(DAT_0001a8b4 + 0x1cc78);
      iVar5 = *(int *)(DAT_0001a8b4 + 0x1cc7c);
      *(uint *)(DAT_0001a8b4 + 0x1cc80) = uVar14 + param_2;
      *(uint *)(iVar8 + 0x1cc84) = *piVar1 + (uint)CARRY4(uVar14,param_2);
      *(uint *)(iVar8 + 0x1cc78) = uVar13 + 1;
      *(uint *)(iVar8 + 0x1cc7c) = iVar5 + (uint)(0xfffffffe < uVar13);
      pthread_mutex_unlock(ppVar6);
      FUN_0001924c(DAT_0001a8b8 + 0x1a844,200,uVar17,iVar12,param_2);
    }
    else {
      uVar17 = *(uint *)(DAT_0001a88c + 0x1c7e0);
      iVar8 = *(int *)(DAT_0001a88c + 0x1c7e4);
      ppVar6 = (pthread_mutex_t *)(DAT_0001a88c + 0x1c7a4);
      uVar14 = *(uint *)(DAT_0001a88c + 0x1c7d8);
      piVar1 = (int *)(DAT_0001a88c + 0x1c7dc);
      uVar13 = *(uint *)(DAT_0001a88c + 0x1c7e8);
      iVar19 = *(int *)(DAT_0001a88c + 0x1c7ec);
      *(uint *)(DAT_0001a88c + 0x1c7d8) = uVar14 + 1;
      *(uint *)(iVar5 + 0x1c7dc) = *piVar1 + (uint)(0xfffffffe < uVar14);
      *(uint *)(iVar5 + 0x1c7e8) = uVar13 + 1;
      *(uint *)(iVar5 + 0x1c7ec) = iVar19 + (uint)(0xfffffffe < uVar13);
      *(uint *)(iVar5 + 0x1c7e0) = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1c7e4) = iVar8 + (uint)CARRY4(uVar17,param_2);
      pthread_mutex_unlock(ppVar6);
      FUN_0001924c(DAT_0001a8b0 + 0x1a7a4,param_2,0x200000);
    }
    if (param_3 == 0) {
      FUN_000190fc();
    }
  }
  return;
}



/* Function: FUN_0001a8c0 @ 0001a8c0 */

bool FUN_0001a8c0(void)

{
  int *piVar1;
  bool bVar2;
  
  DataMemoryBarrier(0xf);
  piVar1 = (int *)(DAT_0001a900 + 0x1cd0c);
  do {
    bVar2 = *piVar1 == 1;
    if (!bVar2) goto LAB_0001a8f0;
    bVar2 = (bool)hasExclusiveAccess(piVar1);
  } while (!bVar2);
  *piVar1 = 2;
  bVar2 = true;
LAB_0001a8f0:
  DataMemoryBarrier(0xf);
  return bVar2;
}



/* Function: FUN_0001a904 @ 0001a904 */

void FUN_0001a904(void)

{
  bool bVar1;
  
  do {
    bVar1 = (bool)hasExclusiveAccess((undefined4 *)(DAT_0001a930 + 0x1cd4c));
  } while (!bVar1);
  *(undefined4 *)(DAT_0001a930 + 0x1cd4c) = 0;
  DataMemoryBarrier(0xf);
  return;
}



/* Function: FUN_0001a934 @ 0001a934 */

void FUN_0001a934(undefined4 *param_1)

{
  int iVar1;
  undefined4 uVar2;
  undefined4 uVar3;
  undefined4 uVar4;
  pthread_mutex_t *__mutex;
  
  iVar1 = DAT_0001a994;
  if (param_1 == (undefined4 *)0x0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_0001a994 + 0x1cda4);
  pthread_mutex_lock(__mutex);
  uVar2 = *(undefined4 *)(iVar1 + 0x1cdc4);
  uVar3 = *(undefined4 *)(iVar1 + 0x1cdc8);
  uVar4 = *(undefined4 *)(iVar1 + 0x1cdcc);
  *param_1 = *(undefined4 *)(iVar1 + 0x1cdc0);
  param_1[1] = uVar2;
  param_1[2] = uVar3;
  param_1[3] = uVar4;
  uVar2 = *(undefined4 *)(iVar1 + 0x1cdd4);
  uVar3 = *(undefined4 *)(iVar1 + 0x1cdd8);
  uVar4 = *(undefined4 *)(iVar1 + 0x1cddc);
  param_1[4] = *(undefined4 *)(iVar1 + 0x1cdd0);
  param_1[5] = uVar2;
  param_1[6] = uVar3;
  param_1[7] = uVar4;
  uVar2 = *(undefined4 *)(iVar1 + 0x1cde4);
  uVar3 = *(undefined4 *)(iVar1 + 0x1cde8);
  uVar4 = *(undefined4 *)(iVar1 + 0x1cdec);
  param_1[8] = *(undefined4 *)(iVar1 + 0x1cde0);
  param_1[9] = uVar2;
  param_1[10] = uVar3;
  param_1[0xb] = uVar4;
  uVar2 = *(undefined4 *)(iVar1 + 0x1cdf4);
  uVar3 = *(undefined4 *)(iVar1 + 0x1cdf8);
  uVar4 = *(undefined4 *)(iVar1 + 0x1cdfc);
  param_1[0xc] = *(undefined4 *)(iVar1 + 0x1cdf0);
  param_1[0xd] = uVar2;
  param_1[0xe] = uVar3;
  param_1[0xf] = uVar4;
  pthread_mutex_unlock(__mutex);
  return;
}



/* Function: FUN_0001a998 @ 0001a998 */

void FUN_0001a998(int param_1,code *UNRECOVERED_JUMPTABLE_00)

{
  int iVar1;
  undefined4 *puVar2;
  code *UNRECOVERED_JUMPTABLE;
  char *__name;
  
  puVar2 = (undefined4 *)(DAT_0001aaa4 + 0x1a9b0);
  *puVar2 = UNRECOVERED_JUMPTABLE_00;
  if (param_1 == 0) {
    if (UNRECOVERED_JUMPTABLE_00 != (code *)0x0) {
                    /* WARNING: Could not recover jumptable at 0x0001a9cc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(DAT_0001aaa8 + 0x1a9c8);
      return;
    }
  }
  else {
    __name = (char *)(DAT_0001aaac + 0x1a9e0);
    iVar1 = access(__name,1);
    if (iVar1 == 0) {
      iVar1 = spawnl(1,__name,__name,DAT_0001aab4 + 0x1aa40,0);
      if (iVar1 == -1) {
        UNRECOVERED_JUMPTABLE = (code *)*puVar2;
        if (UNRECOVERED_JUMPTABLE != (code *)0x0) {
          puVar2 = (undefined4 *)__get_errno_ptr();
                    /* WARNING: Could not recover jumptable at 0x0001aaa0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE)(DAT_0001aac0 + 0x1aa9c,*puVar2);
          return;
        }
      }
      else {
        UNRECOVERED_JUMPTABLE = (code *)*puVar2;
        *(int *)(DAT_0001aab8 + 0x1aa5c) = iVar1;
        if (UNRECOVERED_JUMPTABLE != (code *)0x0) {
                    /* WARNING: Could not recover jumptable at 0x0001aa74. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE)(DAT_0001aabc + 0x1aa70,iVar1);
          return;
        }
      }
    }
    else {
      UNRECOVERED_JUMPTABLE = (code *)*puVar2;
      if (UNRECOVERED_JUMPTABLE != (code *)0x0) {
        puVar2 = (undefined4 *)__get_errno_ptr();
                    /* WARNING: Could not recover jumptable at 0x0001aa20. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE)(DAT_0001aab0 + 0x1aa1c,__name,*puVar2);
        return;
      }
    }
  }
  return;
}



/* Function: FUN_0001aac4 @ 0001aac4 */

void FUN_0001aac4(void)

{
  int iVar1;
  undefined4 *puVar2;
  __pid_t *p_Var3;
  code *UNRECOVERED_JUMPTABLE;
  
  p_Var3 = (__pid_t *)(DAT_0001ab44 + 0x1aad4);
  if (*p_Var3 < 1) {
    return;
  }
  iVar1 = kill(*p_Var3,0x14);
  if (iVar1 != 0) {
    UNRECOVERED_JUMPTABLE = *(code **)(DAT_0001ab48 + 0x1aaf8);
    if (UNRECOVERED_JUMPTABLE == (code *)0x0) {
      return;
    }
    puVar2 = (undefined4 *)__get_errno_ptr();
                    /* WARNING: Could not recover jumptable at 0x0001ab18. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE)(DAT_0001ab4c + 0x1ab1c,*p_Var3,*puVar2);
    return;
  }
  if (*(code **)(DAT_0001ab50 + 0x1ab28) == (code *)0x0) {
    return;
  }
                    /* WARNING: Could not recover jumptable at 0x0001ab40. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_0001ab50 + 0x1ab28))(DAT_0001ab54 + 0x1ab40,*p_Var3);
  return;
}



/* Function: FUN_0001ab58 @ 0001ab58 */

void FUN_0001ab58(void)

{
  __pid_t _Var1;
  undefined4 *puVar2;
  int iVar3;
  __pid_t *p_Var4;
  code *pcVar5;
  int *piVar6;
  int local_14;
  
  piVar6 = (int *)(DAT_0001ac74 + 0x1ab6c);
  _Var1 = *piVar6;
  if (_Var1 < 1) {
    return;
  }
  iVar3 = 10;
  do {
    _Var1 = waitpid(_Var1,&local_14,0x40);
    if (_Var1 == *piVar6) {
      if (*(code **)(DAT_0001ac84 + 0x1ac1c) != (code *)0x0) {
        (**(code **)(DAT_0001ac84 + 0x1ac1c))(DAT_0001ac88 + 0x1ac34,local_14);
      }
LAB_0001ac34:
      *(undefined4 *)(DAT_0001ac8c + 0x1ac44) = 0xffffffff;
      return;
    }
    if (_Var1 == -1) {
      pcVar5 = *(code **)(DAT_0001ac90 + 0x1ac58);
      if (pcVar5 != (code *)0x0) {
        puVar2 = (undefined4 *)__get_errno_ptr();
        (*pcVar5)(DAT_0001ac94 + 0x1ac70,*puVar2);
      }
      goto LAB_0001ac34;
    }
    usleep(100000);
    iVar3 = iVar3 + -1;
    if (iVar3 == 0) {
      if (*(code **)(DAT_0001ac78 + 0x1abc8) != (code *)0x0) {
        (**(code **)(DAT_0001ac78 + 0x1abc8))(DAT_0001ac7c + 0x1abdc);
      }
      p_Var4 = (__pid_t *)(DAT_0001ac80 + 0x1abec);
      kill(*p_Var4,0xf);
      waitpid(*p_Var4,&local_14,0);
      *p_Var4 = -1;
      return;
    }
    _Var1 = *piVar6;
  } while( true );
}



/* Function: FUN_0001ac98 @ 0001ac98 */

undefined4 FUN_0001ac98(void)

{
  int *piVar1;
  __pid_t _Var2;
  uint __seconds;
  undefined4 *puVar3;
  int iVar4;
  undefined4 *puVar5;
  int iVar6;
  pthread_mutex_t *__mutex;
  pthread_mutex_t *__mutex_00;
  int iVar7;
  pthread_mutex_t *__mutex_01;
  int *piVar8;
  int *piVar9;
  code *pcVar10;
  uint local_2c [2];
  
  __mutex_00 = (pthread_mutex_t *)(DAT_0001af2c + 0x1acb8);
  piVar8 = (int *)(DAT_0001af30 + 0x1acc0);
  iVar7 = DAT_0001af34 + 0x1acc8;
  __mutex_01 = (pthread_mutex_t *)(DAT_0001af38 + 0x1acd0);
  piVar9 = (int *)(DAT_0001af3c + 0x1acd4);
  local_2c[0] = 0;
  puVar3 = (undefined4 *)(DAT_0001af40 + 0x1acdc);
LAB_0001acdc:
  pthread_mutex_lock(__mutex_00);
  iVar4 = *piVar8;
  pthread_mutex_unlock(__mutex_00);
  if (iVar4 != 0) {
    return 0;
  }
  iVar4 = spawnl(1,iVar7,iVar7,0);
  if (iVar4 == -1) {
    pcVar10 = *(code **)(DAT_0001af70 + 0x1ae58);
    if (pcVar10 != (code *)0x0) {
      puVar5 = (undefined4 *)__get_errno_ptr();
      (*pcVar10)(DAT_0001af74 + 0x1ae70,*puVar5);
    }
  }
  else {
    if ((code *)*puVar3 != (code *)0x0) {
      (*(code *)*puVar3)(DAT_0001af44 + 0x1ad38,iVar4);
    }
    pthread_mutex_lock(__mutex_01);
    if (*piVar9 != 0) {
      pthread_mutex_unlock(__mutex_01);
      kill(iVar4,0xf);
      waitpid(iVar4,(int *)local_2c,0);
      return 0;
    }
    *(int *)(DAT_0001af48 + 0x1ad60) = iVar4;
    pthread_mutex_unlock(__mutex_01);
    do {
      _Var2 = waitpid(iVar4,(int *)local_2c,0);
      if (_Var2 != -1) {
        pthread_mutex_lock((pthread_mutex_t *)(DAT_0001af4c + 0x1ada0));
        if (*(int *)(DAT_0001af50 + 0x1adac) != iVar4) goto LAB_0001adb4;
        goto LAB_0001aee8;
      }
      piVar1 = (int *)__get_errno_ptr();
    } while (*piVar1 == 4);
    __mutex = (pthread_mutex_t *)(DAT_0001af64 + 0x1ae1c);
    pthread_mutex_lock(__mutex);
    if (*(int *)(DAT_0001af68 + 0x1ae2c) == iVar4) {
LAB_0001aee8:
      *(undefined4 *)(DAT_0001af84 + 0x1aef8) = 0xffffffff;
LAB_0001adb4:
      iVar6 = *(int *)(DAT_0001af54 + 0x1adc4);
      pthread_mutex_unlock((pthread_mutex_t *)(DAT_0001af58 + 0x1adc8));
      if (((_Var2 != iVar4) || ((local_2c[0] & 0xff) != 0)) || ((local_2c[0] & 0xffff) >> 8 != 0x49)
         ) goto LAB_0001add4;
      puVar5 = (undefined4 *)(DAT_0001af78 + 0x1aea4);
      pcVar10 = (code *)*puVar5;
      if (pcVar10 != (code *)0x0) {
        (*pcVar10)(DAT_0001af7c + 0x1aec0,_Var2,local_2c[0],iVar6);
        pcVar10 = (code *)*puVar5;
        if (pcVar10 != (code *)0x0) {
          (*pcVar10)(DAT_0001af80 + 0x1aed8);
        }
      }
      if (iVar6 == 0) {
        __seconds = 10;
LAB_0001ae08:
        sleep(__seconds);
      }
      goto LAB_0001acdc;
    }
    iVar6 = *(int *)(DAT_0001af6c + 0x1ae44);
    pthread_mutex_unlock(__mutex);
LAB_0001add4:
    if (*(code **)(DAT_0001af5c + 0x1ade0) != (code *)0x0) {
      (**(code **)(DAT_0001af5c + 0x1ade0))(DAT_0001af60 + 0x1adfc,_Var2,local_2c[0],iVar6);
      if (iVar6 == 0) goto LAB_0001ae04;
      goto LAB_0001acdc;
    }
    if (iVar6 != 0) goto LAB_0001acdc;
  }
LAB_0001ae04:
  __seconds = 1;
  goto LAB_0001ae08;
}



/* Function: FUN_0001af88 @ 0001af88 */

void FUN_0001af88(int param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 *puVar2;
  code *UNRECOVERED_JUMPTABLE;
  char *__name;
  pthread_mutex_t *__mutex;
  
  puVar2 = (undefined4 *)(DAT_0001b060 + 0x1af9c);
  *puVar2 = param_2;
  if (param_1 == 0) {
    return;
  }
  __name = (char *)(DAT_0001b064 + 0x1afb0);
  iVar1 = access(__name,1);
  if (iVar1 != 0) {
    UNRECOVERED_JUMPTABLE = (code *)*puVar2;
    if (UNRECOVERED_JUMPTABLE == (code *)0x0) {
      return;
    }
    puVar2 = (undefined4 *)__get_errno_ptr();
                    /* WARNING: Could not recover jumptable at 0x0001afe4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE)(DAT_0001b068 + 0x1afe8,__name,*puVar2);
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_0001b06c + 0x1aff4);
  pthread_mutex_lock(__mutex);
  *(undefined4 *)(DAT_0001b070 + 0x1b008) = 0;
  pthread_mutex_unlock(__mutex);
  iVar1 = pthread_create((pthread_t *)(DAT_0001b074 + 0x1b024),(pthread_attr_t *)0x0,
                         (__start_routine *)(DAT_0001b078 + 0x1b028),(void *)0x0);
  if (iVar1 == 0) {
    *(undefined4 *)(DAT_0001b080 + 0x1b05c) = 1;
    return;
  }
  if ((code *)*puVar2 == (code *)0x0) {
    return;
  }
                    /* WARNING: Could not recover jumptable at 0x0001b048. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*(code *)*puVar2)(DAT_0001b07c + 0x1b04c);
  return;
}



/* Function: FUN_0001b084 @ 0001b084 */

void FUN_0001b084(void)

{
  undefined4 *puVar1;
  pthread_mutex_t *__mutex;
  __pid_t __pid;
  
  if (*(int *)(DAT_0001b11c + 0x1b090) == 0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_0001b120 + 0x1b0a8);
  pthread_mutex_lock(__mutex);
  __pid = *(__pid_t *)(DAT_0001b124 + 0x1b0c0);
  *(undefined4 *)(DAT_0001b128 + 0x1b0c8) = 1;
  pthread_mutex_unlock(__mutex);
  if (0 < __pid) {
    kill(__pid,0xf);
  }
  pthread_join(*(pthread_t *)(DAT_0001b12c + 0x1b0f4),(void **)0x0);
  puVar1 = (undefined4 *)(DAT_0001b134 + 0x1b114);
  *(undefined4 *)(DAT_0001b130 + 0x1b10c) = 0;
  *puVar1 = 0xffffffff;
  return;
}



/* Function: FUN_0001b138 @ 0001b138 */

undefined4 FUN_0001b138(void)

{
  return 1;
}



/* Function: FUN_0001b140 @ 0001b140 */

uint FUN_0001b140(uint param_1,uint param_2)

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
                    /* WARNING: Could not recover jumptable at 0x0001b174. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  uVar1 = (*(code *)(iVar2 * 0xc + 0x1b17c))();
  return uVar1;
}



/* Function: FUN_0001b350 @ 0001b350 */

undefined8 FUN_0001b350(int param_1,int param_2,int param_3,int param_4)

{
  undefined8 uVar1;
  
  if (param_4 != 0 || param_3 != 0) {
    uVar1 = FUN_0001b3c8();
    return uVar1;
  }
  if (param_2 != 0 || param_1 != 0) {
    param_2 = -1;
    param_1 = -1;
  }
  return CONCAT44(param_2,param_1);
}



/* Function: FUN_0001b38c @ 0001b38c */

void FUN_0001b38c(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_0001b404();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_0001b3c8 @ 0001b3c8 */

void FUN_0001b3c8(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_0001b584();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_0001b404 @ 0001b404 */

undefined8 FUN_0001b404(uint param_1,uint param_2,uint param_3,uint param_4)

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
          if (uVar4 == 0) goto LAB_0001b504;
        }
        bVar11 = param_1 < uVar7;
        uVar6 = param_1 - uVar7;
        param_1 = uVar6 * 2 + 1;
        param_2 = (param_2 - (uVar5 + bVar11)) * 2 + (uint)CARRY4(uVar6,uVar6) +
                  (uint)(0xfffffffe < uVar6 * 2);
        uVar4 = uVar4 - 1;
      } while (uVar4 != 0);
LAB_0001b504:
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



/* Function: FUN_0001b584 @ 0001b584 */

undefined8 FUN_0001b584(uint param_1,uint param_2,uint param_3,uint param_4)

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
      if (uVar6 == 0) goto LAB_0001b660;
    }
    bVar10 = param_1 < uVar8;
    uVar7 = param_1 - uVar8;
    param_1 = uVar7 * 2 + 1;
    param_2 = (param_2 - (uVar9 + bVar10)) * 2 + (uint)CARRY4(uVar7,uVar7) +
              (uint)(0xfffffffe < uVar7 * 2);
    uVar6 = uVar6 - 1;
  } while (uVar6 != 0);
LAB_0001b660:
  uVar9 = param_1 >> (uVar5 & 0xff) | param_2 << (0x20 - uVar5 & 0xff) |
          param_2 >> (uVar5 - 0x20 & 0xff);
  uVar8 = uVar9 << (uVar5 & 0xff);
  return CONCAT44((uVar2 + param_2 + (uint)CARRY4(uVar1,param_1)) -
                  (((param_2 >> (uVar5 & 0xff)) << (uVar5 & 0xff) | uVar9 << (uVar5 - 0x20 & 0xff) |
                   uVar9 >> (0x20 - uVar5 & 0xff)) + (uint)(uVar1 + param_1 < uVar8)),
                  (uVar1 + param_1) - uVar8);
}



/* Function: _fini @ 0001b6a8 */

void _fini(void)

{
  return;
}


