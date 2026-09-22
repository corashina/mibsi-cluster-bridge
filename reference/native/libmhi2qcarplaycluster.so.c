/* RECONSTRUCTED PSEUDOCODE - not original source, not build-ready.
 * Input: libmhi2qcarplaycluster.so
 * Language: ARM:LE:32:v8
 * Generated with Ghidra. Types, signatures and control flow may be inaccurate.
 */


/* Function: _init @ 00010e20 */

int _init(EVP_PKEY_CTX *ctx)

{
  return (int)ctx;
}



/* Function: clock_gettime @ 00010e3c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int clock_gettime(clockid_t __clock_id,timespec *__tp)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_clock_gettime_0001dba0)(__clock_id);
  return iVar1;
}



/* Function: waitpid @ 00010e48 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t waitpid(__pid_t __pid,int *__stat_loc,int __options)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_waitpid_0001dba4)(__pid);
  return _Var1;
}



/* Function: pthread_cond_signal @ 00010e54 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_signal(pthread_cond_t *__cond)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_signal_0001dba8)(__cond);
  return iVar1;
}



/* Function: sysconf @ 00010e60 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

long sysconf(int __name)

{
  long lVar1;
  
  lVar1 = (*(code *)PTR_sysconf_0001dbac)(__name);
  return lVar1;
}



/* Function: pthread_create @ 00010e6c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_create(pthread_t *__newthread,pthread_attr_t *__attr,__start_routine *__start_routine,
                  void *__arg)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_create_0001dbb0)(__newthread);
  return iVar1;
}



/* Function: sigemptyset @ 00010e78 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigemptyset(sigset_t *__set)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigemptyset_0001dbb4)(__set);
  return iVar1;
}



/* Function: memmove @ 00010e84 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memmove(void *__dest,void *__src,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memmove_0001dbb8)(__dest);
  return pvVar1;
}



/* Function: snprintf @ 00010e90 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int snprintf(char *__s,size_t __maxlen,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_snprintf_0001dbbc)(__s);
  return iVar1;
}



/* Function: bind @ 00010e9c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int bind(int __fd,sockaddr *__addr,socklen_t __len)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_bind_0001dbc0)(__fd);
  return iVar1;
}



/* Function: setsockopt @ 00010ea8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int setsockopt(int __fd,int __level,int __optname,void *__optval,socklen_t __optlen)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_setsockopt_0001dbc4)(__fd);
  return iVar1;
}



/* Function: usleep @ 00010eb4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int usleep(__useconds_t __useconds)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_usleep_0001dbc8)(__useconds);
  return iVar1;
}



/* Function: getpid @ 00010ec0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t getpid(void)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_getpid_0001dbcc)();
  return _Var1;
}



/* Function: shutdown @ 00010ecc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int shutdown(int __fd,int __how)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_shutdown_0001dbd0)(__fd);
  return iVar1;
}



/* Function: memcpy @ 00010ed8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memcpy(void *__dest,void *__src,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memcpy_0001dbd4)(__dest);
  return pvVar1;
}



/* Function: pthread_equal @ 00010ee4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_equal(pthread_t __thread1,pthread_t __thread2)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_equal_0001dbd8)(__thread1);
  return iVar1;
}



/* Function: __cxa_finalize @ 00010ef0 */

void __cxa_finalize(void)

{
  (*(code *)PTR___cxa_finalize_0001dbdc)();
  return;
}



/* Function: sleep @ 00010efc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

uint sleep(uint __seconds)

{
  uint uVar1;
  
  uVar1 = (*(code *)PTR_sleep_0001dbe0)(__seconds);
  return uVar1;
}



/* Function: vsnprintf @ 00010f08 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int vsnprintf(char *__s,size_t __maxlen,char *__format,__gnuc_va_list __arg)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_vsnprintf_0001dbe4)(__s);
  return iVar1;
}



/* Function: spawnl @ 00010f14 */

void spawnl(void)

{
  (*(code *)PTR_spawnl_0001dbe8)();
  return;
}



/* Function: select @ 00010f20 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int select(int __nfds,fd_set *__readfds,fd_set *__writefds,fd_set *__exceptfds,timeval *__timeout)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_select_0001dbec)(__nfds);
  return iVar1;
}



/* Function: sigaddset @ 00010f2c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigaddset(sigset_t *__set,int __signo)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigaddset_0001dbf0)(__set);
  return iVar1;
}



/* Function: rename @ 00010f38 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int rename(char *__old,char *__new)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_rename_0001dbf4)(__old);
  return iVar1;
}



/* Function: write @ 00010f44 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t write(int __fd,void *__buf,size_t __n)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_write_0001dbf8)(__fd);
  return sVar1;
}



/* Function: kill @ 00010f50 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int kill(__pid_t __pid,int __sig)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_kill_0001dbfc)(__pid);
  return iVar1;
}



/* Function: pthread_cond_broadcast @ 00010f5c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_broadcast(pthread_cond_t *__cond)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_broadcast_0001dc00)(__cond);
  return iVar1;
}



/* Function: __deregister_frame_info @ 00010f68 */

void __deregister_frame_info(void)

{
  (*(code *)PTR___deregister_frame_info_0001dc04)();
  return;
}



/* Function: __get_errno_ptr @ 00010f74 */

void __get_errno_ptr(void)

{
  (*(code *)PTR___get_errno_ptr_0001dc08)();
  return;
}



/* Function: read @ 00010f80 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t read(int __fd,void *__buf,size_t __nbytes)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_read_0001dc0c)(__fd);
  return sVar1;
}



/* Function: dlopen @ 00010f8c */

void dlopen(void)

{
  (*(code *)PTR_dlopen_0001dc10)();
  return;
}



/* Function: unlink @ 00010f98 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int unlink(char *__name)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_unlink_0001dc14)(__name);
  return iVar1;
}



/* Function: accept @ 00010fa4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int accept(int __fd,sockaddr *__addr,socklen_t *__addr_len)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_accept_0001dc18)(__fd);
  return iVar1;
}



/* Function: pthread_sigmask @ 00010fb0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_sigmask(int __how,__sigset_t *__newmask,__sigset_t *__oldmask)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_sigmask_0001dc1c)(__how);
  return iVar1;
}



/* Function: realloc @ 00010fbc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * realloc(void *__ptr,size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_realloc_0001dc20)(__ptr);
  return pvVar1;
}



/* Function: dlsym @ 00010fc8 */

void dlsym(void)

{
  (*(code *)PTR_dlsym_0001dc24)();
  return;
}



/* Function: recvfrom @ 00010fd4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t recvfrom(int __fd,void *__buf,size_t __n,int __flags,sockaddr *__addr,socklen_t *__addr_len)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_recvfrom_0001dc28)(__fd);
  return sVar1;
}



/* Function: memset @ 00010fe0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memset(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memset_0001dc2c)(__s);
  return pvVar1;
}



/* Function: poll @ 00010fec */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int poll(pollfd *__fds,nfds_t __nfds,int __timeout)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_poll_0001dc30)(__fds);
  return iVar1;
}



/* Function: pthread_mutex_unlock @ 00010ff8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_unlock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_unlock_0001dc34)(__mutex);
  return iVar1;
}



/* Function: socket @ 00011004 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int socket(int __domain,int __type,int __protocol)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_socket_0001dc38)(__domain);
  return iVar1;
}



/* Function: pthread_self @ 00011010 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

pthread_t pthread_self(void)

{
  pthread_t pVar1;
  
  pVar1 = (*(code *)PTR_pthread_self_0001dc3c)();
  return pVar1;
}



/* Function: access @ 0001101c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int access(char *__name,int __type)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_access_0001dc40)(__name);
  return iVar1;
}



/* Function: pthread_mutex_lock @ 00011028 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_lock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_lock_0001dc44)(__mutex);
  return iVar1;
}



/* Function: pthread_cond_wait @ 00011034 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_wait(pthread_cond_t *__cond,pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_wait_0001dc48)(__cond);
  return iVar1;
}



/* Function: pthread_mutex_trylock @ 00011040 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_trylock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_trylock_0001dc4c)(__mutex);
  return iVar1;
}



/* Function: listen @ 0001104c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int listen(int __fd,int __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_listen_0001dc50)(__fd);
  return iVar1;
}



/* Function: strlen @ 00011058 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t strlen(char *__s)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_strlen_0001dc54)(__s);
  return sVar1;
}



/* Function: open @ 00011064 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int open(char *__file,int __oflag,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_open_0001dc58)(__file);
  return iVar1;
}



/* Function: mprotect @ 00011070 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int mprotect(void *__addr,size_t __len,int __prot)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_mprotect_0001dc5c)(__addr);
  return iVar1;
}



/* Function: fcntl @ 0001107c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fcntl(int __fd,int __cmd,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fcntl_0001dc60)(__fd);
  return iVar1;
}



/* Function: pthread_join @ 00011088 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_join(pthread_t __th,void **__thread_return)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_join_0001dc64)(__th);
  return iVar1;
}



/* Function: pthread_cond_timedwait @ 00011094 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_cond_timedwait(pthread_cond_t *__cond,pthread_mutex_t *__mutex,timespec *__abstime)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_cond_timedwait_0001dc68)(__cond);
  return iVar1;
}



/* Function: __register_frame_info @ 000110a0 */

void __register_frame_info(void)

{
  (*(code *)PTR___register_frame_info_0001dc6c)();
  return;
}



/* Function: close @ 000110ac */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int close(int __fd)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_close_0001dc70)(__fd);
  return iVar1;
}



/* Function: entry @ 000110b8 */

void processEntry entry(void)

{
  code *pcVar1;
  
  if ((uint)((DAT_000110f4 + 0x110cb) - (DAT_000110f8 + 0x110d0)) < 7) {
    return;
  }
  pcVar1 = *(code **)(DAT_000110fc + 0x110dc + DAT_00011100);
  if (pcVar1 == (code *)0x0) {
    return;
  }
  (*pcVar1)();
  return;
}



/* Function: _FINI_0 @ 00011154 */

void _FINI_0(void)

{
  int iVar1;
  
  iVar1 = DAT_000111c8 + 70000;
  if (*(char *)(DAT_000111c4 + 0x11164) != '\0') {
    return;
  }
  if (*(int *)(iVar1 + DAT_000111cc) != 0) {
    __cxa_finalize(*(undefined4 *)(DAT_000111d0 + 0x11190));
  }
  entry();
  if (*(int *)(iVar1 + DAT_000111d4) != 0) {
    __deregister_frame_info(DAT_000111d8 + 0x111b0);
  }
  *(undefined1 *)(DAT_000111dc + 0x111c0) = 1;
  return;
}



/* Function: _INIT_0 @ 000111e0 */

void _INIT_0(void)

{
  code *pcVar1;
  int iVar2;
  
  iVar2 = DAT_00011244 + 0x111f4;
  if (*(int *)(iVar2 + DAT_00011248) != 0) {
    __register_frame_info(DAT_0001124c + 0x1120c,DAT_00011250 + 0x11210);
  }
  if ((*(int *)(DAT_00011254 + 0x1121c) != 0) &&
     (pcVar1 = *(code **)(iVar2 + DAT_00011258), pcVar1 != (code *)0x0)) {
    (*pcVar1)();
  }
  if (((DAT_00011144 + 0x11114) - (DAT_00011148 + 0x1111c) >> 2) / 2 == 0) {
    return;
  }
  pcVar1 = *(code **)(DAT_0001114c + 0x11124 + DAT_00011150);
  if (pcVar1 == (code *)0x0) {
    return;
  }
  (*pcVar1)();
  return;
}



/* Function: FUN_00011268 @ 00011268 */

undefined4 FUN_00011268(undefined4 param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  undefined4 uVar2;
  
  iVar1 = (**(code **)(DAT_000112d4 + 0x11288))
                    (0,param_2,0x8000100,*(code **)(DAT_000112d4 + 0x11288),param_4);
  if (iVar1 == 0) {
    uVar2 = 0;
  }
  else {
    uVar2 = (**(code **)(DAT_000112d8 + 0x112a8))(param_1,iVar1);
    (**(code **)(DAT_000112dc + 0x112b8))(iVar1);
  }
  return uVar2;
}



/* Function: FUN_000112e0 @ 000112e0 */

undefined8
FUN_000112e0(undefined4 param_1,undefined4 param_2,undefined4 *param_3,undefined4 param_4)

{
  int iVar1;
  undefined8 uVar2;
  
  iVar1 = (**(code **)(DAT_00011374 + 0x11300))
                    (0,param_2,0x8000100,*(code **)(DAT_00011374 + 0x11300),param_4);
  if (iVar1 == 0) {
    if (param_3 != (undefined4 *)0x0) {
      *param_3 = 0xffffffff;
    }
    uVar2 = 0;
  }
  else {
    uVar2 = (**(code **)(DAT_00011378 + 0x11328))(param_1,iVar1,param_3);
    (**(code **)(DAT_0001137c + 0x11338))(iVar1);
  }
  return uVar2;
}



/* Function: FUN_00011380 @ 00011380 */

void FUN_00011380(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  int iVar1;
  
  iVar1 = (**(code **)(DAT_000113e4 + 0x11390))(0,param_2,0x8000100);
  if (iVar1 == 0) {
    return;
  }
  (**(code **)(DAT_000113e8 + 0x113c8))(param_1,iVar1,param_3);
                    /* WARNING: Could not recover jumptable at 0x000113e0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_000113ec + 0x113dc))(iVar1);
  return;
}



/* Function: FUN_000113f0 @ 000113f0 */

void FUN_000113f0(undefined4 param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  
  iVar1 = (**(code **)(DAT_0001145c + 0x11400))(0,param_2,0x8000100);
  if (iVar1 == 0) {
    return;
  }
  (**(code **)(DAT_00011460 + 0x1143c))(param_1,iVar1,param_3,param_4);
                    /* WARNING: Could not recover jumptable at 0x00011458. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_00011464 + 0x11454))(iVar1);
  return;
}



/* Function: FUN_00011468 @ 00011468 */

void FUN_00011468(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  int iVar1;
  
  iVar1 = (**(code **)(DAT_000114d0 + 0x11478))(0,param_2,0x8000100);
  if (iVar1 == 0) {
    return;
  }
  (**(code **)(DAT_000114d4 + 0x114b0))(param_1,iVar1,param_3,0xffffffff);
                    /* WARNING: Could not recover jumptable at 0x000114cc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_000114d8 + 0x114c8))(iVar1);
  return;
}



/* Function: FUN_000114e0 @ 000114e0 */

int FUN_000114e0(int param_1,int param_2,int *param_3,undefined4 param_4)

{
  int iVar1;
  int iVar2;
  int iVar3;
  undefined4 *puVar4;
  undefined4 *puVar5;
  int iVar6;
  int iVar7;
  undefined4 *puVar8;
  int iVar9;
  int iVar10;
  
  puVar5 = (undefined4 *)(DAT_00011764 + 0x11504);
  puVar4 = (undefined4 *)(DAT_00011768 + 0x11508);
  puVar8 = (undefined4 *)(DAT_0001176c + 0x11510);
  iVar2 = (*(code *)*puVar8)(0,0,*puVar5,*puVar4,param_4);
  iVar3 = (*(code *)*puVar8)(0,0,*puVar5,*puVar4);
  iVar1 = param_2;
  if (0 < param_2) {
    iVar1 = param_1;
  }
  if ((((param_3 == (int *)0x0 || iVar1 < 1) || (*param_3 < 0)) || (param_3[1] < 0)) ||
     (((param_3[2] < 1 || (param_3[3] < 1)) ||
      ((param_1 - param_3[2] < *param_3 || (param_2 - param_3[3] < param_3[1])))))) {
    if (iVar2 != 0) {
      (**(code **)(DAT_00011770 + 0x115c0))(iVar2);
    }
    if (iVar3 == 0) {
      return 0;
    }
    iVar2 = 0;
    (**(code **)(DAT_00011774 + 0x115e0))(iVar3);
  }
  else if (iVar2 == 0 || iVar3 == 0) {
    if (iVar2 != 0) {
      (**(code **)(DAT_00011778 + 0x1161c))(iVar2);
    }
    if (iVar3 == 0) {
      return 0;
    }
    iVar2 = 0;
    (**(code **)(DAT_0001177c + 0x1163c))(iVar3);
  }
  else {
    iVar6 = DAT_00011780 + 0x1165c;
    iVar9 = DAT_00011784 + 0x11664;
    iVar10 = DAT_00011788 + 0x1166c;
    iVar7 = DAT_0001178c + 0x11678;
    FUN_000113f0(iVar2,iVar6,param_1,param_1 >> 0x1f);
    FUN_000113f0(iVar2,iVar9,param_2,param_2 >> 0x1f);
    FUN_000113f0(iVar2,iVar10,0,0);
    FUN_000113f0(iVar2,iVar7,0,0);
    iVar1 = DAT_00011790;
    FUN_000113f0(iVar3,iVar6,param_3[2],param_3[2] >> 0x1f);
    FUN_000113f0(iVar3,iVar9,param_3[3],param_3[3] >> 0x1f);
    FUN_000113f0(iVar3,iVar10,*param_3,*param_3 >> 0x1f);
    FUN_000113f0(iVar3,iVar7,param_3[1],param_3[1] >> 0x1f);
    FUN_00011380(iVar3,DAT_00011794 + 0x11724,*(undefined4 *)(iVar1 + 0x116e4));
    FUN_00011380(iVar2,DAT_00011798 + 0x11738,*(undefined4 *)(iVar1 + 0x116e4));
    FUN_00011380(iVar2,DAT_0001179c + 0x1174c,iVar3);
    (**(code **)(DAT_000117a0 + 0x1175c))(iVar3);
  }
  return iVar2;
}



/* Function: FUN_000117a4 @ 000117a4 */

undefined4
FUN_000117a4(int param_1,undefined4 param_2,int param_3,undefined4 param_4,undefined4 param_5)

{
  int iVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  undefined4 *puVar5;
  undefined4 *puVar6;
  undefined4 *puVar7;
  
  puVar6 = (undefined4 *)(DAT_0001191c + 0x117c8);
  puVar5 = (undefined4 *)(DAT_00011920 + 0x117cc);
  puVar7 = (undefined4 *)(DAT_00011924 + 0x117d4);
  iVar2 = (*(code *)*puVar7)(0,0,*puVar6,*puVar5,param_4);
  iVar3 = (*(code *)*puVar7)(0,0,*puVar6,*puVar5);
  if (param_1 == 0 || iVar2 == 0) {
    if (iVar2 == 0) goto LAB_000118cc;
  }
  else if (iVar3 != 0) {
    FUN_00011468(iVar2,DAT_00011928 + 0x11830,param_2);
    FUN_00011468(iVar3,DAT_0001192c + 0x11844,DAT_00011930 + 0x11848);
    if (param_3 != 0) {
      FUN_00011468(iVar3,DAT_00011948 + 0x11914,DAT_0001194c + 0x11918);
    }
    iVar1 = DAT_00011938;
    FUN_00011380(iVar2,DAT_00011934 + 0x11868,iVar3);
    uVar4 = (**(code **)(DAT_0001193c + 0x1187c))(param_1,iVar2,param_4,param_5);
    (**(code **)(iVar1 + 0x11888))(iVar3);
    (**(code **)(iVar1 + 0x11888))(iVar2);
    return uVar4;
  }
  (**(code **)(DAT_00011940 + 0x118c8))(iVar2);
LAB_000118cc:
  if (iVar3 != 0) {
    (**(code **)(DAT_00011944 + 0x118e4))(iVar3);
    return 0xffff1a48;
  }
  return 0xffff1a48;
}



/* Function: FUN_00011a30 @ 00011a30 */

undefined4 FUN_00011a30(int param_1,int param_2)

{
  int iVar1;
  int iVar2;
  undefined4 uVar3;
  int iVar4;
  int iVar5;
  int iVar6;
  undefined4 *puVar7;
  undefined8 uVar8;
  int local_2c [2];
  
  if (param_1 == 0) {
    return 0;
  }
  iVar1 = FUN_00011268(param_1,DAT_00011b00 + 0x11a50);
  if ((iVar1 != 0) && (iVar2 = (**(code **)(DAT_00011b04 + 0x11a64))(), 0 < iVar2)) {
    puVar7 = (undefined4 *)(DAT_00011b08 + 0x11a8c);
    iVar6 = DAT_00011b0c + 0x11a94;
    iVar4 = 0;
    do {
      iVar5 = iVar4 + 1;
      uVar3 = (*(code *)*puVar7)(iVar1,iVar4);
      local_2c[0] = 0;
      uVar8 = FUN_000112e0(uVar3,iVar6,local_2c);
      if ((param_2 >> 0x1f == (int)((ulonglong)uVar8 >> 0x20) && param_2 == (int)uVar8) &&
         (local_2c[0] == 0)) {
        return 1;
      }
      iVar4 = iVar5;
    } while (iVar5 != iVar2);
  }
  return 0;
}



/* Function: FUN_00011b10 @ 00011b10 */

int FUN_00011b10(int *param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  
  if ((param_1 != (int *)0x0) && (*param_1 != 0)) {
    iVar1 = (**(code **)(DAT_00011b84 + 0x11b40))
                      (0,*param_1,2,*(code **)(DAT_00011b84 + 0x11b40),param_4);
    if (iVar1 != 0) {
      (**(code **)(DAT_00011b88 + 0x11b5c))(*param_1);
      *param_1 = iVar1;
    }
    return iVar1;
  }
  return 0;
}



/* Function: FUN_00011b8c @ 00011b8c */

undefined4
FUN_00011b8c(int param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4,undefined4 param_5
            ,undefined4 param_6,int param_7)

{
  undefined4 uVar1;
  
  if ((param_1 != 0) && (param_1 == *(int *)(DAT_00011bfc + 0x11bb0))) {
    FUN_0001b148(param_2,param_3,param_7 != 0,*(int *)(DAT_00011bfc + 0x11bb0),param_5,param_6);
    return 0;
  }
                    /* WARNING: Could not recover jumptable at 0x00011bd4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  uVar1 = (**(code **)(DAT_00011c00 + 0x11bc8))();
  return uVar1;
}



/* Function: FUN_00011c30 @ 00011c30 */

undefined4 FUN_00011c30(void)

{
  FUN_0001b7b4();
  return 0;
}



/* Function: FUN_00011c48 @ 00011c48 */

void FUN_00011c48(int param_1)

{
  int iVar1;
  undefined4 *puVar2;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_00011ca4 + 0x11c5c));
  iVar1 = DAT_00011cb4;
  if (*(int *)(DAT_00011ca8 + 0x11c68) == param_1) {
    puVar2 = (undefined4 *)(DAT_00011cb0 + 0x11c94);
    *(int *)(DAT_00011ca8 + 0x11c68) = 0;
    *puVar2 = 0;
    pthread_cond_broadcast((pthread_cond_t *)(iVar1 + 0x11c9c));
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00011cac + 0x11c80));
  return;
}



/* Function: FUN_00011cb8 @ 00011cb8 */

void FUN_00011cb8(int param_1)

{
  undefined4 *puVar1;
  int *piVar2;
  undefined4 *puVar3;
  pthread_mutex_t *__mutex;
  int iVar4;
  undefined4 *puVar5;
  undefined4 *puVar6;
  
  __mutex = (pthread_mutex_t *)(DAT_00011dc4 + 0x11ccc);
  pthread_mutex_lock(__mutex);
  if (param_1 == 0) {
    piVar2 = (int *)(DAT_00011dcc + 0x11d10);
    puVar6 = (undefined4 *)(DAT_00011dd0 + 0x11d1c);
    puVar5 = (undefined4 *)(DAT_00011dd8 + 0x11d2c);
    iVar4 = *piVar2;
    puVar1 = (undefined4 *)(DAT_00011ddc + 0x11d34);
    puVar3 = (undefined4 *)(DAT_00011de0 + 0x11d38);
    *(undefined4 *)(DAT_00011dd4 + 0x11d24) = 0;
    *piVar2 = 0;
    *puVar6 = 0;
    *puVar5 = 0;
    *puVar1 = 0xffffffff;
    *puVar3 = 0;
    pthread_mutex_unlock(__mutex);
    if (iVar4 == 0) {
      return;
    }
  }
  else {
    piVar2 = (int *)(DAT_00011dc8 + 0x11ce4);
    iVar4 = *piVar2;
    if (iVar4 != param_1) {
      pthread_mutex_unlock(__mutex);
      return;
    }
    puVar3 = (undefined4 *)(DAT_00011dec + 0x11d94);
    puVar6 = (undefined4 *)(DAT_00011df0 + 0x11d9c);
    puVar5 = (undefined4 *)(DAT_00011df4 + 0x11da0);
    puVar1 = (undefined4 *)(DAT_00011df8 + 0x11da8);
    *(undefined4 *)(DAT_00011de8 + 0x11d8c) = 0;
    *piVar2 = 0;
    *puVar3 = 0;
    *puVar6 = 0;
    *puVar5 = 0;
    *puVar1 = 0xffffffff;
    pthread_mutex_unlock(__mutex);
  }
                    /* WARNING: Could not recover jumptable at 0x00011d6c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_00011de4 + 0x11d6c))(iVar4);
  return;
}



/* Function: FUN_00011dfc @ 00011dfc */

void FUN_00011dfc(int param_1)

{
  int iVar1;
  undefined4 *puVar2;
  int iVar3;
  undefined4 uVar4;
  undefined4 *puVar5;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_00011ec4 + 0x11e10));
  iVar1 = DAT_00011ee0;
  if ((param_1 == 0) || (*(int *)(DAT_00011ec8 + 0x11e24) == param_1)) {
    iVar3 = *(int *)(DAT_00011ed0 + 0x11e54);
    puVar5 = (undefined4 *)(DAT_00011ed8 + 0x11e68);
    puVar2 = (undefined4 *)(DAT_00011edc + 0x11e70);
    *(undefined4 *)(DAT_00011ed4 + 0x11e60) = 1;
    *puVar5 = 0;
    *puVar2 = 0;
    *(undefined4 *)(iVar1 + 0x11e7c) = 0;
    iVar1 = DAT_00011eec;
    uVar4 = 0;
    if (iVar3 != 0) {
      if (*(int *)(DAT_00011ee4 + 0x11e94) == 0) {
        uVar4 = 3;
      }
      else {
        uVar4 = 0;
      }
    }
    puVar2 = (undefined4 *)(DAT_00011eec + 0x11ebc);
    *(undefined4 *)(DAT_00011ee8 + 0x11eb8) = uVar4;
    *puVar2 = 0;
    *(undefined4 *)(iVar1 + 0x11ec0) = 0;
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00011ecc + 0x11e3c));
  return;
}



/* Function: FUN_00011ef0 @ 00011ef0 */

void FUN_00011ef0(int param_1,int param_2)

{
  int iVar1;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_00011f54 + 0x11f08));
  iVar1 = DAT_00011f5c;
  if (*(int *)(DAT_00011f58 + 0x11f14) != param_1) {
    *(int *)(DAT_00011f58 + 0x11f14) = param_1;
    *(undefined4 *)(iVar1 + 0x11f30) = 0;
  }
  if (param_2 != 0) {
    *(undefined4 *)(DAT_00011f60 + 0x11f44) = 1;
  }
  pthread_mutex_unlock((pthread_mutex_t *)((int)&DAT_00011f54 + DAT_00011f64));
  return;
}



/* Function: FUN_00011f68 @ 00011f68 */

undefined4 FUN_00011f68(int param_1)

{
  int iVar1;
  int iVar2;
  undefined4 *puVar3;
  undefined4 *puVar4;
  int iVar5;
  pthread_mutex_t *__mutex;
  int *piVar6;
  undefined4 *puVar7;
  undefined4 *puVar8;
  undefined4 *puVar9;
  undefined4 *puVar10;
  
  if (param_1 == 0) {
    return 0xffff1a48;
  }
  __mutex = (pthread_mutex_t *)(DAT_000120c8 + 0x11f88);
  piVar6 = (int *)(DAT_000120cc + 0x11f8c);
  pthread_mutex_lock(__mutex);
  iVar1 = DAT_00012104;
  iVar5 = *piVar6;
  if (iVar5 != param_1) {
    puVar8 = (undefined4 *)(DAT_000120d4 + 0x11fbc);
    puVar7 = (undefined4 *)(DAT_000120d8 + 0x11fc8);
    iVar2 = (**(code **)(DAT_000120d0 + 0x11fb4))(param_1);
    iVar1 = DAT_000120ec;
    puVar10 = (undefined4 *)(DAT_000120dc + 0x11fdc);
    puVar9 = (undefined4 *)(DAT_000120e0 + 0x11fe4);
    puVar3 = (undefined4 *)(DAT_000120e4 + 0x11fec);
    puVar4 = (undefined4 *)(DAT_000120e8 + 0x11ff0);
    *puVar8 = 0;
    *puVar7 = 0;
    *puVar10 = 0;
    *puVar9 = 0;
    *puVar3 = 0;
    *puVar4 = 0xffffffff;
    *(undefined4 *)(iVar1 + 0x11ff8) = 0;
    *(undefined4 *)(iVar1 + 0x11ffc) = 0;
    *piVar6 = iVar2;
    pthread_mutex_unlock(__mutex);
    if (iVar5 != 0) {
      (**(code **)(DAT_000120f0 + 0x12038))(iVar5);
      return 0;
    }
    return 0;
  }
  if (((*(int *)(DAT_000120f4 + 0x12050) == 0) && (*(int *)(DAT_000120f8 + 0x12064) == 0)) &&
     (*(int *)(DAT_000120fc + 0x12078) == 0)) {
    puVar3 = (undefined4 *)(DAT_00012104 + 0x1209c);
    *(undefined4 *)(DAT_00012100 + 0x12098) = 0;
    *puVar3 = 0;
    *(undefined4 *)(iVar1 + 0x120a0) = 0;
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012108 + 0x120ac));
  return 0;
}



/* Function: FUN_0001210c @ 0001210c */

void FUN_0001210c(void)

{
  undefined4 *puVar1;
  int *piVar2;
  pthread_mutex_t *__mutex;
  int iVar3;
  
  __mutex = (pthread_mutex_t *)(DAT_00012188 + 0x1211c);
  pthread_mutex_lock(__mutex);
  piVar2 = (int *)(DAT_0001218c + 0x12134);
  iVar3 = *piVar2;
  puVar1 = (undefined4 *)(DAT_00012194 + 0x12148);
  *(undefined4 *)(DAT_00012190 + 0x1213c) = 0;
  *piVar2 = 0;
  *puVar1 = 0;
  pthread_mutex_unlock(__mutex);
  if (iVar3 == 0) {
    return;
  }
  (**(code **)(DAT_00012198 + 0x1216c))(iVar3);
                    /* WARNING: Could not recover jumptable at 0x00012184. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_0001219c + 0x12180))(iVar3);
  return;
}



/* Function: FUN_000121a0 @ 000121a0 */

void FUN_000121a0(int param_1)

{
  pthread_mutex_t *__mutex;
  
  __mutex = (pthread_mutex_t *)(DAT_00012208 + 0x121b4);
  pthread_mutex_lock(__mutex);
  if ((param_1 == 0) || (param_1 != *(int *)(DAT_0001220c + 0x121cc))) {
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012210 + 0x121e0));
  }
  else {
    pthread_mutex_unlock(__mutex);
    FUN_0001210c();
  }
                    /* WARNING: Could not recover jumptable at 0x000121f4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(DAT_00012214 + 0x121f4))(param_1);
  return;
}



/* Function: FUN_00012218 @ 00012218 */

undefined4 FUN_00012218(void)

{
  int __fd;
  ssize_t sVar1;
  undefined4 uVar2;
  char local_11 [5];
  
  __fd = open((char *)(DAT_00012294 + 0x1222c),0);
  if (-1 < __fd) {
    sVar1 = read(__fd,local_11,1);
    close(__fd);
    if (sVar1 == 1) {
      if (local_11[0] == 'L') {
        uVar2 = 0;
      }
      else if (local_11[0] == 'C') {
        uVar2 = 1;
      }
      else if (local_11[0] == 'S') {
        uVar2 = 2;
      }
      else {
        uVar2 = 0xffffffff;
      }
      return uVar2;
    }
  }
  return 0xffffffff;
}



/* Function: FUN_00012298 @ 00012298 */

int FUN_00012298(void)

{
  int iVar1;
  timespec local_10;
  
  iVar1 = clock_gettime(2,&local_10);
  if (iVar1 == 0) {
    iVar1 = FUN_0001b7ec(local_10.tv_nsec,local_10.tv_nsec >> 0x1f,&DAT_000f4240,0);
    iVar1 = local_10.tv_sec * 1000 + iVar1;
  }
  else {
    iVar1 = 0;
  }
  return iVar1;
}



/* Function: FUN_000122e8 @ 000122e8 */

void FUN_000122e8(int param_1,undefined4 param_2,int param_3)

{
  int iVar1;
  undefined4 *puVar2;
  int iVar3;
  int *piVar4;
  longlong lVar5;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_0001242c + 0x12300));
  iVar3 = DAT_0001243c;
  if (((param_3 != 0) && (param_3 == *(int *)(DAT_00012430 + 0x12314))) &&
     (iVar1 = *(int *)(DAT_00012438 + 0x12338), iVar1 != 0)) {
    *(int *)(DAT_00012430 + 0x12314) = 0;
    iVar3 = *(int *)(iVar3 + 0x12354);
    if (param_1 == 0) {
      *(int *)(DAT_00012440 + 0x12368) = iVar3;
      if (iVar3 == 0) {
        puVar2 = (undefined4 *)(DAT_0001245c + 0x123e0);
        *(undefined4 *)(DAT_00012458 + 0x123dc) = 0;
        *puVar2 = 0;
      }
      else {
        if (*(int *)(DAT_00012444 + 0x12378) == 0) {
          puVar2 = (undefined4 *)(DAT_0001244c + 0x12394);
          *(undefined4 *)(DAT_00012448 + 0x12390) = 0;
          *puVar2 = 0;
        }
        else {
          piVar4 = (int *)(DAT_0001246c + 0x12424);
          *(undefined4 *)(DAT_00012468 + 0x12420) = 1;
          *piVar4 = iVar1;
        }
        *(int *)(DAT_00012450 + 0x123a4) = *(int *)(DAT_00012450 + 0x123a4) + 1;
      }
      iVar3 = DAT_00012464;
      puVar2 = (undefined4 *)(DAT_00012464 + 0x12404);
      *(undefined4 *)(DAT_00012460 + 0x123fc) = 0;
      *puVar2 = 0;
      *(undefined4 *)(iVar3 + 0x12408) = 0;
    }
    else {
      lVar5 = FUN_00012298();
      *(longlong *)(DAT_00012454 + 0x123c0) = lVar5 + 1000;
    }
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00012434 + 0x1232c));
  return;
}



/* Function: FUN_000131d4 @ 000131d4 */

void FUN_000131d4(int param_1)

{
  pthread_t __thread2;
  int iVar1;
  __pid_t _Var2;
  undefined4 *puVar3;
  undefined4 *puVar4;
  pthread_mutex_t *ppVar5;
  int iVar6;
  int *piVar7;
  pthread_t __thread1;
  int iStack_1c;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_000133b8 + 0x131ec));
  if ((*(int *)(DAT_000133bc + 0x13208) == 0) ||
     ((param_1 != 0 &&
      (param_1 != *(int *)(DAT_000133bc + 0x131f8) && *(int *)(DAT_000133bc + 0x131f8) != 0)))) {
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_000133c0 + 0x13224));
  }
  else {
    if (-1 < *(int *)(DAT_000133c4 + 0x13248)) {
      shutdown(*(int *)(DAT_000133c4 + 0x13248),2);
    }
    if (-1 < *(int *)(DAT_000133c8 + 0x13268)) {
      shutdown(*(int *)(DAT_000133c8 + 0x13268),2);
    }
    iVar6 = DAT_000133cc;
    ppVar5 = (pthread_mutex_t *)(DAT_000133d0 + 0x13280);
    __thread1 = *(pthread_t *)(DAT_000133cc + 0x13290);
    __thread2 = pthread_self();
    iVar1 = pthread_equal(__thread1,__thread2);
    pthread_mutex_unlock(ppVar5);
    if (iVar1 == 0) {
      pthread_join(__thread1,(void **)0x0);
      pthread_mutex_lock(ppVar5);
      *(undefined4 *)(iVar6 + 0x1328c) = 0;
      *(undefined4 *)(iVar6 + 0x13284) = 0xffffffff;
      *(undefined4 *)(iVar6 + 0x13288) = 0xffffffff;
      pthread_mutex_unlock(ppVar5);
      goto LAB_000132d0;
    }
  }
  if (param_1 != 0) {
    return;
  }
LAB_000132d0:
  ppVar5 = (pthread_mutex_t *)(DAT_000133d4 + 0x132dc);
  pthread_mutex_lock(ppVar5);
  iVar6 = DAT_000133dc;
  if (*(int *)(DAT_000133d8 + 0x132ec) == 0) {
    pthread_mutex_unlock(ppVar5);
    return;
  }
  FUN_0001b034();
  piVar7 = (int *)(iVar6 + 0x13304);
  _Var2 = *piVar7;
  if (0 < _Var2) {
    iVar6 = 10;
    do {
      _Var2 = waitpid(_Var2,&iStack_1c,0x40);
      if (_Var2 == -1 || _Var2 == *piVar7) goto LAB_00013360;
      usleep(100000);
      iVar6 = iVar6 + -1;
      _Var2 = *piVar7;
    } while (iVar6 != 0);
    kill(_Var2,0xf);
    waitpid(*piVar7,&iStack_1c,0);
  }
LAB_00013360:
  iVar6 = DAT_000133e8;
  puVar3 = (undefined4 *)(DAT_000133e4 + 0x13384);
  puVar4 = (undefined4 *)(DAT_000133e8 + 0x13388);
  ppVar5 = (pthread_mutex_t *)(DAT_000133ec + 0x13390);
  *(undefined4 *)(DAT_000133e0 + 0x1337c) = 0xffffffff;
  *puVar3 = 0;
  *puVar4 = 0;
  *(undefined4 *)(iVar6 + 0x1338c) = 0;
  pthread_mutex_unlock(ppVar5);
  return;
}



/* Function: FUN_000133f0 @ 000133f0 */

undefined4 FUN_000133f0(int param_1,int param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  undefined4 uVar2;
  
  if (param_2 == 0) {
    FUN_00011c48();
    FUN_00011cb8(param_1);
  }
  else {
    iVar1 = FUN_00011268(param_2,DAT_0001352c + 0x13418,param_3,param_4,param_4);
    if ((iVar1 == 0) || (iVar1 = FUN_00011a30(param_2,0x6e), iVar1 != 0)) {
      FUN_00011c48(param_1);
    }
    iVar1 = FUN_00011268(param_2,DAT_00013530 + 0x13444);
    if (iVar1 == 0) {
      FUN_00011cb8(param_1);
    }
    iVar1 = FUN_00011268(param_2,DAT_00013534 + 0x1345c);
    if ((iVar1 != 0) && (iVar1 = FUN_00011a30(param_2,0x6f), iVar1 == 0)) {
      FUN_00011a30(param_2,0x6e);
      goto LAB_00013488;
    }
  }
  FUN_00011dfc(param_1);
  FUN_000131d4(param_1);
LAB_00013488:
  uVar2 = (**(code **)(DAT_00013538 + 0x134a0))(param_1,param_2,param_3,param_4);
  pthread_mutex_lock((pthread_mutex_t *)(DAT_0001353c + 0x134b8));
  if (param_1 == *(int *)(DAT_00013540 + 0x134c4)) {
    *(undefined4 *)(DAT_00013548 + 0x1350c) = 0;
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00013544 + 0x134d8));
  return uVar2;
}



/* Function: FUN_0001354c @ 0001354c */

void FUN_0001354c(void)

{
  int __fd;
  pthread_t __th;
  int *piVar1;
  
  piVar1 = (int *)(DAT_00013858 + 0x1355c);
  if (*piVar1 != 0) {
    __th = *(pthread_t *)(DAT_000138f4 + 0x13840);
    *(undefined4 *)(DAT_000138f8 + 0x13844) = 1;
    pthread_join(__th,(void **)0x0);
    *piVar1 = 0;
  }
  piVar1 = (int *)(DAT_0001385c + 0x13570);
  __fd = *piVar1;
  if (-1 < __fd) {
    close(__fd);
    *piVar1 = -1;
  }
  FUN_0001210c();
  FUN_00011cb8(0);
  FUN_00011dfc(0);
  FUN_000131d4(0);
  piVar1 = *(int **)(DAT_00013860 + 0x135b0);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_00013868 + 0x135d0)) {
    *piVar1 = *(int *)(DAT_00013864 + 0x135b4);
  }
  piVar1 = *(int **)(DAT_0001386c + 0x135e4);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_00013874 + 0x13604)) {
    *piVar1 = *(int *)(DAT_00013870 + 0x135e8);
  }
  piVar1 = *(int **)(DAT_00013878 + 0x13618);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_00013880 + 0x13638)) {
    *piVar1 = *(int *)(DAT_0001387c + 0x1361c);
  }
  piVar1 = *(int **)(DAT_00013884 + 0x1364c);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_0001388c + 0x1366c)) {
    *piVar1 = *(int *)(DAT_00013888 + 0x13650);
  }
  piVar1 = *(int **)(DAT_00013890 + 0x13680);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_00013898 + 0x136a0)) {
    *piVar1 = *(int *)(DAT_00013894 + 0x13684);
  }
  piVar1 = *(int **)(DAT_0001389c + 0x136b4);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138a4 + 0x136d4)) {
    *piVar1 = *(int *)(DAT_000138a0 + 0x136b8);
  }
  piVar1 = *(int **)(DAT_000138a8 + 0x136e8);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138b0 + 0x13708)) {
    *piVar1 = *(int *)(DAT_000138ac + 0x136ec);
  }
  piVar1 = *(int **)(DAT_000138b4 + 0x1371c);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138bc + 0x1373c)) {
    *piVar1 = *(int *)(DAT_000138b8 + 0x13720);
  }
  piVar1 = *(int **)(DAT_000138c0 + 0x13750);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138c8 + 0x13770)) {
    *piVar1 = *(int *)(DAT_000138c4 + 0x13754);
  }
  piVar1 = *(int **)(DAT_000138cc + 0x13784);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138d4 + 0x137a4)) {
    *piVar1 = *(int *)(DAT_000138d0 + 0x13788);
  }
  piVar1 = *(int **)(DAT_000138d8 + 0x137b8);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138e0 + 0x137d8)) {
    *piVar1 = *(int *)(DAT_000138dc + 0x137bc);
  }
  piVar1 = *(int **)(DAT_000138e4 + 0x137ec);
  if ((piVar1 != (int *)0x0) && (*piVar1 == DAT_000138ec + 0x1380c)) {
    *piVar1 = *(int *)(DAT_000138e8 + 0x137f0);
  }
  DataMemoryBarrier(0xf);
  *(undefined4 *)(DAT_000138f0 + 0x13824) = 0;
  return;
}



/* Function: FUN_000138fc @ 000138fc */

undefined4 FUN_000138fc(void)

{
  int iVar1;
  int iVar2;
  undefined4 *puVar3;
  undefined4 *puVar4;
  int *piVar5;
  int iVar6;
  int *piVar7;
  pthread_mutex_t *ppVar8;
  pthread_cond_t *__cond;
  int iVar9;
  undefined4 uVar10;
  int local_30;
  int local_2c;
  timespec local_28;
  
  iVar2 = DAT_00013c08;
  ppVar8 = (pthread_mutex_t *)(DAT_00013c04 + 0x1391c);
  local_2c = -1;
  piVar5 = (int *)(DAT_00013c08 + 0x13924);
  local_30 = 0;
  pthread_mutex_lock(ppVar8);
  iVar6 = *(int *)(iVar2 + 0x13928);
  uVar10 = *(undefined4 *)(iVar2 + 0x1392c);
  iVar9 = *piVar5;
  pthread_mutex_unlock(ppVar8);
  iVar1 = (**(code **)(DAT_00013c0c + 0x13958))(uVar10,10,&local_2c,0);
  if (iVar1 == 0) {
    pthread_mutex_lock(ppVar8);
    *(int *)(iVar2 + 0x13930) = local_2c;
    if (-1 < *(int *)(iVar2 + 0x1392c)) {
      close(*(int *)(iVar2 + 0x1392c));
    }
    ppVar8 = (pthread_mutex_t *)(DAT_00013c28 + 0x13a50);
    piVar5 = (int *)(DAT_00013c2c + 0x13a54);
    *(undefined4 *)(DAT_00013c2c + 0x13a5c) = 0xffffffff;
    pthread_mutex_unlock(ppVar8);
    iVar2 = (**(code **)(DAT_00013c30 + 0x13a70))(&local_30,local_2c);
    if (iVar2 == 0) {
      local_2c = -1;
      iVar2 = clock_gettime(0,&local_28);
      if (iVar2 == 0) {
        piVar7 = (int *)(DAT_00013c34 + 0x13aac);
        local_28.tv_sec = local_28.tv_sec + 5;
        pthread_mutex_lock(ppVar8);
        if (*piVar7 == 0) {
LAB_00013b0c:
          iVar2 = *(int *)(DAT_00013c40 + 0x13b1c);
          pthread_mutex_unlock((pthread_mutex_t *)(DAT_00013c44 + 0x13b20));
          if ((iVar9 == iVar2) && (iVar2 = *(int *)(iVar9 + 0xc), iVar2 != 0)) {
            puVar4 = (undefined4 *)(DAT_00013c48 + 0x13b50);
            uVar10 = *(undefined4 *)(iVar2 + 0x1d0);
            puVar3 = (undefined4 *)(DAT_00013c4c + 0x13b58);
            *puVar4 = 1;
            DataMemoryBarrier(0xf);
            iVar1 = (*(code *)*puVar3)(iVar6,uVar10);
            *puVar4 = 0;
            DataMemoryBarrier(0xf);
            if (iVar1 == 0) {
              piVar5 = (int *)(DAT_00013c50 + 0x13b84);
              if ((*piVar5 == 0) &&
                 (memcpy(&local_28,(void *)(iVar6 + 0x1ec),4), local_28.tv_sec != 0)) {
                memcpy(piVar5,(void *)(local_28.tv_sec + 0x38),4);
              }
              if (*(int *)(DAT_00013c54 + 0x13b98) != 0) {
                (**(code **)(DAT_00013c58 + 0x13bb4))(iVar6,local_30,*(undefined4 *)(iVar2 + 0x1c8))
                ;
              }
            }
          }
        }
        else {
          if (iVar9 == *piVar5) {
            __cond = (pthread_cond_t *)(DAT_00013c38 + 0x13adc);
            piVar5 = (int *)(DAT_00013c3c + 0x13ae0);
            do {
              iVar2 = pthread_cond_timedwait(__cond,ppVar8,&local_28);
              if (*piVar7 == 0) goto LAB_00013b0c;
            } while (iVar2 == 0 && iVar9 == *piVar5);
          }
          pthread_mutex_unlock((pthread_mutex_t *)(DAT_00013c5c + 0x13bd0));
        }
      }
    }
  }
  if (iVar6 != 0) {
    (**(code **)(DAT_00013c10 + 0x13980))(iVar6);
  }
  if (local_30 == 0) {
    if (-1 < local_2c) {
      close(local_2c);
    }
  }
  else {
    (**(code **)(DAT_00013c14 + 0x1399c))();
  }
  if (iVar6 != 0) {
    (**(code **)(DAT_00013c18 + 0x139b8))(iVar6);
  }
  ppVar8 = (pthread_mutex_t *)(DAT_00013c1c + 0x139cc);
  pthread_mutex_lock(ppVar8);
  iVar2 = DAT_00013c20;
  puVar4 = (undefined4 *)(DAT_00013c20 + 0x139e4);
  puVar3 = (undefined4 *)(DAT_00013c24 + 0x139ec);
  *(undefined4 *)(DAT_00013c20 + 0x139e8) = 0;
  *(undefined4 *)(iVar2 + 0x139f0) = 0xffffffff;
  *puVar4 = 0;
  *puVar3 = 0;
  pthread_mutex_unlock(ppVar8);
  return 0;
}



/* Function: FUN_00013c60 @ 00013c60 */

int FUN_00013c60(uint param_1,undefined4 param_2,undefined4 *param_3)

{
  int iVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  int iVar5;
  uint *puVar6;
  char *__name;
  int iVar7;
  int iVar8;
  pthread_mutex_t *ppVar9;
  undefined4 *puVar10;
  int iVar11;
  int iVar12;
  undefined4 *puVar13;
  longlong lVar14;
  int local_6c;
  int local_58;
  int local_54;
  int local_50;
  int local_4c;
  uint local_48;
  uint local_44;
  uint local_40;
  uint local_3c;
  uint local_38;
  uint local_34;
  uint local_30;
  uint local_2c;
  
  iVar1 = FUN_00011268(param_2,DAT_000145c8 + 0x13c84);
  if (iVar1 == 0) {
    iVar1 = (**(code **)(DAT_00014658 + 0x14258))(param_1,param_2,param_3);
    if (iVar1 != 0) {
      return iVar1;
    }
    iVar1 = FUN_00011b10(param_3);
    if (iVar1 != 0) {
      uVar4 = *param_3;
      iVar1 = FUN_00011268(uVar4,DAT_0001465c + 0x14290);
      if (iVar1 == 0) {
        iVar1 = (**(code **)(DAT_00014688 + 0x14414))(0,0,*(undefined4 *)(DAT_00014684 + 0x14410));
      }
      else {
        iVar1 = (**(code **)(DAT_00014660 + 0x142b0))(0,0);
      }
      if (iVar1 != 0) {
        puVar10 = (undefined4 *)(DAT_00014664 + 0x142d8);
        (*(code *)*puVar10)(iVar1,DAT_00014668 + 0x142dc,0xffffffff);
        (*(code *)*puVar10)(iVar1,DAT_0001466c + 0x142f4,0xffffffff);
        FUN_00011380(uVar4,DAT_00014670 + 0x1430c,iVar1);
        (**(code **)(DAT_00014674 + 0x1431c))(iVar1);
        return 0;
      }
      return 0;
    }
    return 0;
  }
  iVar2 = (**(code **)(DAT_000145cc + 0x13c9c))();
  if (iVar2 < 1) {
    FUN_00011ef0(param_1,0);
    iVar1 = (**(code **)(DAT_00014678 + 0x1434c))(param_1,param_2,param_3);
    return iVar1;
  }
  iVar7 = 0;
  puVar10 = (undefined4 *)(DAT_000145d0 + 0x13cc0);
  iVar12 = 0;
  iVar11 = DAT_000145d4 + 0x13cc8;
  local_6c = 0;
  do {
    while( true ) {
      iVar3 = (*(code *)*puVar10)(iVar1,iVar12);
      local_38 = 0;
      lVar14 = FUN_000112e0(iVar3,iVar11,&local_38);
      if (local_38 == 0) break;
LAB_00013cd8:
      iVar12 = iVar12 + 1;
      if (iVar12 == iVar2) goto LAB_00013d34;
    }
    if (lVar14 != 0x6f) {
      if (lVar14 == 0x6e) {
        iVar7 = 1;
      }
      goto LAB_00013cd8;
    }
    iVar12 = iVar12 + 1;
    local_6c = iVar3;
  } while (iVar12 != iVar2);
LAB_00013d34:
  if (local_6c == 0) {
    FUN_00011ef0(param_1,iVar7);
    iVar2 = (**(code **)(DAT_0001467c + 0x14384))(param_1,param_2,param_3);
    iVar1 = iVar7;
    if (iVar2 == 0) {
      iVar1 = 0;
    }
    if (iVar1 == 0) {
      if (iVar2 != 0) {
        iVar7 = 0;
      }
      if (iVar7 != 0) {
        FUN_00011f68(param_1);
        return iVar2;
      }
      return iVar2;
    }
    FUN_00011c48(param_1);
    return iVar2;
  }
  iVar1 = (**(code **)(DAT_000145d8 + 0x13d58))(0,param_2,2);
  if (iVar1 != 0) {
    iVar2 = FUN_00011268(iVar1,DAT_000145dc + 0x13d74);
    if (iVar2 == 0) {
      iVar11 = (**(code **)(DAT_00014608 + 0x13ef8))(0,0,*(undefined4 *)(DAT_00014604 + 0x13ef0));
      if (iVar11 == 0) goto LAB_00013f08;
    }
    else {
      iVar12 = (**(code **)(DAT_000145e0 + 0x13d8c))();
      iVar11 = (**(code **)(DAT_000145e8 + 0x13da4))(0,0,*(undefined4 *)(DAT_000145e4 + 0x13da0));
      if (iVar11 == 0) {
LAB_00013f08:
        (**(code **)(DAT_0001460c + 0x13f18))(iVar1);
        return -0xe5b8;
      }
      if (0 < iVar12) {
        iVar5 = DAT_000145ec + 0x13de0;
        puVar10 = (undefined4 *)(DAT_000145f0 + 0x13de4);
        puVar13 = (undefined4 *)(DAT_000145f4 + 0x13dec);
        iVar3 = 0;
        do {
          iVar8 = iVar3 + 1;
          uVar4 = (*(code *)*puVar10)(iVar2,iVar3);
          local_38 = 0;
          lVar14 = FUN_000112e0(uVar4,iVar5,&local_38);
          if (local_38 != 0 || lVar14 != 0x6f) {
            (*(code *)*puVar13)(iVar11,uVar4);
          }
          iVar3 = iVar8;
        } while (iVar8 != iVar12);
      }
    }
    puVar10 = (undefined4 *)(DAT_000145f8 + 0x13e74);
    FUN_00011380(iVar1,DAT_000145fc + 0x13e7c,iVar11);
    (*(code *)*puVar10)(iVar11);
    FUN_00011ef0(param_1,iVar7);
    iVar2 = (**(code **)(DAT_00014600 + 0x13eac))(param_1,iVar1,param_3);
    (*(code *)*puVar10)(iVar1);
    if (iVar2 != 0) {
      if (iVar7 != 0) {
        FUN_00011c48(param_1);
        return iVar2;
      }
      return iVar2;
    }
    iVar1 = FUN_00011b10(param_3);
    if (iVar1 != 0) {
      local_4c = -1;
      local_58 = iVar2;
      local_54 = iVar2;
      local_50 = iVar2;
      FUN_000131d4(0);
      iVar1 = (**(code **)(DAT_00014610 + 0x13f64))(&local_58);
      if (iVar1 != 0) {
        return 0;
      }
      if (local_58 == 0) {
        return 0;
      }
      (**(code **)(DAT_00014614 + 0x13f8c))(local_58,1);
      iVar1 = (**(code **)(DAT_00014618 + 0x13fa4))(local_58,local_6c,0);
      if (iVar1 == 0) {
        local_34 = *(int *)(DAT_0001461c + 0x13fc8) + 0x24568;
        local_30 = *(int *)(DAT_0001461c + 0x13fc8) + 0x2455c;
        local_38 = param_1;
        (**(code **)(DAT_00014620 + 0x13fd0))(local_58,&local_38);
        lVar14 = FUN_000112e0(local_6c,DAT_00014624 + 0x14008,&local_54);
        local_48 = (uint)(lVar14 == 0);
        if (local_54 != 0) {
          local_48 = 1;
        }
        if (local_48 == 0) {
          (**(code **)(DAT_00014628 + 0x14044))
                    (param_1 + 0x1c8,0x10,(int)lVar14,(int)((ulonglong)lVar14 >> 0x20),&local_48,
                     &local_38);
          iVar1 = (**(code **)(DAT_0001462c + 0x14070))(local_58,&local_48,&local_38);
          if ((iVar1 == 0) &&
             (local_44 = local_48, local_40 = local_48, local_3c = local_48, local_38 = local_48,
             local_34 = local_48, local_30 = local_48, local_2c = local_48,
             iVar2 = (**(code **)(DAT_00014630 + 0x140c4))
                               (*(undefined1 *)(param_1 + 0x55),1,6,0x17d4,&local_50,0xffffffff,
                                &local_4c), iVar1 = DAT_00014638, iVar2 == 0)) {
            ppVar9 = (pthread_mutex_t *)(DAT_00014634 + 0x140f0);
            puVar6 = (uint *)(DAT_00014638 + 0x140f4);
            pthread_mutex_lock(ppVar9);
            iVar2 = DAT_0001463c;
            *(undefined4 *)(iVar1 + 0x14100) = 0xffffffff;
            *puVar6 = param_1;
            *(int *)(iVar1 + 0x140f8) = local_58;
            *(int *)(iVar1 + 0x140fc) = local_4c;
            iVar2 = pthread_create((pthread_t *)(iVar1 + 0x14108),(pthread_attr_t *)0x0,
                                   (__start_routine *)(iVar2 + 0x14120),(void *)0x0);
            if (iVar2 == 0) {
              *(undefined4 *)(iVar1 + 0x14104) = 1;
              pthread_mutex_unlock(ppVar9);
              pthread_mutex_lock((pthread_mutex_t *)(DAT_00014640 + 0x14150));
              if (*(int *)(DAT_00014644 + 0x1415c) != 0) {
                pthread_mutex_unlock((pthread_mutex_t *)(DAT_0001468c + 0x14430));
LAB_00014430:
                iVar1 = local_50;
                uVar4 = *param_3;
                iVar2 = (**(code **)(DAT_00014698 + 0x14458))
                                  (0,0,*(undefined4 *)(DAT_00014690 + 0x1444c),
                                   *(undefined4 *)(DAT_00014694 + 0x14454));
                if (iVar2 != 0) {
                  FUN_000113f0(iVar2,DAT_0001469c + 0x14488,0x6f,0);
                  FUN_000113f0(iVar2,DAT_000146a0 + 0x1449c,iVar1,iVar1 >> 0x1f);
                  iVar1 = FUN_00011268(uVar4,DAT_000146a4 + 0x144b0);
                  if (iVar1 == 0) {
                    iVar1 = (**(code **)(DAT_000146bc + 0x14550))
                                      (0,0,*(undefined4 *)(DAT_000146b8 + 0x1454c));
                  }
                  else {
                    iVar1 = (**(code **)(DAT_000146a8 + 0x144cc))(0,0);
                  }
                  if (iVar1 != 0) {
                    puVar10 = (undefined4 *)(DAT_000146b0 + 0x144f8);
                    (**(code **)(DAT_000146ac + 0x144f4))(iVar1,iVar2);
                    FUN_00011380(uVar4,DAT_000146b4 + 0x14510,iVar1);
                    (*(code *)*puVar10)(iVar1);
                    (*(code *)*puVar10)(iVar2);
                    FUN_00011f68(param_1);
                    return 0;
                  }
                  (**(code **)(DAT_00014680 + 0x143e4))(iVar2);
                }
                FUN_000131d4(param_1);
                return -0xe5b8;
              }
              iVar1 = FUN_0001ad4c(0x3cc5,3,DAT_00014648 + 0x14178);
              if (iVar1 != 0) {
                __name = (char *)(DAT_0001464c + 0x14190);
                iVar1 = access(__name,1);
                if ((iVar1 == 0) &&
                   (iVar2 = spawnl(1,__name,__name,DAT_000146c0 + 0x1457c,0), iVar1 = DAT_000146cc,
                   iVar2 != -1)) {
                  puVar10 = (undefined4 *)(DAT_000146c8 + 0x145ac);
                  puVar13 = (undefined4 *)(DAT_000146cc + 0x145b0);
                  ppVar9 = (pthread_mutex_t *)(DAT_000146d0 + 0x145b8);
                  *(int *)(DAT_000146c4 + 0x145a4) = iVar2;
                  *puVar10 = 1;
                  *puVar13 = 0;
                  *(undefined4 *)(iVar1 + 0x145b4) = 0;
                  pthread_mutex_unlock(ppVar9);
                  goto LAB_00014430;
                }
                FUN_0001b034();
              }
              pthread_mutex_unlock((pthread_mutex_t *)(DAT_00014650 + 0x141ac));
              FUN_000131d4(param_1);
              return 0;
            }
            pthread_mutex_unlock(ppVar9);
            close(local_4c);
            pthread_mutex_lock(ppVar9);
            *puVar6 = 0;
            *(undefined4 *)(iVar1 + 0x140f8) = 0;
            *(undefined4 *)(iVar1 + 0x140fc) = 0xffffffff;
            *(undefined4 *)(iVar1 + 0x14100) = 0xffffffff;
            *(undefined4 *)(iVar1 + 0x14104) = 0;
            pthread_mutex_unlock(ppVar9);
          }
        }
      }
      local_48 = 0;
      local_44 = 0;
      local_40 = 0;
      local_3c = 0;
      local_38 = 0;
      local_34 = 0;
      local_30 = 0;
      local_2c = 0;
      if (local_58 != 0) {
        (**(code **)(DAT_00014654 + 0x1422c))();
      }
      return 0;
    }
  }
  return -0xe5b8;
}



/* Function: FUN_000147f8 @ 000147f8 */

undefined4 FUN_000147f8(int *param_1,int param_2,int param_3,int param_4,int *param_5)

{
  size_t __len;
  int iVar1;
  int local_1c [2];
  
  memcpy(local_1c,param_1,4);
  if (local_1c[0] == param_2) {
    *param_5 = local_1c[0];
    if (local_1c[0] == param_3) {
      return 1;
    }
  }
  else {
    if (local_1c[0] == param_3) {
      *param_5 = local_1c[0];
      return 1;
    }
    if (param_4 == 0 || local_1c[0] != param_4) {
      return 0;
    }
    *param_5 = local_1c[0];
  }
  __len = sysconf(0xb);
  if ((int)__len < 1) {
    __len = 0x1000;
  }
  iVar1 = mprotect((void *)(-__len & (uint)param_1),__len,0x300);
  if (iVar1 != 0) {
    return 0;
  }
  *param_1 = param_3;
  DataMemoryBarrier(0xf);
  return 1;
}



/* Function: FUN_000153a4 @ 000153a4 */

int FUN_000153a4(int param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int *piVar4;
  undefined4 *puVar5;
  pthread_mutex_t *__mutex;
  int iVar6;
  bool bVar7;
  bool bVar8;
  int local_28;
  int local_24;
  
  local_24 = 0;
  if (param_1 == 0) {
    iVar1 = (**(code **)(DAT_000157e4 + 0x15664))();
    return iVar1;
  }
  puVar5 = (undefined4 *)(DAT_00015790 + 0x153d4);
  iVar1 = (*(code *)*puVar5)(0,DAT_00015794 + 0x153dc,0x8000100);
  bVar7 = iVar1 != 0;
  iVar2 = (*(code *)*puVar5)(0,DAT_00015798 + 0x153f8,0x8000100);
  if (iVar2 != 0 && bVar7) {
    iVar6 = (**(code **)(DAT_000157d4 + 0x155e4))(param_1,iVar1,0,&local_24);
    bVar8 = false;
    if (local_24 == 0 && iVar6 != 0) {
      iVar3 = (**(code **)(DAT_000157d8 + 0x1561c))(iVar6,iVar2);
      bVar8 = iVar3 != 0;
      (**(code **)(DAT_000157dc + 0x1562c))(iVar6);
    }
    (**(code **)(DAT_000157e0 + 0x15650))(iVar2);
  }
  else {
    bVar8 = false;
    if (iVar2 != 0) {
      (**(code **)(DAT_0001579c + 0x15430))();
      if (!bVar7) {
        iVar1 = (**(code **)(DAT_00015810 + 0x15788))(param_1);
        return iVar1;
      }
    }
    else if (!bVar7) {
      iVar1 = (**(code **)(DAT_000157c0 + 0x1554c))(param_1);
      return iVar1;
    }
  }
  puVar5 = (undefined4 *)(DAT_000157a4 + 0x15454);
  (**(code **)(DAT_000157a0 + 0x15450))(iVar1);
  iVar1 = (*(code *)*puVar5)(param_1);
  if (iVar1 != 0) {
    bVar8 = false;
  }
  if (!bVar8) {
    return iVar1;
  }
  FUN_0001210c();
  iVar1 = (**(code **)(DAT_000157b0 + 0x154a0))
                    (0,0,*(undefined4 *)(DAT_000157a8 + 0x15494),
                     *(undefined4 *)(DAT_000157ac + 0x15498));
  iVar6 = DAT_000157b4 + 0x154c8;
  iVar2 = (**(code **)(DAT_000157b8 + 0x154d0))(0,iVar6,0x8000100);
  local_24 = -0xe5b8;
  local_28 = 0;
  if (iVar1 == 0 || iVar2 == 0) {
    if (iVar2 == 0) goto LAB_0001550c;
  }
  else {
    iVar3 = (**(code **)(DAT_000157c4 + 0x1556c))(param_1,iVar2,0,&local_24);
    if (iVar3 == 0) {
      if (local_24 == 0) {
        local_24 = -0xe5b8;
      }
      if (local_28 != 0) {
        (**(code **)(DAT_0001580c + 0x15764))();
      }
    }
    else {
      if (local_24 == 0) {
        FUN_00011380(iVar1,iVar6,iVar3);
        FUN_00011468(iVar1,DAT_000157e8 + 0x15694,DAT_000157ec + 0x15698);
        FUN_00011468(iVar1,DAT_000157f0 + 0x156ac,DAT_000157f4 + 0x156b0);
        local_24 = (**(code **)(DAT_000157f8 + 0x156c4))(&local_28,iVar1);
        if (local_28 == 0) {
          if (local_24 == 0) {
            local_24 = -0xe5b8;
          }
        }
        else {
          if (local_24 != 0) goto LAB_00015590;
          local_24 = (*(code *)*puVar5)();
          if (local_24 != 0) goto LAB_00015584;
          __mutex = (pthread_mutex_t *)(DAT_000157fc + 0x15708);
          pthread_mutex_lock(__mutex);
          piVar4 = (int *)(DAT_00015800 + 0x15724);
          puVar5 = (undefined4 *)(DAT_00015808 + 0x15730);
          *(int *)(DAT_00015804 + 0x1572c) = param_1;
          *piVar4 = local_28;
          *puVar5 = 0;
          pthread_mutex_unlock(__mutex);
          local_28 = 0;
        }
      }
      else {
LAB_00015584:
        if (local_28 != 0) {
LAB_00015590:
          (**(code **)(DAT_000157c8 + 0x1559c))();
        }
      }
      (**(code **)(DAT_000157cc + 0x155b0))(iVar3);
    }
  }
  (**(code **)(DAT_000157d0 + 0x155c4))(iVar2);
LAB_0001550c:
  if (iVar1 != 0) {
    (**(code **)(DAT_000157bc + 0x15524))(iVar1);
  }
  return 0;
}



/* Function: _INIT_1 @ 00015814 */

/* WARNING: Removing unreachable block (ram,0x00019d58) */

void _INIT_1(void)

{
  int iVar1;
  int iVar2;
  int iVar3;
  int iVar4;
  int iVar5;
  undefined4 uVar6;
  int *piVar7;
  undefined4 *puVar8;
  void *pvVar9;
  int *piVar10;
  code *UNRECOVERED_JUMPTABLE;
  int iVar11;
  char *__name;
  pthread_mutex_t *__mutex;
  void *__src;
  int *piVar12;
  int iVar13;
  int *piVar14;
  int *piVar15;
  int local_2c [2];
  
  iVar4 = FUN_0001b7e4();
  if (iVar4 != 0) {
    getpid();
    iVar4 = dlopen(DAT_000166d0 + 0x15844,0x102);
    if (iVar4 != 0) {
      iVar5 = dlsym(iVar4,DAT_000166d4 + 0x15858);
      *(int *)(DAT_000166d8 + 0x15864) = iVar5;
      if (iVar5 != 0) {
        iVar5 = dlsym(iVar4,DAT_000166dc + 0x1587c);
        *(int *)(DAT_000166e0 + 0x15888) = iVar5;
        if (iVar5 != 0) {
          iVar5 = dlsym(iVar4,DAT_000166e4 + 0x158a0);
          *(int *)(DAT_000166e8 + 0x158ac) = iVar5;
          if (iVar5 != 0) {
            iVar5 = dlsym(iVar4,DAT_000166ec + 0x158c4);
            *(int *)(DAT_000166f0 + 0x158d0) = iVar5;
            if (iVar5 != 0) {
              iVar5 = dlsym(iVar4,DAT_000166f4 + 0x158e8);
              *(int *)(DAT_000166f8 + 0x158f4) = iVar5;
              if (iVar5 != 0) {
                iVar5 = dlsym(iVar4,DAT_000166fc + 0x1590c);
                *(int *)(DAT_00016700 + 0x15918) = iVar5;
                if (iVar5 != 0) {
                  iVar5 = dlsym(iVar4,DAT_00016704 + 0x15930);
                  *(int *)(DAT_00016708 + 0x1593c) = iVar5;
                  if (iVar5 != 0) {
                    iVar5 = dlsym(iVar4,DAT_0001670c + 0x15954);
                    *(int *)(DAT_00016710 + 0x15960) = iVar5;
                    if (iVar5 != 0) {
                      iVar5 = dlsym(iVar4,DAT_00016714 + 0x15978);
                      *(int *)(DAT_00016718 + 0x15984) = iVar5;
                      if (iVar5 != 0) {
                        iVar5 = dlsym(iVar4,DAT_0001671c + 0x1599c);
                        *(int *)(DAT_00016720 + 0x159a8) = iVar5;
                        if (iVar5 != 0) {
                          iVar5 = dlsym(iVar4,DAT_00016724 + 0x159c0);
                          *(int *)(DAT_00016728 + 0x159cc) = iVar5;
                          if (iVar5 != 0) {
                            iVar5 = dlsym(iVar4,DAT_0001672c + 0x159e4);
                            *(int *)(DAT_00016730 + 0x159f0) = iVar5;
                            if (iVar5 != 0) {
                              iVar5 = dlsym(iVar4,DAT_00016734 + 0x15a08);
                              *(int *)(DAT_00016738 + 0x15a14) = iVar5;
                              if (iVar5 != 0) {
                                iVar5 = dlsym(iVar4,DAT_0001673c + 0x15a2c);
                                *(int *)(DAT_00016740 + 0x15a38) = iVar5;
                                if (iVar5 != 0) {
                                  iVar5 = dlsym(iVar4,DAT_00016744 + 0x15a50);
                                  *(int *)(DAT_00016748 + 0x15a5c) = iVar5;
                                  if (iVar5 != 0) {
                                    iVar5 = dlsym(iVar4,DAT_0001674c + 0x15a74);
                                    *(int *)(DAT_00016750 + 0x15a80) = iVar5;
                                    if (iVar5 != 0) {
                                      iVar5 = dlsym(iVar4,DAT_00016754 + 0x15a98);
                                      *(int *)(DAT_00016758 + 0x15aa4) = iVar5;
                                      if (iVar5 != 0) {
                                        iVar5 = dlsym(iVar4,DAT_0001675c + 0x15abc);
                                        *(int *)(DAT_00016760 + 0x15ac8) = iVar5;
                                        if (iVar5 != 0) {
                                          iVar5 = dlsym(iVar4,DAT_00016764 + 0x15ae0);
                                          *(int *)(DAT_00016768 + 0x15aec) = iVar5;
                                          if (iVar5 != 0) {
                                            iVar5 = dlsym(iVar4,DAT_0001676c + 0x15b04);
                                            *(int *)(DAT_00016770 + 0x15b10) = iVar5;
                                            if (iVar5 != 0) {
                                              iVar5 = dlsym(iVar4,DAT_00016774 + 0x15b28);
                                              *(int *)(DAT_00016778 + 0x15b34) = iVar5;
                                              if (iVar5 != 0) {
                                                iVar5 = dlsym(iVar4,DAT_0001677c + 0x15b4c);
                                                *(int *)(DAT_00016780 + 0x15b58) = iVar5;
                                                if (iVar5 != 0) {
                                                  iVar5 = dlsym(iVar4,DAT_00016784 + 0x15b70);
                                                  *(int *)(DAT_00016788 + 0x15b7c) = iVar5;
                                                  if (iVar5 != 0) {
                                                    iVar5 = dlsym(iVar4,DAT_0001678c + 0x15b94);
                                                    *(int *)(DAT_00016790 + 0x15ba0) = iVar5;
                                                    if (iVar5 != 0) {
                                                      iVar5 = dlsym(iVar4,DAT_00016794 + 0x15bb8);
                                                      *(int *)(DAT_00016798 + 0x15bc4) = iVar5;
                                                      if (iVar5 != 0) {
                                                        iVar5 = dlsym(iVar4,DAT_0001679c + 0x15bdc);
                                                        *(int *)(DAT_000167a0 + 0x15be8) = iVar5;
                                                        if (iVar5 != 0) {
                                                          iVar5 = dlsym(iVar4,DAT_000167a4 + 0x15c00
                                                                       );
                                                          *(int *)(DAT_000167a8 + 0x15c0c) = iVar5;
                                                          if (iVar5 != 0) {
                                                            uVar6 = dlsym(iVar4,DAT_000167ac +
                                                                                0x15c24);
                                                            iVar5 = DAT_000167b4 + 0x15c38;
                                                            *(undefined4 *)(DAT_000167b0 + 0x15c34)
                                                                 = uVar6;
                                                            uVar6 = dlsym(iVar4,iVar5);
                                                            iVar5 = DAT_000167bc + 0x15c54;
                                                            *(undefined4 *)(DAT_000167b8 + 0x15c50)
                                                                 = uVar6;
                                                            uVar6 = dlsym(iVar4,iVar5);
                                                            iVar5 = DAT_000167c4 + 0x15c70;
                                                            *(undefined4 *)(DAT_000167c0 + 0x15c6c)
                                                                 = uVar6;
                                                            iVar5 = dlsym(iVar4,iVar5);
                                                            *(int *)(DAT_000167c8 + 0x15c84) = iVar5
                                                            ;
                                                            if (iVar5 != 0) {
                                                              iVar5 = dlsym(iVar4,DAT_000167cc +
                                                                                  0x15c9c);
                                                              *(int *)(DAT_000167d0 + 0x15ca8) =
                                                                   iVar5;
                                                              if (iVar5 != 0) {
                                                                iVar5 = dlsym(iVar4,DAT_000167d4 +
                                                                                    0x15cc0);
                                                                *(int *)(DAT_000167d8 + 0x15ccc) =
                                                                     iVar5;
                                                                if (iVar5 != 0) {
                                                                  iVar5 = dlsym(iVar4,DAT_000167dc +
                                                                                      0x15ce4);
                                                                  *(int *)(DAT_000167e0 + 0x15cf0) =
                                                                       iVar5;
                                                                  if (iVar5 != 0) {
                                                                    iVar5 = dlsym(iVar4,DAT_000167e4
                                                                                        + 0x15d08);
                                                                    *(int *)(DAT_000167e8 + 0x15d14)
                                                                         = iVar5;
                                                                    if (iVar5 != 0) {
                                                                      iVar5 = dlsym(iVar4,
                                                  DAT_000167ec + 0x15d2c);
                                                  *(int *)(DAT_000167f0 + 0x15d38) = iVar5;
                                                  if (iVar5 != 0) {
                                                    iVar5 = dlsym(iVar4,DAT_000167f4 + 0x15d50);
                                                    *(int *)(DAT_000167f8 + 0x15d5c) = iVar5;
                                                    if (iVar5 != 0) {
                                                      iVar5 = dlsym(iVar4,DAT_000167fc + 0x15d74);
                                                      *(int *)(DAT_00016800 + 0x15d80) = iVar5;
                                                      if (iVar5 != 0) {
                                                        iVar5 = dlsym(iVar4,DAT_00016804 + 0x15d98);
                                                        *(int *)(DAT_00016808 + 0x15da4) = iVar5;
                                                        if (iVar5 != 0) {
                                                          iVar5 = dlsym(iVar4,DAT_0001680c + 0x15dbc
                                                                       );
                                                          *(int *)(DAT_00016810 + 0x15dc8) = iVar5;
                                                          if (iVar5 != 0) {
                                                            iVar5 = dlsym(iVar4,DAT_00016814 +
                                                                                0x15de0);
                                                            *(int *)(DAT_00016818 + 0x15dec) = iVar5
                                                            ;
                                                            if (iVar5 != 0) {
                                                              iVar5 = dlsym(iVar4,DAT_0001681c +
                                                                                  0x15e04);
                                                              *(int *)(DAT_00016820 + 0x15e10) =
                                                                   iVar5;
                                                              if (iVar5 != 0) {
                                                                iVar5 = dlsym(iVar4,DAT_00016824 +
                                                                                    0x15e28);
                                                                *(int *)(DAT_00016828 + 0x15e34) =
                                                                     iVar5;
                                                                if (iVar5 != 0) {
                                                                  iVar5 = dlsym(iVar4,DAT_0001682c +
                                                                                      0x15e4c);
                                                                  *(int *)(DAT_00016830 + 0x15e58) =
                                                                       iVar5;
                                                                  if (iVar5 != 0) {
                                                                    iVar5 = dlsym(iVar4,DAT_00016834
                                                                                        + 0x15e70);
                                                                    *(int *)(DAT_00016838 + 0x15e7c)
                                                                         = iVar5;
                                                                    if (iVar5 != 0) {
                                                                      iVar5 = dlsym(iVar4,
                                                  DAT_0001683c + 0x15e94);
                                                  *(int *)(DAT_00016840 + 0x15ea0) = iVar5;
                                                  if (iVar5 != 0) {
                                                    iVar5 = dlsym(iVar4,DAT_00016844 + 0x15eb8);
                                                    *(int *)(DAT_00016848 + 0x15ec4) = iVar5;
                                                    if (iVar5 != 0) {
                                                      iVar5 = dlsym(iVar4,DAT_0001684c + 0x15edc);
                                                      *(int *)(DAT_00016850 + 0x15ee8) = iVar5;
                                                      if (iVar5 != 0) {
                                                        iVar5 = dlsym(iVar4,DAT_00016854 + 0x15f00);
                                                        *(int *)(DAT_00016858 + 0x15f0c) = iVar5;
                                                        if (iVar5 != 0) {
                                                          iVar5 = dlsym(iVar4,DAT_0001685c + 0x15f24
                                                                       );
                                                          *(int *)(DAT_00016860 + 0x15f30) = iVar5;
                                                          if (iVar5 != 0) {
                                                            iVar5 = dlsym(iVar4,DAT_00016864 +
                                                                                0x15f48);
                                                            *(int *)(DAT_00016868 + 0x15f54) = iVar5
                                                            ;
                                                            if (iVar5 != 0) {
                                                              iVar5 = dlsym(iVar4,DAT_0001686c +
                                                                                  0x15f6c);
                                                              *(int *)(DAT_00016870 + 0x15f78) =
                                                                   iVar5;
                                                              if (iVar5 != 0) {
                                                                uVar6 = dlsym(iVar4,DAT_00016874 +
                                                                                    90000);
                                                                iVar5 = DAT_0001687c + 0x15fa4;
                                                                *(undefined4 *)
                                                                 (DAT_00016878 + 0x15fa0) = uVar6;
                                                                uVar6 = dlsym(iVar4,iVar5);
                                                                iVar5 = DAT_00016884 + 0x15fc0;
                                                                *(undefined4 *)
                                                                 (DAT_00016880 + 0x15fbc) = uVar6;
                                                                uVar6 = dlsym(iVar4,iVar5);
                                                                iVar5 = DAT_0001688c + 0x15fdc;
                                                                *(undefined4 *)
                                                                 (DAT_00016888 + 0x15fd8) = uVar6;
                                                                piVar7 = (int *)dlsym(iVar4,iVar5);
                                                                iVar4 = *(int *)(DAT_00016890 +
                                                                                0x15ff4);
                                                                if (piVar7 == (int *)0x0) {
                                                                  iVar5 = 0;
                                                                }
                                                                else {
                                                                  iVar5 = *piVar7;
                                                                }
                                                                *(int *)(DAT_00016894 + 0x15ff8) =
                                                                     iVar5;
                                                                if (((iVar4 != 0) &&
                                                                    (*(int *)(DAT_00016898 + 0x1601c
                                                                             ) != 0)) &&
                                                                   (*(int *)(DAT_0001689c + 0x16030)
                                                                    != 0 && iVar5 != 0)) {
                                                                  __src = *(void **)(DAT_000168a0 +
                                                                                    0x1604c);
                                                                  pvVar9 = *(void **)(DAT_000168a4 +
                                                                                     0x16054);
                                                                  *(int *)(DAT_000168a8 + 0x1605c) =
                                                                       (int)__src + -0x8c2b8;
                                                                  if (((((void *)((int)__src +
                                                                                 -0x65000) == pvVar9
                                                                        ) && ((void *)((int)__src +
                                                                                      -0x65758) ==
                                                                              *(void **)(
                                                  DAT_000168ac + 0x1608c))) &&
                                                  ((*(int *)(DAT_000168b0 + 0x160a8) ==
                                                    (int)__src + -0x6a064 &&
                                                   ((*(int *)(DAT_000168b4 + 0x160c4) ==
                                                     (int)__src + -0x63e58 &&
                                                    (*(int *)(DAT_000168b8 + 0x160e0) ==
                                                     (int)__src + -0x6385c)))))) &&
                                                  (((void *)((int)__src + -0x65d4) ==
                                                    *(void **)(DAT_000168bc + 0x16100) &&
                                                   (((((void *)((int)__src + 0x380) ==
                                                       *(void **)(DAT_000168c0 + 0x1611c) &&
                                                      (*(int *)(DAT_000168c4 + 0x16138) ==
                                                       (int)__src + 0xd0)) &&
                                                     (*(int *)(DAT_000168c8 + 0x16150) ==
                                                      (int)__src + 0x928)) &&
                                                    ((*(int *)(DAT_000168cc + 0x16170) ==
                                                      (int)__src + 0x79c &&
                                                     (*(int *)(DAT_000168d0 + 0x16188) ==
                                                      (int)__src + -0x88)))))))) {
                                                    memcpy(local_2c,(void *)((int)__src + -0x65000),
                                                           4);
                                                    if (((((local_2c[0] == -0x16d2b010) &&
                                                          ((memcpy(local_2c,(void *)((int)__src +
                                                                                    -0x65758),4),
                                                           local_2c[0] == -0x16d2b010 &&
                                                           (memcpy(local_2c,__src,4),
                                                           local_2c[0] == -0x1a6ffff8)))) &&
                                                         (memcpy(local_2c,(void *)((int)__src +
                                                                                  0x380),4),
                                                         local_2c[0] == -0x16d2bf10)) &&
                                                        (((memcpy(local_2c,(void *)((int)__src +
                                                                                   -0x65d4),4),
                                                          local_2c[0] == -0x16d2ba10 &&
                                                          (memcpy(local_2c,(void *)((int)__src +
                                                                                   -0x67d50),4),
                                                          local_2c[0] == -0x1cfecc30)) &&
                                                         (memcpy(local_2c,(void *)((int)__src +
                                                                                  -0x67d5c),4),
                                                         local_2c[0] == -0x1cfeec30)))) &&
                                                       (pvVar9 = (void *)dlsym(0xfffffffe,
                                                                               DAT_000168d4 +
                                                                               0x16298),
                                                       pvVar9 != (void *)0x0)) {
                                                      *(int *)(DAT_000168d8 + 0x162b8) =
                                                           (int)pvVar9 + -0x158680;
                                                      memcpy(local_2c,pvVar9,4);
                                                      if (local_2c[0] == -0x16d2bc10) {
                                                        iVar11 = *(int *)(DAT_000168dc + 0x162f0);
                                                        piVar12 = (int *)(DAT_000168e4 + 0x16304);
                                                        piVar15 = (int *)(DAT_000168e8 + 0x1630c);
                                                        piVar7 = (int *)(DAT_000168ec + 0x16318);
                                                        iVar13 = DAT_000168f0 + 0x16324;
                                                        piVar10 = (int *)(DAT_000168f4 + 0x16330);
                                                        uVar6 = *(undefined4 *)
                                                                 (DAT_000168e0 + 0x162fc);
                                                        piVar14 = (int *)(DAT_000168f8 + 0x16344);
                                                        *piVar7 = iVar11 + 0xb8e80;
                                                        *piVar15 = iVar11 + 0xb8be0;
                                                        iVar5 = DAT_00016900;
                                                        iVar4 = DAT_000168fc;
                                                        *piVar10 = iVar11 + 0xb8bd0;
                                                        *piVar14 = iVar11 + 0xb90a0;
                                                        iVar1 = DAT_00016904;
                                                        *piVar12 = iVar11 + 0xb9910;
                                                        piVar10 = (int *)(DAT_00016908 + 0x163a8);
                                                        *(int *)(iVar4 + 0x1637c) = iVar11 + 0xb8bd4
                                                        ;
                                                        iVar4 = DAT_0001690c;
                                                        *(int *)(iVar5 + 0x1638c) = iVar11 + 0xb9ce0
                                                        ;
                                                        *(int *)(iVar1 + 0x1639c) = iVar11 + 0xb8bc8
                                                        ;
                                                        *piVar10 = iVar11 + 0xb8bd8;
                                                        iVar3 = DAT_00016914;
                                                        iVar2 = DAT_00016910;
                                                        *(int *)(iVar4 + 0x163c0) =
                                                             (int)pvVar9 + 0x4d784;
                                                        iVar4 = DAT_00016918 + 0x1640c;
                                                        *(int *)(iVar2 + 0x163f4) = iVar11 + 0xb8bdc
                                                        ;
                                                        *(int *)(iVar3 + 0x163f8) =
                                                             (int)pvVar9 + 0x4d9ac;
                                                        iVar4 = FUN_000147f8((int)pvVar9 + 0x4d784,
                                                                             uVar6,iVar4,
                                                                             (int)pvVar9 + -0x461ac,
                                                                             iVar13);
                                                        if ((iVar4 != 0) &&
                                                           (iVar4 = FUN_000147f8(*(int *)(iVar3 + 
                                                  0x163f8),*(undefined4 *)(DAT_0001691c + 0x16440),
                                                  DAT_00016924 + 0x16458,(int)pvVar9 + -0x461ac,
                                                  DAT_00016920 + 0x16448), iVar4 != 0)) {
                                                    iVar11 = (int)&DAT_00018854 + iVar11;
                                                    iVar4 = FUN_000147f8(*piVar7,*(undefined4 *)
                                                                                  (DAT_00016928 +
                                                                                  0x16484),
                                                                         DAT_00016930 + 0x164a4,
                                                                         iVar11,DAT_0001692c +
                                                                                0x16490);
                                                    if (((iVar4 != 0) &&
                                                        ((iVar4 = FUN_000147f8(*piVar14,*(undefined4
                                                                                          *)(
                                                  DAT_00016934 + 0x164c0),DAT_0001693c + 0x164d8,
                                                  iVar11,DAT_00016938 + 0x164c8), iVar4 != 0 &&
                                                  (iVar4 = FUN_000147f8(*piVar12,*(undefined4 *)
                                                                                  (DAT_00016940 +
                                                                                  0x164f4),
                                                                        DAT_00016948 + 0x1650c,
                                                                        iVar11,DAT_00016944 +
                                                                               0x164fc), iVar4 != 0)
                                                  ))) && ((iVar4 = FUN_000147f8(*(int *)(iVar5 + 
                                                  0x1638c),*(undefined4 *)(DAT_0001694c + 0x16528),
                                                  DAT_00016954 + 0x16540,iVar11,
                                                  DAT_00016950 + 0x16530), iVar4 != 0 &&
                                                  ((((iVar4 = FUN_000147f8(*(int *)(iVar1 + 0x1639c)
                                                                           ,*(undefined4 *)
                                                                             (DAT_00016958 + 0x1655c
                                                                             ),DAT_00016960 +
                                                                               0x16574,0,
                                                                           DAT_0001695c + 0x16564),
                                                     iVar4 != 0 &&
                                                     (iVar4 = FUN_000147f8(*(undefined4 *)
                                                                            (DAT_00016964 + 0x16590)
                                                                           ,*(undefined4 *)
                                                                             (DAT_00016968 + 0x16598
                                                                             ),DAT_00016970 +
                                                                               0x165b0,0,
                                                                           DAT_0001696c + 0x1659c),
                                                     iVar4 != 0)) &&
                                                    (iVar4 = FUN_000147f8(*(undefined4 *)
                                                                           (DAT_00016974 + 0x165cc),
                                                                          *(undefined4 *)
                                                                           (DAT_00016978 + 0x165d4),
                                                                          DAT_00016980 + 0x165ec,0,
                                                                          DAT_0001697c + 0x165d8),
                                                    iVar4 != 0)) &&
                                                   (((iVar4 = FUN_000147f8(*(undefined4 *)
                                                                            (DAT_00016984 + 0x16608)
                                                                           ,*(undefined4 *)
                                                                             (DAT_00016988 + 0x16610
                                                                             ),DAT_00016990 +
                                                                               0x16628,0,
                                                                           DAT_0001698c + 0x16614),
                                                     iVar4 != 0 &&
                                                     (iVar4 = FUN_000147f8(*(undefined4 *)
                                                                            (DAT_00016994 + 0x16644)
                                                                           ,*(undefined4 *)
                                                                             (DAT_00016998 + 0x1664c
                                                                             ),DAT_000169a0 +
                                                                               0x16664,0,
                                                                           DAT_0001699c + 0x16650),
                                                     iVar4 != 0)) &&
                                                    (iVar4 = FUN_000147f8(*(undefined4 *)
                                                                           (DAT_000169a4 + 0x16680),
                                                                          *(undefined4 *)
                                                                           (DAT_000169a8 + 0x16688),
                                                                          DAT_000169b0 + 0x166a0,0,
                                                                          DAT_000169ac + 0x1668c),
                                                    iVar4 != 0)))))))) {
                                                    iVar4 = DAT_000169b8 + 0x166c4;
                                                    *(undefined4 *)(DAT_000169b4 + 0x166c0) = 1;
                                                    piVar7 = (int *)(DAT_00019e1c + 0x19d58);
                                                    *piVar7 = iVar4;
                                                    __name = (char *)(DAT_00019e20 + 0x19d6c);
                                                    iVar4 = access(__name,1);
                                                    if (iVar4 != 0) {
                                                      UNRECOVERED_JUMPTABLE = (code *)*piVar7;
                                                      if (UNRECOVERED_JUMPTABLE != (code *)0x0) {
                                                        puVar8 = (undefined4 *)__get_errno_ptr();
                    /* WARNING: Could not recover jumptable at 0x00019da0. Too many branches */
                    /* WARNING: Treating indirect jump as call */
                                                        (*UNRECOVERED_JUMPTABLE)
                                                                  (DAT_00019e24 + 0x19da4,__name,
                                                                   *puVar8);
                                                        return;
                                                      }
                                                      return;
                                                    }
                                                    __mutex = (pthread_mutex_t *)
                                                              (DAT_00019e28 + 0x19db0);
                                                    pthread_mutex_lock(__mutex);
                                                    *(undefined4 *)(DAT_00019e2c + 0x19dc4) = 0;
                                                    pthread_mutex_unlock(__mutex);
                                                    iVar4 = pthread_create((pthread_t *)
                                                                           (DAT_00019e30 + 0x19de0),
                                                                           (pthread_attr_t *)0x0,
                                                                           (__start_routine *)
                                                                           (DAT_00019e34 + 0x19de4),
                                                                           (void *)0x0);
                                                    if (iVar4 != 0) {
                                                      if ((code *)*piVar7 != (code *)0x0) {
                    /* WARNING: Could not recover jumptable at 0x00019e04. Too many branches */
                    /* WARNING: Treating indirect jump as call */
                                                        (*(code *)*piVar7)(DAT_00019e38 + 0x19e08);
                                                        return;
                                                      }
                                                      return;
                                                    }
                                                    *(undefined4 *)(DAT_00019e3c + 0x19e18) = 1;
                                                    return;
                                                  }
                                                  }
                                                  FUN_0001354c();
                                                  return;
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  return;
}



/* Function: _FINI_1 @ 000169bc */

void _FINI_1(void)

{
  bool bVar1;
  pthread_cond_t *__cond;
  __pid_t _Var2;
  undefined4 *puVar3;
  undefined4 *puVar4;
  int *piVar5;
  pthread_mutex_t *ppVar6;
  code *pcVar7;
  int iVar8;
  
  if (*(int *)(DAT_000169e4 + 0x169cc) != 0) {
    FUN_0001354c();
  }
  FUN_00019e40();
  ppVar6 = (pthread_mutex_t *)(DAT_000184a8 + 0x18368);
  piVar5 = (int *)(DAT_000184ac + 0x1836c);
  pthread_mutex_lock(ppVar6);
  __cond = (pthread_cond_t *)(DAT_000184b4 + 0x18388);
  *(undefined4 *)(DAT_000184b0 + 0x18384) = 1;
  pthread_cond_broadcast(__cond);
  pthread_cond_broadcast((pthread_cond_t *)(DAT_000184b8 + 0x18398));
  if ((*piVar5 == 0) || (_Var2 = getpid(), _Var2 != *(int *)(DAT_000184bc + 0x183b4))) {
    bVar1 = false;
    puVar3 = (undefined4 *)(DAT_000184c4 + 0x183d8);
    ppVar6 = (pthread_mutex_t *)(DAT_000184c8 + 0x183dc);
    *(undefined4 *)(DAT_000184c0 + 0x183d4) = 0;
    *puVar3 = 0;
    pthread_mutex_unlock(ppVar6);
    if (*(int *)(DAT_000184cc + 0x183f0) == 0) goto LAB_000183f8;
    pcVar7 = (code *)0x0;
    iVar8 = 0;
LAB_0001841c:
    pthread_join(*(pthread_t *)(DAT_000184d8 + 0x1842c),(void **)0x0);
    *(undefined4 *)(DAT_000184dc + 0x18440) = 0;
  }
  else {
    puVar3 = (undefined4 *)(DAT_000184e0 + 0x1846c);
    iVar8 = *piVar5;
    puVar4 = (undefined4 *)(DAT_000184e4 + 0x18474);
    *piVar5 = 0;
    pcVar7 = (code *)*puVar3;
    *puVar4 = 0;
    pthread_mutex_unlock(ppVar6);
    bVar1 = iVar8 != 0 && pcVar7 != (code *)0x0;
    if (*(int *)(DAT_000184e8 + 0x18494) != 0) goto LAB_0001841c;
  }
  if (bVar1) {
    (*pcVar7)(iVar8);
  }
LAB_000183f8:
  unlink((char *)(DAT_000184d0 + 0x18404));
  unlink((char *)(DAT_000184d4 + 0x18414));
  return;
}



/* Function: FUN_000169e8 @ 000169e8 */

int * FUN_000169e8(uint param_1,int param_2)

{
  int *piVar1;
  int iVar2;
  int *piVar3;
  int iVar4;
  int *__s;
  
  iVar2 = 0;
  piVar3 = (int *)(DAT_00016ad0 + 0x169fc);
  piVar1 = piVar3;
  do {
    __s = piVar3;
    if (*piVar3 != 0) {
      if (*(ushort *)(piVar3 + 1) == param_1) {
        piVar1 = (int *)(iVar2 * 0x6e8 + DAT_00016ad8 + 0x16ab0);
        iVar2 = *(int *)(DAT_00016adc + 0x16ab4) + 1;
        *(int *)(DAT_00016adc + 0x16ab4) = iVar2;
        piVar1[2] = iVar2;
        return piVar1;
      }
      __s = piVar1;
      if ((*piVar1 != 0) && ((uint)piVar3[2] < (uint)piVar1[2])) {
        __s = piVar3;
      }
    }
    iVar2 = iVar2 + 1;
    piVar3 = piVar3 + 0x1ba;
    piVar1 = __s;
  } while (piVar3 != (int *)(DAT_00016ad0 + 0x246fc));
  if (param_2 == 0) {
    return (int *)0x0;
  }
  memset(__s,0,0x6e8);
  iVar2 = DAT_00016ad4;
  *__s = 1;
  *(short *)(__s + 1) = (short)param_1;
  iVar4 = *(int *)(iVar2 + 0x16a84) + 1;
  *(int *)(iVar2 + 0x16a84) = iVar4;
  __s[2] = iVar4;
  return __s;
}



/* Function: FUN_00016ae0 @ 00016ae0 */

int FUN_00016ae0(void)

{
  int iVar1;
  timespec local_10;
  
  iVar1 = clock_gettime(2,&local_10);
  if (iVar1 == 0) {
    iVar1 = FUN_0001b7ec(local_10.tv_nsec,local_10.tv_nsec >> 0x1f,&DAT_000f4240,0);
    iVar1 = local_10.tv_sec * 1000 + iVar1;
  }
  else {
    iVar1 = 0;
  }
  return iVar1;
}



/* Function: FUN_00016b30 @ 00016b30 */

bool FUN_00016b30(char *param_1,int param_2,uint param_3,undefined4 param_4)

{
  int iVar1;
  ssize_t sVar2;
  int *piVar3;
  uint uVar4;
  
  iVar1 = open(param_1,0x301,0x1a4,param_4,param_4);
  if (iVar1 < 0) {
    return false;
  }
  if (param_3 == 0) {
    uVar4 = 0;
  }
  else {
    uVar4 = 0;
    do {
      sVar2 = write(iVar1,(void *)(param_2 + uVar4),param_3 - uVar4);
      if (sVar2 < 1) {
        if ((sVar2 == 0) || (piVar3 = (int *)__get_errno_ptr(), *piVar3 != 4)) break;
      }
      else {
        uVar4 = uVar4 + sVar2;
      }
    } while (uVar4 < param_3);
  }
  iVar1 = close(iVar1);
  return iVar1 == 0 && uVar4 == param_3;
}



/* Function: FUN_00016bc4 @ 00016bc4 */

void FUN_00016bc4(void)

{
  int iVar1;
  int *piVar2;
  
  piVar2 = (int *)(DAT_00016cfc + 0x16bd4);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d1c + 0x16c7c);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d00 + 0x16be8);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d20 + 0x16c94);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d04 + 0x16bfc);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d24 + 0x16cac);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d08 + 0x16c10);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d28 + 0x16cc4);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d0c + 0x16c24);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d2c + 0x16cdc);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d10 + 0x16c38);
  if (*piVar2 == 0) {
    iVar1 = dlsym(0xfffffffd,DAT_00016d30 + 0x16cf4);
    *piVar2 = iVar1;
  }
  piVar2 = (int *)(DAT_00016d14 + 0x16c4c);
  if (*piVar2 != 0) {
    return;
  }
  iVar1 = dlsym(0xfffffffd,DAT_00016d18 + 0x16c64);
  *piVar2 = iVar1;
  return;
}



/* Function: FUN_00016d34 @ 00016d34 */

void FUN_00016d34(void *param_1,uint param_2,char *param_3)

{
  size_t __n;
  
  if (param_3 == (char *)0x0) {
    if (param_2 != 0) {
      __n = 0;
      goto LAB_00016d68;
    }
    param_3 = (char *)((int)&DAT_00016da0 + DAT_00016da0);
LAB_00016d5c:
    __n = param_2 - 1;
  }
  else {
    __n = strlen(param_3);
    if (param_2 <= __n) goto LAB_00016d5c;
  }
  if (__n != 0) {
    memcpy(param_1,param_3,__n);
  }
LAB_00016d68:
  *(undefined1 *)((int)param_1 + __n) = 0;
  return;
}



/* Function: FUN_00016da4 @ 00016da4 */

void FUN_00016da4(void)

{
  longlong lVar1;
  pthread_cond_t *__cond;
  int iVar2;
  uint extraout_r1;
  uint uVar3;
  int *piVar4;
  int iVar5;
  uint uVar6;
  int iVar7;
  int iVar8;
  uint uVar9;
  uint uVar10;
  undefined4 uVar11;
  uint uVar12;
  uint uVar13;
  bool bVar14;
  timespec local_30;
  
  if ((*(uint *)(DAT_000173cc + 0x16db4) & 2) == 0) {
    uVar12 = 0;
    uVar13 = uVar12;
  }
  else {
    uVar13 = (uint)*(byte *)(DAT_000173cc + 0x16dce);
    if (uVar13 - 1 < 6) {
      uVar12 = 1;
    }
    else {
      uVar12 = 0;
    }
  }
  if ((*(uint *)(DAT_000173cc + 0x16db4) & 0x100000) == 0) {
    uVar6 = 1;
  }
  else {
    uVar6 = 0;
    if (*(char *)(DAT_000173d0 + 0x16e24) != '\0') {
      uVar6 = 1;
    }
  }
  if ((*(int *)(DAT_000173d4 + 0x16e18) == 0) ||
     (iVar7 = FUN_000169e8(*(undefined2 *)(DAT_00017414 + 0x1709c),0), iVar7 == 0)) {
    iVar7 = 0;
    iVar8 = 1000;
    uVar9 = 0;
    uVar10 = 0;
  }
  else {
    uVar9 = *(uint *)(iVar7 + 0x10);
    if ((uVar9 & 8) == 0) {
      uVar9 = 0;
      iVar8 = 1000;
      uVar10 = uVar9;
    }
    else {
      uVar10 = (uint)*(byte *)(iVar7 + 0x2c);
      if ((uVar9 & 0x800) == 0) {
        iVar8 = 1000;
      }
      else {
        iVar8 = (int)*(short *)(iVar7 + 0x30);
      }
      if ((uVar9 & 0x100) == 0) {
        uVar9 = 0;
      }
      else {
        uVar9 = (uint)*(byte *)(iVar7 + 0x2e);
      }
    }
  }
  piVar4 = (int *)(DAT_000173d8 + 0x16e40);
  iVar2 = *piVar4 + 1;
  if (iVar2 == 0) {
    *piVar4 = *piVar4 + 2;
  }
  else {
    *piVar4 = iVar2;
  }
  iVar2 = clock_gettime(0,&local_30);
  uVar3 = extraout_r1;
  if (iVar2 != 0) {
    uVar3 = 0;
  }
  lVar1 = (ulonglong)uVar3 << 0x20;
  if (iVar2 == 0) {
    lVar1 = FUN_0001b7ec(local_30.tv_nsec,local_30.tv_nsec >> 0x1f,&DAT_000f4240,0);
    lVar1 = (ulonglong)(uint)local_30.tv_sec * 1000 + lVar1;
  }
  iVar2 = DAT_000173dc;
  *(longlong *)(DAT_000173dc + 0x16ea4) = lVar1;
  *(uint *)(iVar2 + 0x16eb0) = uVar13;
  if ((uVar6 & uVar12) == 0) {
    uVar12 = uVar10 - 0x1c;
    *(undefined4 *)(iVar2 + 0x16eac) = 0;
    *(undefined4 *)(iVar2 + 0x16eb4) = 0;
    uVar13 = 0;
    if (0x12 < uVar12) goto LAB_00017208;
LAB_00016ee0:
    uVar10 = uVar10 - 0x1b;
LAB_00016ee4:
    iVar2 = DAT_000173e4;
    if (uVar13 != 0x21) {
      uVar12 = 0xffffffff;
    }
    *(uint *)(DAT_000173e4 + 0x16f10) = uVar10;
    if (uVar13 != 0x21) {
      *(uint *)(iVar2 + 0x16f14) = uVar12;
    }
    else {
      if (uVar9 != 1) {
        iVar8 = -iVar8;
      }
      iVar8 = iVar8 + 0xb4;
      if (iVar8 == 0) {
        iVar8 = 0x168;
      }
      *(int *)(DAT_000173e8 + 0x16f38) = iVar8;
    }
  }
  else {
    uVar13 = 1;
    *(undefined4 *)(iVar2 + 0x16eac) = 1;
    uVar11 = 0;
    if (uVar10 == 0) {
LAB_000171fc:
      *(undefined4 *)(DAT_00017418 + 0x17220) = uVar11;
    }
    else {
      if (uVar10 != 1 && uVar10 != 0x14) {
        if (uVar10 == 2 || uVar10 == 0x15) {
          uVar13 = 8;
          goto LAB_00016ec4;
        }
        if ((uVar10 == 5 || (uVar10 & 0xfffffff7) == 3) || (uVar12 = uVar10 - 0xf, uVar12 < 3))
        goto LAB_00016ec4;
        if (uVar10 == 4 || (uVar10 & 0xfffffff7) == 0x12) {
          if (iVar8 == 0 || 0x168 < iVar8 + 0xb4U) {
            if (uVar9 == 1) {
              uVar13 = 0xc;
            }
            else {
              uVar13 = 0xb;
            }
          }
          else if (iVar8 < 0) {
            uVar13 = 0xb;
          }
          else {
            uVar13 = 0xc;
          }
          goto LAB_00016ec4;
        }
        bVar14 = 0x12 < uVar10;
        if (uVar10 != 0x13) {
          bVar14 = uVar10 != 6;
        }
        if (bVar14 && (uVar10 != 0x13 && uVar10 != 7)) {
          if (0x12 < uVar10 - 0x1c) {
            if (uVar10 == 8) {
              if (iVar8 == 0 || 0x168 < iVar8 + 0xb4U) {
                if (uVar9 == 1) {
                  uVar11 = 0x15;
                }
                else {
                  uVar11 = 0x16;
                }
              }
              else if (iVar8 < 0) {
                uVar11 = 0x15;
              }
              else {
                uVar11 = 0x16;
              }
            }
            else if (uVar10 == 9) {
              if (iVar8 == 0 || 0x168 < iVar8 + 0xb4U) {
                if (uVar9 == 1) {
                  uVar11 = 0xd;
                }
                else {
                  uVar11 = 0xe;
                }
              }
              else if (iVar8 < 0) {
                uVar11 = 0xd;
              }
              else {
                uVar11 = 0xe;
              }
            }
            else if (uVar10 == 0x1b || (uVar10 - 10 & 0xfffffffd) == 0) {
              uVar11 = 0x27;
            }
            else if (uVar10 == 0xd) {
              uVar11 = 3;
            }
            else if (uVar10 == 0xe) {
              uVar11 = 4;
            }
            else if (uVar10 == 0x34) {
              uVar11 = 0x1b;
            }
            else if (uVar10 == 0x35) {
              uVar11 = 0x1c;
            }
            else if (uVar10 == 0x16) {
              uVar11 = 0x15;
            }
            else if (uVar10 == 0x17) {
              uVar11 = 0x16;
            }
            else if (uVar10 == 0x18) {
              uVar11 = 0x29;
            }
            else if (uVar10 == 0x19) {
              uVar11 = 0x2a;
            }
            else if (uVar10 == 0x2f) {
              uVar11 = 9;
            }
            else if (uVar10 == 0x30) {
              uVar11 = 10;
            }
            else if (uVar10 == 0x31) {
              uVar11 = 5;
            }
            else if (uVar10 == 0x32) {
              uVar11 = 6;
            }
            else if (uVar10 == 0x33) {
              if (iVar8 == 0 || 0x168 < iVar8 + 0xb4U) {
                uVar11 = 0x1d;
              }
              else if (iVar8 < 0) {
                uVar11 = 0x1b;
              }
              else {
                uVar11 = 0x1c;
              }
            }
            else {
              uVar11 = 0;
            }
            goto LAB_000171fc;
          }
          if (uVar9 != 1) goto LAB_0001729c;
          uVar13 = (iVar8 + 0xb4U < 0x169) + 0x20;
          *(uint *)((int)&DAT_000173d4 + DAT_00017428) = uVar13;
          goto LAB_00016edc;
        }
        if (uVar9 == 1) {
          uVar13 = 0x20;
        }
        else {
LAB_0001729c:
          uVar13 = 0x22;
        }
        uVar6 = uVar10 - 0x1c;
        if (iVar8 + 0xb4U < 0x169) {
          uVar13 = uVar13 + 1;
        }
        uVar12 = DAT_00017424 + 0x172c0;
        if (0x12 < uVar6) {
          uVar10 = 0xffffffff;
        }
        *(uint *)(DAT_00017424 + 0x172d8) = uVar13;
        if (uVar6 < 0x13) goto LAB_00016edc;
        uVar13 = uVar13 & 0xfffffffd;
        goto LAB_00016ee4;
      }
      uVar13 = 7;
LAB_00016ec4:
      uVar12 = uVar10 - 0x1c;
      *(uint *)(DAT_000173e0 + 0x16ef0) = uVar13;
      if (uVar12 < 0x13) {
LAB_00016edc:
        uVar13 = uVar13 & 0xfffffffd;
        goto LAB_00016ee0;
      }
    }
LAB_00017208:
    iVar8 = DAT_0001741c;
    *(undefined4 *)(DAT_0001741c + 0x17234) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x17238) = 0xffffffff;
  }
  iVar8 = DAT_000173f0;
  uVar13 = *(uint *)(DAT_000173ec + 94000);
  if ((uVar13 & 0x400) == 0) {
    iVar2 = -1;
  }
  else {
    iVar2 = *(int *)(DAT_000173ec + 0x16f60);
    if (iVar2 < 0) {
      iVar2 = -1;
    }
  }
  iVar5 = -1;
  *(int *)(DAT_000173f0 + 0x16f88) = iVar2;
  *(undefined4 *)(iVar8 + 0x16f8c) = 0xffffffff;
  if (((uVar13 & 0x80) != 0) && (iVar5 = *(int *)(DAT_000173f4 + 0x16fa8), iVar5 < 0)) {
    iVar5 = -1;
  }
  *(int *)(DAT_000173f8 + 0x16fc8) = iVar5;
  iVar8 = DAT_00017400;
  if ((uVar13 & 0x40) == 0) {
    uVar13 = 0xffffffff;
  }
  else {
    uVar13 = *(uint *)(DAT_000173fc + 0x16ff8);
    if (*(int *)(DAT_000173fc + 0x16ffc) != 0 || 0x7fffffff < uVar13) {
      uVar13 = 0xffffffff;
    }
  }
  iVar2 = DAT_00017400 + 0x17010;
  iVar5 = DAT_00017404 + 0x17028;
  *(uint *)(DAT_00017400 + 0x1700c) = uVar13;
  FUN_00016d34(iVar2,0xc0,iVar5);
  if (iVar7 == 0) {
    iVar7 = DAT_00017420 + 0x17234;
    FUN_00016d34(iVar8 + 0x170d0,0xc0,iVar7);
  }
  else {
    FUN_00016d34(iVar8 + 0x170d0,0xc0,iVar7 + 0x158);
    iVar7 = iVar7 + 0x58;
  }
  iVar8 = DAT_00017408;
  FUN_00016d34(DAT_00017408 + 0x171cc,0xc0,iVar7);
  if (*(int *)(iVar8 + 0x17028) == 0) {
    *(undefined1 *)(iVar8 + 0x1704c) = 0;
    *(undefined1 *)(iVar8 + 0x1710c) = 0;
    *(undefined1 *)(iVar8 + 0x171cc) = 0;
    *(undefined4 *)(iVar8 + 0x17034) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x17038) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x1703c) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x17040) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x17044) = 0xffffffff;
    *(undefined4 *)(iVar8 + 0x17048) = 0xffffffff;
  }
  __cond = (pthread_cond_t *)(DAT_00017410 + 0x17068);
  *(undefined4 *)(DAT_0001740c + 0x17064) = 1;
  pthread_cond_signal(__cond);
  return;
}



/* Function: FUN_0001742c @ 0001742c */

void FUN_0001742c(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  void *__s;
  uint *puVar2;
  uint uVar3;
  undefined4 uVar4;
  undefined4 *puVar5;
  char cVar6;
  uint uVar7;
  uint local_350 [6];
  char local_336;
  undefined1 local_330;
  undefined4 local_324;
  undefined4 local_320;
  undefined4 local_310;
  undefined4 uStack_30c;
  undefined1 auStack_308 [256];
  undefined1 auStack_208 [448];
  short local_48;
  short local_28;
  
  pthread_mutex_lock((pthread_mutex_t *)(DAT_0001776c + 0x1744c));
  FUN_00018d94(param_1,param_2,local_350);
  cVar6 = local_336;
  uVar3 = local_350[0];
  uVar7 = local_350[0] & 2;
  if (uVar7 == 0) {
    if ((local_350[0] & 0x4000) == 0) goto LAB_000174a4;
LAB_00017618:
    iVar1 = DAT_00017794;
    if (((local_28 == 0) || (*(int *)(DAT_0001779c + 0x17668) == 0)) ||
       (local_48 != *(short *)(DAT_000177a0 + 0x17680))) {
      puVar5 = (undefined4 *)(DAT_00017794 + 0x17638);
      *(uint *)(DAT_00017794 + 0x17634) = *(uint *)(DAT_00017794 + 0x17634) & 0xfffffbff;
      *(undefined4 *)(iVar1 + 0x17638) = *puVar5;
    }
    cVar6 = local_336;
    if (uVar7 == 0) goto LAB_000174a4;
  }
  else {
    if (local_336 == '\0') {
      memset((void *)(DAT_000177b8 + 0x17738),0,0x330);
      memset((void *)(DAT_000177bc + 0x1774c),0,0xdd00);
      *(undefined4 *)(DAT_000177c0 + 0x1775c) = 0;
    }
    if ((uVar3 & 0x4000) != 0) goto LAB_00017618;
  }
  iVar1 = DAT_00017770;
  puVar2 = (uint *)(DAT_00017770 + 0x17498);
  puVar5 = (undefined4 *)(DAT_00017770 + 0x1749c);
  *(char *)(DAT_00017770 + 0x174b2) = cVar6;
  *puVar2 = *puVar2 | 2;
  *(undefined4 *)(iVar1 + 0x1749c) = *puVar5;
LAB_000174a4:
  iVar1 = DAT_00017774;
  if ((uVar3 & 0x100000) != 0) {
    puVar2 = (uint *)(DAT_00017774 + 0x174c4);
    puVar5 = (undefined4 *)(DAT_00017774 + 0x174c8);
    *(undefined1 *)(DAT_00017774 + 0x174e4) = local_330;
    *puVar2 = *puVar2 | 0x100000;
    *(undefined4 *)(iVar1 + 0x174c8) = *puVar5;
  }
  iVar1 = DAT_000177b4;
  if ((uVar3 & 8) != 0) {
    puVar2 = (uint *)(DAT_000177b4 + 0x17710);
    FUN_00016d34(DAT_000177b4 + 0x17758,0x100,auStack_308);
    uVar4 = *(undefined4 *)(iVar1 + 0x17714);
    *puVar2 = *puVar2 | 8;
    *(undefined4 *)(iVar1 + 0x17714) = uVar4;
  }
  iVar1 = DAT_000177b0;
  if ((local_350[0] & 0x10) != 0) {
    puVar2 = (uint *)(DAT_000177b0 + 0x176e4);
    FUN_00016d34(DAT_000177b0 + 0x1782c,0x100,auStack_208);
    uVar4 = *(undefined4 *)(iVar1 + 0x176e8);
    *puVar2 = *puVar2 | 0x10;
    *(undefined4 *)(iVar1 + 0x176e8) = uVar4;
  }
  iVar1 = DAT_00017778;
  if ((local_350[0] & 0x400) != 0) {
    puVar2 = (uint *)(DAT_00017778 + 0x17514);
    puVar5 = (undefined4 *)(DAT_00017778 + 0x17518);
    *(undefined4 *)(DAT_00017778 + 0x17544) = local_320;
    *puVar2 = *puVar2 | 0x400;
    *(undefined4 *)(iVar1 + 0x17518) = *puVar5;
  }
  iVar1 = DAT_0001777c;
  if ((local_350[0] & 0x80) != 0) {
    puVar2 = (uint *)(DAT_0001777c + 0x17540);
    puVar5 = (undefined4 *)(DAT_0001777c + 0x17544);
    *(undefined4 *)(DAT_0001777c + 0x1756c) = local_324;
    *puVar2 = *puVar2 | 0x80;
    *(undefined4 *)(iVar1 + 0x17544) = *puVar5;
  }
  iVar1 = DAT_00017780;
  if ((local_350[0] & 0x40) != 0) {
    puVar2 = (uint *)(DAT_00017780 + 0x1756c);
    uVar3 = *puVar2;
    uVar4 = *(undefined4 *)(DAT_00017780 + 0x17570);
    *(undefined4 *)(DAT_00017780 + 0x175ac) = local_310;
    *(undefined4 *)(iVar1 + 0x175b0) = uStack_30c;
    *puVar2 = uVar3 | 0x40;
    *(undefined4 *)(iVar1 + 0x17570) = uVar4;
  }
  if ((local_350[0] & 0x4000) != 0) {
    if (local_28 == 0) {
      *(undefined4 *)(DAT_00017798 + 0x17658) = 0;
    }
    else {
      puVar5 = (undefined4 *)(DAT_00017788 + 0x175b8);
      *(short *)(DAT_00017784 + 0x175b0) = local_48;
      *puVar5 = 1;
    }
  }
  iVar1 = DAT_000177a4;
  if (((local_350[0] & 2) != 0) && (local_336 == '\0')) {
    puVar5 = (undefined4 *)(DAT_000177a4 + 0x176a4);
    memset(puVar5,0,0x330);
    __s = (void *)(DAT_000177a8 + 0x176bc);
    *puVar5 = 2;
    *(undefined4 *)(iVar1 + 0x176a8) = 0;
    memset(__s,0,0xdd00);
    *(undefined4 *)(DAT_000177ac + 0x176cc) = 0;
  }
  *(undefined4 *)(DAT_0001778c + 0x175f0) = 1;
  FUN_00016da4();
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00017790 + 0x17600));
  return;
}



/* Function: FUN_000177c4 @ 000177c4 */

void FUN_000177c4(undefined4 param_1,undefined4 param_2)

{
  int iVar1;
  undefined4 *puVar2;
  uint local_6f0;
  uint uStack_6ec;
  undefined2 local_6d6;
  undefined1 local_6d4;
  undefined1 local_6d2;
  undefined2 local_6d0;
  undefined1 auStack_6a8 [256];
  undefined1 auStack_5a8 [1428];
  
  FUN_000193a8(param_1,param_2,&local_6f0);
  if ((local_6f0 & 2) != 0) {
    pthread_mutex_lock((pthread_mutex_t *)(DAT_000178d4 + 0x17800));
    iVar1 = FUN_000169e8(local_6d6,1);
    if ((local_6f0 & 8) != 0) {
      *(undefined1 *)(iVar1 + 0x2c) = local_6d4;
    }
    if ((local_6f0 & 0x800) != 0) {
      *(undefined2 *)(iVar1 + 0x30) = local_6d0;
    }
    if ((local_6f0 & 0x100) != 0) {
      *(undefined1 *)(iVar1 + 0x2e) = local_6d2;
    }
    if ((local_6f0 & 0x10) != 0) {
      FUN_00016d34(iVar1 + 0x158,0x100,auStack_5a8);
    }
    if ((local_6f0 & 4) != 0) {
      FUN_00016d34(iVar1 + 0x58,0x100,auStack_6a8);
    }
    puVar2 = (undefined4 *)(DAT_000178d8 + 0x178ac);
    *(uint *)(iVar1 + 0x10) = *(uint *)(iVar1 + 0x10) | local_6f0;
    *(uint *)(iVar1 + 0x14) = *(uint *)(iVar1 + 0x14) | uStack_6ec;
    *puVar2 = 1;
    FUN_00016da4();
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_000178dc + 0x178c8));
  }
  return;
}



/* Function: FUN_000178e0 @ 000178e0 */

undefined4 FUN_000178e0(undefined4 *param_1,void *param_2,uint param_3)

{
  short sVar1;
  size_t __n;
  uint uVar2;
  void *__ptr;
  int iVar3;
  uint uVar4;
  uint uVar5;
  bool bVar6;
  undefined1 auStack_128 [260];
  
  if (param_2 != (void *)0x0 && param_1 != (undefined4 *)0x0) {
    iVar3 = 0;
    uVar5 = param_3 - 6;
    if (uVar5 < 0x1ffb) {
      if (3 < uVar5) {
        uVar4 = (uint)CONCAT11(*(undefined1 *)((int)param_2 + 6),*(undefined1 *)((int)param_2 + 7));
        sVar1 = CONCAT11(*(undefined1 *)((int)param_2 + 8),*(undefined1 *)((int)param_2 + 9));
        if ((3 < uVar4) && (uVar4 <= uVar5)) {
          while( true ) {
            if (sVar1 == 0x1e) {
              uVar5 = FUN_000199b8((int)param_2 + iVar3 + 6,uVar4);
              if (uVar5 == 0) {
                uVar5 = (uint)*(ushort *)(DAT_00017b40 + 0x17aec);
              }
              else {
                *(short *)(DAT_00017b30 + 0x179d4) = (short)uVar5;
              }
              __n = FUN_00019860(auStack_128,0x100,uVar5);
              if (__n == 0) {
                return 0;
              }
              uVar5 = (param_3 - uVar4) + __n;
              goto LAB_00017a28;
            }
            iVar3 = iVar3 + uVar4;
            if (uVar5 < iVar3 + 4U) break;
            uVar4 = (uint)CONCAT11(*(undefined1 *)((int)param_2 + iVar3 + 6),
                                   *(undefined1 *)((int)param_2 + iVar3 + 7));
            sVar1 = CONCAT11(*(undefined1 *)((int)param_2 + iVar3 + 8),
                             *(undefined1 *)((int)param_2 + iVar3 + 9));
            if ((uVar4 < 4) || (uVar5 - iVar3 < uVar4)) break;
          }
        }
      }
      __n = FUN_00019860(auStack_128,0x100,*(undefined2 *)(DAT_00017b34 + 0x17a0c));
      if (__n != 0) {
        uVar4 = 0;
        uVar5 = __n + param_3;
        iVar3 = 0;
LAB_00017a28:
        if (uVar5 < 0x2001) {
          uVar2 = param_1[2];
          __ptr = (void *)*param_1;
          bVar6 = uVar2 == param_3;
          if (param_3 <= uVar2) {
            bVar6 = __ptr == param_2;
          }
          if (uVar2 < 0x2001 && bVar6) {
            if (uVar2 < uVar5) {
              __ptr = realloc(__ptr,uVar5 + 0x40);
              if (__ptr == (void *)0x0) {
                return 0;
              }
              *param_1 = __ptr;
              param_1[2] = uVar5 + 0x40;
            }
            if (uVar4 == 0) {
              memcpy((void *)((int)__ptr + param_3),auStack_128,__n);
            }
            else {
              iVar3 = iVar3 + 6;
              memmove((void *)((int)__ptr + iVar3 + __n),(void *)((int)__ptr + uVar4 + iVar3),
                      param_3 - (uVar4 + iVar3));
              memcpy((void *)((int)__ptr + iVar3),auStack_128,__n);
            }
            iVar3 = DAT_00017b38;
            *(char *)((int)__ptr + 3) = (char)uVar5;
            *(char *)((int)__ptr + 2) = (char)(uVar5 >> 8);
            param_1[1] = uVar5;
            pthread_mutex_lock((pthread_mutex_t *)(iVar3 + 0x17ab8));
            *(undefined4 *)(DAT_00017b3c + 0x17ad4) = 1;
            pthread_mutex_unlock((pthread_mutex_t *)(iVar3 + 0x17ab8));
            return 1;
          }
        }
      }
    }
  }
  return 0;
}



/* Function: FUN_00017b44 @ 00017b44 */

undefined4 FUN_00017b44(int param_1,uint *param_2,byte *param_3)

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
      iVar3 = DAT_00017c08 + 0x17b70;
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



/* Function: FUN_00017c0c @ 00017c0c */

undefined4 FUN_00017c0c(void)

{
  uint uVar1;
  int *piVar2;
  char *__format;
  int iVar3;
  pthread_mutex_t *__mutex;
  int *piVar4;
  char *__new;
  int *piVar5;
  void *__src;
  int iVar6;
  char *__old;
  uint local_8ec;
  timespec local_8e8;
  undefined4 local_8e0 [2];
  undefined4 local_8d8;
  undefined4 uStack_8d4;
  undefined4 local_8d0;
  undefined4 local_8cc;
  undefined4 local_8c8;
  undefined4 local_8c4;
  undefined4 local_8c0;
  undefined4 local_8bc;
  undefined4 local_8b8;
  undefined4 local_8b4;
  undefined4 local_8b0;
  undefined1 auStack_8ac [192];
  undefined1 auStack_7ec [192];
  undefined1 auStack_72c [196];
  char acStack_668 [1604];
  
  __mutex = (pthread_mutex_t *)(DAT_00017eb0 + 0x17c30);
  piVar5 = (int *)(DAT_00017eb4 + 0x17c38);
  piVar4 = (int *)(DAT_00017eb8 + 0x17c40);
  __format = (char *)(DAT_00017ebc + 0x17c44);
  __src = (void *)(DAT_00017ec0 + 0x17c50);
  iVar3 = DAT_00017ec4 + 0x17c58;
  while( true ) {
    FUN_00016ae0();
    clock_gettime(0,&local_8e8);
    local_8e8.tv_sec = local_8e8.tv_sec + 1;
    pthread_mutex_lock(__mutex);
    if (*piVar5 != 0) break;
    iVar6 = *piVar4;
    memcpy(local_8e0,__src,0x278);
    *piVar4 = 0;
    if (iVar6 == 0) {
      pthread_cond_timedwait((pthread_cond_t *)(DAT_00017ed0 + 0x17e7c),__mutex,&local_8e8);
      pthread_mutex_unlock(__mutex);
    }
    else {
      pthread_mutex_unlock(__mutex);
      uVar1 = snprintf(acStack_668,0x640,__format,local_8e0[0],local_8d8,uStack_8d4,local_8d0,
                       local_8cc,local_8c8,local_8c4,local_8c0,local_8bc,local_8b8,local_8b4,
                       local_8b0);
      if (uVar1 < 0x640) {
        local_8ec = uVar1;
        iVar6 = FUN_00017b44(acStack_668,&local_8ec,auStack_8ac);
        if ((iVar6 != 0) && (local_8ec + 1 < 0x640)) {
          acStack_668[local_8ec] = '\t';
          local_8ec = local_8ec + 1;
          iVar6 = FUN_00017b44(acStack_668,&local_8ec,auStack_7ec);
          if ((iVar6 != 0) && (local_8ec + 1 < 0x640)) {
            acStack_668[local_8ec] = '\t';
            local_8ec = local_8ec + 1;
            iVar6 = FUN_00017b44(acStack_668,&local_8ec,auStack_72c);
            if ((iVar6 != 0) && (uVar1 = local_8ec + 1, uVar1 < 0x640)) {
              acStack_668[local_8ec] = '\n';
              local_8ec = uVar1;
              iVar6 = FUN_00016b30(iVar3,acStack_668,uVar1);
              if (iVar6 == 0) {
                unlink((char *)(DAT_00017ed4 + 0x17eac));
              }
              else {
                __old = (char *)(DAT_00017ec8 + 0x17e1c);
                __new = (char *)(DAT_00017ecc + 0x17e20);
                iVar6 = rename(__old,__new);
                if (iVar6 != 0) {
                  piVar2 = (int *)__get_errno_ptr();
                  iVar6 = *piVar2;
                  unlink(__old);
                  if (iVar6 == 0x59 || iVar6 == 0x12) {
                    FUN_00016b30(__new,acStack_668,local_8ec);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  pthread_mutex_unlock(__mutex);
  return 0;
}



/* Function: FUN_00017ed8 @ 00017ed8 */

undefined4 FUN_00017ed8(void)

{
  char cVar1;
  __pid_t _Var2;
  int iVar3;
  pthread_mutex_t *__mutex;
  code *pcVar4;
  int *piVar5;
  pthread_mutex_t *__mutex_00;
  pthread_cond_t *__cond;
  int *piVar6;
  code *pcVar7;
  int *piVar8;
  int iVar9;
  pthread_mutex_t *__mutex_01;
  int *piVar10;
  undefined1 local_228;
  undefined1 local_227;
  undefined1 local_226;
  undefined1 local_225;
  undefined1 local_224;
  undefined1 local_223;
  undefined1 local_222;
  undefined1 local_221;
  undefined1 local_220;
  undefined1 local_21f;
  undefined1 local_21e;
  undefined1 local_21d;
  undefined1 local_21c;
  undefined1 local_21b;
  undefined1 local_21a;
  undefined1 local_219;
  undefined1 local_218;
  undefined1 local_217;
  undefined1 local_216;
  undefined1 local_215;
  undefined1 local_214;
  undefined1 local_213;
  undefined1 local_212;
  undefined1 local_211;
  
  __mutex_01 = (pthread_mutex_t *)(DAT_00018188 + 0x17ef4);
  piVar10 = (int *)(DAT_0001818c + 0x17efc);
  __cond = (pthread_cond_t *)(DAT_00018190 + 0x17f00);
  __mutex_00 = (pthread_mutex_t *)(DAT_00018194 + 0x17f04);
  while (pthread_mutex_lock(__mutex_01), *piVar10 == 0) {
    if (*(int *)(DAT_00018198 + 0x17f20) == 0) {
      piVar6 = (int *)(DAT_0001819c + 0x17f38);
      piVar8 = (int *)(DAT_000181a0 + 0x17f3c);
      do {
        pthread_cond_wait(__cond,__mutex_00);
        if (*piVar6 != 0) goto LAB_00017f60;
      } while (*piVar8 == 0);
    }
    piVar6 = (int *)(DAT_000181a8 + 0x17f8c);
    iVar3 = *piVar6;
    *(undefined4 *)(DAT_000181ac + 0x17f90) = 0;
    if (((((iVar3 == 0) || (_Var2 = getpid(), _Var2 != *(int *)(DAT_000181b0 + 0x17fac))) ||
         (piVar8 = (int *)(DAT_000181b8 + 0x17fd0), *piVar8 == 0)) ||
        ((pcVar4 = *(code **)(DAT_000181bc + 0x17fe4), pcVar4 == (code *)0x0 ||
         (*(code **)(DAT_000181c0 + 0x17ff8) == (code *)0x0)))) ||
       (pcVar7 = *(code **)(DAT_000181c4 + 0x1800c), pcVar7 == (code *)0x0)) {
      pthread_mutex_unlock((pthread_mutex_t *)(DAT_000181b4 + 0x17fc0));
    }
    else {
      piVar5 = (int *)(DAT_000181cc + 0x18028);
      iVar9 = *piVar6;
      __mutex = (pthread_mutex_t *)(DAT_000181d0 + 0x18034);
      iVar3 = *piVar5;
      cVar1 = *(char *)(DAT_000181c8 + 0x18020);
      (**(code **)(DAT_000181c0 + 0x17ff8))(iVar9);
      pthread_mutex_unlock(__mutex);
      if (iVar9 != 0) {
        local_222 = 0;
        local_220 = 0;
        local_21f = 0;
        local_21c = 0;
        local_21d = (undefined1)*(undefined2 *)(DAT_000181d4 + 0x18070);
        local_21e = (undefined1)((ushort)*(undefined2 *)(DAT_000181d4 + 0x18070) >> 8);
        local_228 = 0x40;
        local_227 = 0x40;
        local_221 = 6;
        local_21b = 4;
        local_217 = 4;
        local_213 = 4;
        local_215 = 2;
        local_211 = 3;
        local_21a = 0;
        local_224 = 0x52;
        local_218 = 0;
        local_216 = 0;
        local_214 = 0;
        local_212 = 0;
        local_226 = 0;
        local_223 = 0;
        local_219 = 1;
        local_225 = 0x18;
        pthread_mutex_lock(__mutex);
        if (((*(int *)(DAT_000181d8 + 0x18108) == 0) && (*piVar8 != 0)) &&
           ((iVar3 == *piVar5 && (*(char *)(DAT_000181e0 + 0x18158) == cVar1)))) {
          pthread_mutex_unlock((pthread_mutex_t *)(DAT_000181e4 + 0x1816c));
          (*pcVar4)(iVar9,cVar1,&local_228,0x18);
        }
        else {
          pthread_mutex_unlock((pthread_mutex_t *)(DAT_000181dc + 0x1813c));
        }
        (*pcVar7)(iVar9);
      }
    }
  }
LAB_00017f60:
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_000181a4 + 0x17f6c));
  return 0;
}



/* Function: FUN_000181e8 @ 000181e8 */

bool FUN_000181e8(void)

{
  int iVar1;
  pthread_mutex_t *__mutex;
  int iVar2;
  
  __mutex = (pthread_mutex_t *)(DAT_0001831c + 0x181f8);
  pthread_mutex_lock(__mutex);
  iVar1 = DAT_0001834c;
  if (*(int *)(DAT_00018320 + 0x18208) == 0) {
    if (*(int *)(DAT_00018324 + 0x1821c) == 0) {
      memset((void *)(DAT_0001834c + 0x182dc),0,0x278);
      iVar2 = DAT_00018350;
      *(undefined4 *)(iVar1 + 0x182f8) = 0xffffffff;
      *(undefined4 *)(iVar1 + 0x182fc) = 0xffffffff;
      *(undefined4 *)(iVar1 + 0x18300) = 0xffffffff;
      *(undefined4 *)(iVar1 + 0x18304) = 0xffffffff;
      *(undefined4 *)(iVar1 + 0x18308) = 0xffffffff;
      *(undefined4 *)(iVar1 + 0x1830c) = 0xffffffff;
      *(undefined4 *)(iVar2 + 0x182f4) = 0;
    }
    iVar1 = pthread_create((pthread_t *)(DAT_00018328 + 0x18238),(pthread_attr_t *)0x0,
                           (__start_routine *)(DAT_0001832c + 0x1823c),(void *)0x0);
    if (iVar1 == 0) {
      *(undefined4 *)(DAT_00018330 + 0x18250) = 1;
    }
    if (*(int *)(DAT_00018334 + 0x1825c) != 0) {
      iVar2 = 0;
      goto LAB_00018268;
    }
  }
  else {
    iVar1 = 0;
    if (*(int *)(DAT_0001833c + 0x18290) != 0) {
      pthread_mutex_unlock(__mutex);
      return true;
    }
  }
  iVar2 = pthread_create((pthread_t *)(DAT_00018340 + 0x182ac),(pthread_attr_t *)0x0,
                         (__start_routine *)(DAT_00018344 + 0x182b0),(void *)0x0);
  if (iVar2 == 0) {
    *(undefined4 *)(DAT_00018348 + 0x182c4) = 1;
  }
LAB_00018268:
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_00018338 + 0x18274));
  return iVar1 == 0 && iVar2 == 0;
}



/* Function: CinemoCreateIAP @ 000184ec */

int CinemoCreateIAP(int *param_1)

{
  int iVar1;
  int iVar2;
  int iVar3;
  __pid_t _Var4;
  int *piVar5;
  int *piVar6;
  int iVar7;
  int iVar8;
  
  FUN_00016bc4();
  if (*(code **)(DAT_000185e8 + 0x18504) == (code *)0x0) {
    return -1;
  }
  iVar3 = (**(code **)(DAT_000185e8 + 0x18504))(param_1);
  iVar1 = DAT_000185f4;
  if (iVar3 == 0 && param_1 != (int *)0x0) {
    piVar5 = (int *)(DAT_000185ec + 0x1853c);
    if (((*piVar5 != 0) && (piVar6 = (int *)(DAT_000185f0 + 0x18550), *piVar6 != 0)) &&
       (iVar3 = *param_1, iVar3 != 0)) {
      FUN_000181e8();
      (*(code *)*piVar5)(iVar3);
      iVar2 = DAT_000185f8;
      pthread_mutex_lock((pthread_mutex_t *)(iVar1 + 0x1857c));
      iVar7 = *(__pid_t *)(iVar2 + 0x18594);
      iVar8 = *(int *)(DAT_000185fc + 0x18598);
      *(int *)(DAT_000185fc + 0x18598) = iVar3;
      _Var4 = getpid();
      *(__pid_t *)(iVar2 + 0x18594) = _Var4;
      pthread_mutex_unlock((pthread_mutex_t *)(iVar1 + 0x1857c));
      if ((iVar8 != 0) && (_Var4 = getpid(), _Var4 == iVar7)) {
        (*(code *)*piVar6)(iVar8);
        return 0;
      }
    }
    return 0;
  }
  return iVar3;
}



/* Function: Decode @ 00018600 */

/* NmeIAP2Message::Decode(unsigned char const*, int) */

int NmeIAP2Message::Decode(uchar *param_1,int param_2)

{
  short sVar1;
  int iVar2;
  int iVar3;
  undefined4 *puVar4;
  uint in_r2;
  undefined4 *puVar5;
  undefined4 *puVar6;
  pthread_mutex_t *ppVar7;
  uint uVar8;
  undefined4 *puVar9;
  undefined4 *puVar10;
  undefined4 *puVar11;
  
  FUN_00016bc4();
  if (*(code **)(DAT_00018844 + 0x18620) == (code *)0x0) {
    return -1;
  }
  iVar3 = (**(code **)(DAT_00018844 + 0x18620))(param_1,param_2);
  if (iVar3 != 0 || param_2 == 0) {
    return iVar3;
  }
  if ((int)in_r2 < 6) {
    return 0;
  }
  if (*(char *)param_2 != '@') {
    return 0;
  }
  if (*(char *)(param_2 + 1) != '@') {
    return 0;
  }
  uVar8 = (uint)CONCAT11(*(undefined1 *)(param_2 + 2),*(undefined1 *)(param_2 + 3));
  if (uVar8 < 6) {
    return 0;
  }
  if (in_r2 < uVar8) {
    return 0;
  }
  sVar1 = CONCAT11(*(undefined1 *)(param_2 + 4),*(undefined1 *)(param_2 + 5));
  FUN_000181e8();
  if (sVar1 != 0x1d00) {
    if (sVar1 == 0x1d02) {
      ppVar7 = (pthread_mutex_t *)(DAT_0001887c + 0x187e0);
      pthread_mutex_lock(ppVar7);
      *(undefined4 *)(DAT_00018880 + 0x187f8) = 1;
      pthread_mutex_unlock(ppVar7);
      return 0;
    }
    if (sVar1 == -0x55fb) {
      ppVar7 = (pthread_mutex_t *)(DAT_00018884 + 0x18810);
      pthread_mutex_lock(ppVar7);
      puVar6 = (undefined4 *)(DAT_00018888 + 0x18820);
    }
    else {
      if (sVar1 == 0x5201) {
        FUN_0001742c(param_2,uVar8);
        return 0;
      }
      if (sVar1 == 0x5202) {
        FUN_000177c4(param_2,uVar8);
        return 0;
      }
      if (sVar1 != 0x5204) {
        return 0;
      }
      ppVar7 = (pthread_mutex_t *)(DAT_00018848 + 0x186f8);
      pthread_mutex_lock(ppVar7);
      puVar6 = (undefined4 *)(DAT_0001884c + 0x18708);
    }
    *puVar6 = 1;
    pthread_mutex_unlock(ppVar7);
    return 0;
  }
  ppVar7 = (pthread_mutex_t *)(DAT_00018850 + 0x18738);
  puVar10 = (undefined4 *)(DAT_00018854 + 0x18740);
  puVar9 = (undefined4 *)(DAT_00018858 + 0x18748);
  pthread_mutex_lock(ppVar7);
  memset((void *)(DAT_0001885c + 0x1875c),0,0x330);
  memset((void *)(DAT_00018860 + 0x18770),0,0xdd00);
  iVar2 = DAT_00018878;
  iVar3 = DAT_00018870;
  puVar11 = (undefined4 *)(DAT_00018868 + 0x1878c);
  puVar6 = (undefined4 *)(DAT_0001886c + 0x18794);
  puVar4 = (undefined4 *)(DAT_00018870 + 0x1879c);
  puVar5 = (undefined4 *)(DAT_00018874 + 0x187a0);
  *(undefined4 *)(DAT_00018864 + 0x18784) = 0;
  *puVar11 = 0;
  *puVar6 = 0;
  *puVar4 = 0;
  *(undefined4 *)(iVar3 + 0x187a0) = 0;
  *puVar5 = 0;
  *(undefined4 *)(iVar2 + 0x187a8) = 0;
  *puVar10 = 0;
  *puVar9 = 0;
  FUN_00016da4();
  pthread_mutex_unlock(ppVar7);
  return 0;
}



/* Function: Encode @ 0001888c */

/* NmeIAP2Message::Encode(NmeArray<unsigned char>&) const */

int __thiscall NmeIAP2Message::Encode(NmeIAP2Message *this,NmeArray *param_1)

{
  int iVar1;
  char *pcVar2;
  
  FUN_00016bc4();
  if (*(code **)(DAT_00018948 + 0x188a8) == (code *)0x0) {
    return -1;
  }
  iVar1 = (**(code **)(DAT_00018948 + 0x188a8))(this,param_1);
  if (iVar1 != 0 || param_1 == (NmeArray *)0x0) {
    return iVar1;
  }
  pcVar2 = *(char **)param_1;
  if (pcVar2 == (char *)0x0 || *(uint *)(param_1 + 4) < 6) {
    return 0;
  }
  if (((*pcVar2 == '@') && (pcVar2[1] == '@')) && (CONCAT11(pcVar2[4],pcVar2[5]) == 0x1d01)) {
    FUN_000178e0(param_1);
    return 0;
  }
  return 0;
}



/* Function: Send @ 0001894c */

/* NmeTransport::Send(unsigned char const*, unsigned int, unsigned int*) */

int __thiscall NmeTransport::Send(NmeTransport *this,uchar *param_1,uint param_2,uint *param_3)

{
  uchar uVar1;
  uchar uVar2;
  int iVar3;
  __pid_t _Var4;
  pthread_mutex_t *__mutex;
  longlong *plVar5;
  int *piVar6;
  undefined4 *puVar7;
  uchar *puVar8;
  int iVar9;
  uint uVar10;
  uint uVar11;
  longlong lVar12;
  uint *puVar13;
  
  puVar13 = param_3;
  FUN_00016bc4();
  if (*(code **)(DAT_00018c1c + 0x18970) == (code *)0x0) {
    return -1;
  }
  iVar3 = (**(code **)(DAT_00018c1c + 0x18970))(this,param_1,param_2,param_3,puVar13);
  if (iVar3 == 0) {
    if ((param_1 != (uchar *)0x0) && (5 < param_2)) {
      puVar8 = param_1;
      uVar10 = 0;
      do {
        uVar11 = uVar10 + 7;
        if ((*puVar8 == '@') && (puVar8[1] == '@')) {
          if ((5 < CONCAT11(puVar8[2],puVar8[3])) &&
             ((uint)CONCAT11(puVar8[2],puVar8[3]) <= (param_2 + 1) - (uVar10 + 1))) {
            uVar1 = param_1[uVar10 + 4];
            uVar2 = param_1[uVar10 + 5];
            FUN_000181e8();
            if (((8 < uVar10) && (param_1[uVar10 - 9] == 0xff)) && (param_1[uVar10 - 8] == 'Z')) {
              pthread_mutex_lock((pthread_mutex_t *)(DAT_00018c20 + 0x18a58));
              piVar6 = (int *)(DAT_00018c24 + 0x18a68);
              iVar9 = *piVar6;
              *(uchar *)(DAT_00018c28 + 0x18a70) = param_1[uVar10 - 2];
              iVar3 = iVar9 + 1;
              if (iVar3 == 0) {
                *piVar6 = iVar9 + 2;
              }
              else {
                *piVar6 = iVar3;
              }
              uVar10 = 0;
              if (*(int *)(DAT_00018c2c + 0x18a90) != 0) {
                _Var4 = getpid();
                uVar10 = (uint)(_Var4 == *(int *)(DAT_00018c30 + 0x18aa8));
              }
              __mutex = (pthread_mutex_t *)(DAT_00018c38 + 0x18ac8);
              *(uint *)(DAT_00018c34 + 0x18ac4) = uVar10;
              pthread_mutex_unlock(__mutex);
            }
            if (CONCAT11(uVar1,uVar2) != -5) {
              return 0;
            }
            lVar12 = FUN_00016ae0();
            pthread_mutex_lock((pthread_mutex_t *)(DAT_00018c3c + 0x18aec));
            iVar3 = DAT_00018c70;
            if (*(uint *)(DAT_00018c40 + 0x18afc) < 5) {
              uVar10 = 2000;
            }
            else {
              uVar10 = 30000;
            }
            if (((((*(int *)(DAT_00018c44 + 0x18b00) == 0) &&
                  (*(int *)(DAT_00018c48 + 0x18b24) == 0)) &&
                 ((*(int *)(DAT_00018c4c + 0x18b38) != 0 &&
                  ((*(int *)(DAT_00018c50 + 0x18b4c) != 0 && (*(int *)(DAT_00018c54 + 0x18b60) != 0)
                   ))))) && (*(int *)(DAT_00018c58 + 0x18b74) != 0)) &&
               (*(int *)(DAT_00018c5c + 0x18b88) != 0)) {
              uVar11 = *(uint *)(DAT_00018c60 + 0x18b9c);
              if ((lVar12 == 0 || uVar11 == 0 && *(int *)(DAT_00018c60 + 0x18ba0) == 0) ||
                 ((int)((ulonglong)lVar12 >> 0x20) !=
                  *(int *)(DAT_00018c60 + 0x18ba0) + (uint)((uint)lVar12 < uVar11) ||
                  uVar10 <= (uint)lVar12 - uVar11)) {
                plVar5 = (longlong *)(DAT_00018c68 + 0x18bf0);
                puVar7 = (undefined4 *)(DAT_00018c6c + 0x18bf4);
                *(uint *)(DAT_00018c64 + 0x18be8) = *(uint *)(DAT_00018c40 + 0x18afc) + 1;
                *plVar5 = lVar12;
                *puVar7 = 1;
                pthread_cond_signal((pthread_cond_t *)(iVar3 + 0x18bfc));
              }
            }
            pthread_mutex_unlock((pthread_mutex_t *)(DAT_00018c74 + 0x18c10));
            return 0;
          }
        }
        puVar8 = puVar8 + 1;
        uVar10 = uVar10 + 1;
      } while (uVar11 <= param_2);
    }
    return 0;
  }
  return iVar3;
}



/* Function: FUN_00018c78 @ 00018c78 */

void FUN_00018c78(char *param_1,int param_2)

{
  char *pcVar1;
  int iVar2;
  char cVar3;
  uint __n;
  uint uVar4;
  int iVar5;
  undefined *local_124;
  char acStack_120 [256];
  
  cVar3 = *param_1;
  local_124 = &DAT_00202f20;
  if (cVar3 == '\0') {
    uVar4 = 0;
    __n = 0;
  }
  else {
    uVar4 = 0;
    iVar5 = 0;
    do {
      iVar2 = iVar5 + 1;
      if (cVar3 == '\r') {
        cVar3 = param_1[iVar2];
        if (cVar3 == '\n') {
          iVar2 = iVar5 + 2;
          pcVar1 = acStack_120 + uVar4;
          uVar4 = uVar4 + 3;
          memcpy(pcVar1,&local_124,3);
          cVar3 = param_1[iVar2];
        }
        else {
LAB_00018d04:
          iVar2 = iVar5 + 1;
          pcVar1 = acStack_120 + uVar4;
          uVar4 = uVar4 + 3;
          memcpy(pcVar1,&local_124,3);
        }
      }
      else {
        if (cVar3 == '\n') {
          cVar3 = param_1[iVar5 + 1];
          goto LAB_00018d04;
        }
        acStack_120[uVar4] = cVar3;
        uVar4 = uVar4 + 1;
        cVar3 = param_1[iVar2];
      }
      iVar5 = iVar2;
    } while (uVar4 < 0xfc && cVar3 != '\0');
    __n = uVar4;
    if (param_2 - 1U <= uVar4) {
      __n = param_2 - 1U;
    }
  }
  acStack_120[uVar4] = '\0';
  memcpy(param_1,acStack_120,__n);
  param_1[__n] = '\0';
  return;
}



/* Function: FUN_00018d94 @ 00018d94 */

void FUN_00018d94(int param_1,uint param_2,uint *param_3)

{
  undefined1 uVar1;
  undefined1 uVar2;
  byte bVar3;
  byte bVar4;
  byte bVar5;
  byte bVar6;
  byte bVar7;
  byte bVar8;
  byte bVar9;
  byte bVar10;
  uint *puVar11;
  void *__src;
  undefined1 *puVar12;
  int iVar13;
  size_t __n;
  uint uVar14;
  uint uVar15;
  short sVar16;
  uint uVar17;
  undefined2 *puVar18;
  
  if (param_3 == (uint *)0x0) {
    return;
  }
  memset(param_3,0,0x330);
  if (param_1 != 0 && 5 < param_2) {
    uVar15 = 6;
    while (uVar17 = uVar15 + 4, uVar17 <= param_2) {
      iVar13 = uVar15 + 2;
      __src = (void *)(param_1 + uVar17);
      uVar14 = (uint)CONCAT11(*(undefined1 *)(param_1 + uVar15),
                              *(undefined1 *)(param_1 + uVar15 + 1));
      __n = uVar14 - 4;
      if (uVar14 < 4) {
        return;
      }
      uVar15 = uVar15 + uVar14;
      if (param_2 < uVar15) {
        return;
      }
      switch(CONCAT11(*(undefined1 *)(param_1 + iVar13),*(undefined1 *)(param_1 + iVar13 + 1))) {
      case 0:
        *(undefined2 *)(param_3 + 6) = 0;
        if (1 < (int)__n) {
          puVar18 = (undefined2 *)((int)param_3 + 6);
          sVar16 = 1;
          puVar12 = (undefined1 *)((int)__src + 1);
          do {
            *(short *)(param_3 + 6) = sVar16;
            sVar16 = sVar16 + 1;
            puVar18 = puVar18 + 1;
            *puVar18 = CONCAT11(puVar12[-1],*puVar12);
            if (puVar18 == (undefined2 *)((int)param_3 + (uVar14 - 6 & 0xfffffffe) + 8)) break;
            puVar12 = puVar12 + 2;
          } while (puVar18 != (undefined2 *)((int)param_3 + 0x16));
        }
        *param_3 = *param_3 | 1;
        param_3[1] = param_3[1];
        break;
      case 1:
        if (0 < (int)__n) {
          *(undefined1 *)((int)param_3 + 0x1a) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 2;
          param_3[1] = param_3[1];
        }
        break;
      case 2:
        if (0 < (int)__n) {
          *(undefined1 *)((int)param_3 + 0x1b) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 4;
          param_3[1] = param_3[1];
        }
        break;
      case 3:
        if (-1 < (int)__n) {
          puVar11 = param_3 + 0x12;
          if (0xfe < (int)__n) {
            __n = 0xff;
          }
          if (__n != 0) {
            puVar11 = memcpy(puVar11,__src,__n);
          }
          *(undefined1 *)((int)param_3 + __n + 0x48) = 0;
          FUN_00018c78(puVar11,0x100);
          *param_3 = *param_3 | 8;
          param_3[1] = param_3[1];
        }
        break;
      case 4:
        if (-1 < (int)__n) {
          puVar11 = param_3 + 0x52;
          if (0xfe < (int)__n) {
            __n = 0xff;
          }
          if (__n != 0) {
            puVar11 = memcpy(puVar11,__src,__n);
          }
          *(undefined1 *)((int)param_3 + __n + 0x148) = 0;
          FUN_00018c78(puVar11,0x100);
          *param_3 = *param_3 | 0x10;
          param_3[1] = param_3[1];
        }
        break;
      case 5:
        if (7 < (int)__n) {
          bVar3 = *(byte *)((int)__src + 1);
          bVar4 = *(byte *)(param_1 + uVar17);
          bVar5 = *(byte *)((int)__src + 5);
          bVar6 = *(byte *)((int)__src + 4);
          bVar7 = *(byte *)((int)__src + 3);
          bVar8 = *(byte *)((int)__src + 2);
          bVar9 = *(byte *)((int)__src + 7);
          bVar10 = *(byte *)((int)__src + 6);
          *param_3 = *param_3 | 0x20;
          param_3[1] = param_3[1];
          param_3[0xe] = (uint)bVar5 << 0x10 | (uint)bVar6 << 0x18 | (uint)bVar9 | (uint)bVar10 << 8
          ;
          param_3[0xf] = (uint)bVar3 << 0x10 | (uint)bVar4 << 0x18 | (uint)bVar7 | (uint)bVar8 << 8;
        }
        break;
      case 6:
        if (7 < (int)__n) {
          bVar3 = *(byte *)((int)__src + 1);
          bVar4 = *(byte *)(param_1 + uVar17);
          bVar5 = *(byte *)((int)__src + 5);
          bVar6 = *(byte *)((int)__src + 4);
          bVar7 = *(byte *)((int)__src + 3);
          bVar8 = *(byte *)((int)__src + 2);
          bVar9 = *(byte *)((int)__src + 7);
          bVar10 = *(byte *)((int)__src + 6);
          *param_3 = *param_3 | 0x40;
          param_3[1] = param_3[1];
          param_3[0x10] =
               (uint)bVar5 << 0x10 | (uint)bVar6 << 0x18 | (uint)bVar9 | (uint)bVar10 << 8;
          param_3[0x11] = (uint)bVar3 << 0x10 | (uint)bVar4 << 0x18 | (uint)bVar7 | (uint)bVar8 << 8
          ;
        }
        break;
      case 7:
        if (3 < (int)__n) {
          param_3[0xb] = (uint)*(byte *)((int)__src + 1) << 0x10 |
                         (uint)*(byte *)(param_1 + uVar17) << 0x18 | (uint)*(byte *)((int)__src + 3)
                         | (uint)*(byte *)((int)__src + 2) << 8;
          *param_3 = *param_3 | 0x80;
          param_3[1] = param_3[1];
        }
        break;
      case 8:
        if (-1 < (int)__n) {
          if (0x3e < (int)__n) {
            __n = 0x3f;
          }
          if (__n != 0) {
            memcpy(param_3 + 0x92,__src,__n);
          }
          uVar17 = *param_3;
          uVar14 = param_3[1];
          *(undefined1 *)((int)param_3 + __n + 0x248) = 0;
          *param_3 = uVar17 | 0x100;
          param_3[1] = uVar14;
        }
        break;
      case 9:
        if (0 < (int)__n) {
          *(undefined1 *)(param_3 + 7) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 0x200;
          param_3[1] = param_3[1];
        }
        break;
      case 10:
        if (3 < (int)__n) {
          param_3[0xc] = (uint)*(byte *)((int)__src + 1) << 0x10 |
                         (uint)*(byte *)(param_1 + uVar17) << 0x18 | (uint)*(byte *)((int)__src + 3)
                         | (uint)*(byte *)((int)__src + 2) << 8;
          *param_3 = *param_3 | 0x400;
          param_3[1] = param_3[1];
        }
        break;
      case 0xb:
        if (-1 < (int)__n) {
          if (0x3e < (int)__n) {
            __n = 0x3f;
          }
          if (__n != 0) {
            memcpy(param_3 + 0xa2,__src,__n);
          }
          uVar17 = *param_3;
          uVar14 = param_3[1];
          *(undefined1 *)((int)param_3 + __n + 0x288) = 0;
          *param_3 = uVar17 | 0x800;
          param_3[1] = uVar14;
        }
        break;
      case 0xc:
        if (0 < (int)__n) {
          *(undefined1 *)((int)param_3 + 0x1d) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 0x1000;
          param_3[1] = param_3[1];
        }
        break;
      case 0xd:
        *(undefined2 *)(param_3 + 0xca) = 0;
        if (1 < (int)__n) {
          puVar18 = (undefined2 *)((int)param_3 + 0x306);
          sVar16 = 1;
          puVar12 = (undefined1 *)((int)__src + 1);
          do {
            *(short *)(param_3 + 0xca) = sVar16;
            sVar16 = sVar16 + 1;
            puVar18 = puVar18 + 1;
            *puVar18 = CONCAT11(puVar12[-1],*puVar12);
            if (puVar18 == (undefined2 *)((int)param_3 + (uVar14 - 6 & 0xfffffffe) + 0x308)) break;
            puVar12 = puVar12 + 2;
          } while (puVar18 != (undefined2 *)((int)param_3 + 0x326));
        }
        *param_3 = *param_3 | 0x4000;
        param_3[1] = param_3[1];
        break;
      case 0xe:
        if (1 < (int)__n) {
          uVar1 = *(undefined1 *)(param_1 + uVar17);
          uVar2 = *(undefined1 *)((int)__src + 1);
          *param_3 = *param_3 | 0x2000;
          param_3[1] = param_3[1];
          *(ushort *)((int)param_3 + 0x22) = CONCAT11(uVar1,uVar2);
        }
        break;
      case 0xf:
        if (0 < (int)__n) {
          *(undefined1 *)((int)param_3 + 0x1e) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 0x8000;
          param_3[1] = param_3[1];
        }
        break;
      case 0x10:
        if (1 < (int)__n) {
          uVar1 = *(undefined1 *)(param_1 + uVar17);
          uVar2 = *(undefined1 *)((int)__src + 1);
          *param_3 = *param_3 | 0x10000;
          param_3[1] = param_3[1];
          *(ushort *)(param_3 + 9) = CONCAT11(uVar1,uVar2);
        }
        break;
      case 0x11:
        if (1 < (int)__n) {
          uVar1 = *(undefined1 *)(param_1 + uVar17);
          uVar2 = *(undefined1 *)((int)__src + 1);
          *param_3 = *param_3 | 0x20000;
          param_3[1] = param_3[1];
          *(ushort *)(param_3 + 10) = CONCAT11(uVar1,uVar2);
        }
        break;
      case 0x12:
        if (0 < (int)__n) {
          *(undefined1 *)((int)param_3 + 0x1f) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 0x40000;
          param_3[1] = param_3[1];
        }
        break;
      case 0x13:
        if (-1 < (int)__n) {
          puVar11 = param_3 + 0xb2;
          if (0x3e < (int)__n) {
            __n = 0x3f;
          }
          if (__n != 0) {
            puVar11 = memcpy(puVar11,__src,__n);
          }
          *(undefined1 *)((int)param_3 + __n + 0x2c8) = 0;
          FUN_00018c78(puVar11,0x40);
          *param_3 = *param_3 | 0x80000;
          param_3[1] = param_3[1];
        }
        break;
      case 0x14:
        if (0 < (int)__n) {
          *(undefined1 *)(param_3 + 8) = *(undefined1 *)(param_1 + uVar17);
          *param_3 = *param_3 | 0x100000;
          param_3[1] = param_3[1];
        }
      }
    }
  }
  return;
}



/* Function: FUN_000193a8 @ 000193a8 */

void FUN_000193a8(int param_1,uint param_2,uint *param_3)

{
  byte bVar1;
  undefined1 uVar2;
  undefined1 uVar3;
  bool bVar4;
  bool bVar5;
  uint uVar6;
  undefined1 *puVar7;
  byte *pbVar8;
  uint uVar9;
  int iVar10;
  uint __n;
  undefined2 *puVar11;
  short sVar12;
  uint uVar13;
  uint uVar14;
  uint *puVar15;
  void *__src;
  uint *puVar16;
  uint uVar17;
  bool bVar18;
  bool bVar19;
  
  if (param_3 == (uint *)0x0) {
    return;
  }
  memset(param_3,0,0x6d8);
  bVar5 = param_1 == 0 || param_2 < 6;
  *(undefined2 *)(param_3 + 0x173) = 0xffff;
  if (param_1 == 0 || param_2 < 6) {
    return;
  }
  uVar14 = 6;
  puVar15 = param_3 + 0x174;
  while (uVar17 = uVar14 + 4, uVar17 <= param_2) {
    iVar10 = uVar14 + 2;
    __src = (void *)(param_1 + uVar17);
    bVar1 = *(byte *)(param_1 + iVar10);
    uVar6 = (uint)bVar1;
    uVar13 = (uint)CONCAT11(*(undefined1 *)(param_1 + uVar14),*(undefined1 *)(param_1 + uVar14 + 1))
    ;
    __n = uVar13 - 4;
    if ((uVar13 < 4) || (uVar14 = uVar14 + uVar13, param_2 < uVar14)) break;
    switch(CONCAT11(bVar1,*(undefined1 *)(param_1 + iVar10 + 1))) {
    case 0:
      *(ushort *)(param_3 + 6) = (ushort)bVar5;
      if (1 < (int)__n) {
        puVar11 = (undefined2 *)((int)param_3 + 6);
        sVar12 = 1;
        puVar7 = (undefined1 *)((int)__src + 1);
        do {
          *(short *)(param_3 + 6) = sVar12;
          sVar12 = sVar12 + 1;
          puVar11 = puVar11 + 1;
          *puVar11 = CONCAT11(puVar7[-1],*puVar7);
          if (puVar11 == (undefined2 *)((int)param_3 + (uVar13 - 6 & 0xfffffffe) + 8)) break;
          puVar7 = puVar7 + 2;
        } while (puVar11 != (undefined2 *)((int)param_3 + 0x16));
      }
      *param_3 = *param_3 | 1;
      param_3[1] = param_3[1];
      break;
    case 1:
      if (1 < (int)__n) {
        uVar2 = *(undefined1 *)(param_1 + uVar17);
        uVar3 = *(undefined1 *)((int)__src + 1);
        *param_3 = *param_3 | 2;
        param_3[1] = param_3[1];
        *(ushort *)((int)param_3 + 0x1a) = CONCAT11(uVar2,uVar3);
      }
      break;
    case 2:
      if (-1 < (int)__n) {
        puVar16 = param_3 + 0x12;
        if (0xfe < (int)__n) {
          __n = 0xff;
        }
        if (__n != 0) {
          puVar16 = memcpy(puVar16,__src,__n);
        }
        *(bool *)((int)param_3 + __n + 0x48) = bVar5;
        FUN_00018c78(puVar16,0x100);
        *param_3 = *param_3 | 4;
        param_3[1] = param_3[1];
      }
      break;
    case 3:
      if (0 < (int)__n) {
        *(undefined1 *)(param_3 + 7) = *(undefined1 *)(param_1 + uVar17);
        *param_3 = *param_3 | 8;
        param_3[1] = param_3[1];
      }
      break;
    case 4:
      if (-1 < (int)__n) {
        puVar16 = param_3 + 0x52;
        if (0xfe < (int)__n) {
          __n = 0xff;
        }
        if (__n != 0) {
          puVar16 = memcpy(puVar16,__src,__n);
        }
        *(bool *)((int)param_3 + __n + 0x148) = bVar5;
        FUN_00018c78(puVar16,0x100);
        *param_3 = *param_3 | 0x10;
        param_3[1] = param_3[1];
      }
      break;
    case 5:
      if (3 < (int)__n) {
        param_3[0x11] =
             (uint)*(byte *)((int)__src + 1) << 0x10 | (uint)*(byte *)(param_1 + uVar17) << 0x18 |
             (uint)*(byte *)((int)__src + 3) | (uint)*(byte *)((int)__src + 2) << 8;
        *param_3 = *param_3 | 0x20;
        param_3[1] = param_3[1];
      }
      break;
    case 6:
      if (-1 < (int)__n) {
        if (0x3e < (int)__n) {
          __n = 0x3f;
        }
        if (__n != 0) {
          memcpy(param_3 + 0x92,__src,__n);
        }
        uVar17 = *param_3;
        uVar6 = param_3[1];
        *(bool *)((int)param_3 + __n + 0x248) = bVar5;
        *param_3 = uVar17 | 0x40;
        param_3[1] = uVar6;
      }
      break;
    case 7:
      if (0 < (int)__n) {
        *(undefined1 *)((int)param_3 + 0x1d) = *(undefined1 *)(param_1 + uVar17);
        *param_3 = *param_3 | 0x80;
        param_3[1] = param_3[1];
      }
      break;
    case 8:
      if (0 < (int)__n) {
        *(undefined1 *)((int)param_3 + 0x1e) = *(undefined1 *)(param_1 + uVar17);
        *param_3 = *param_3 | 0x100;
        param_3[1] = param_3[1];
      }
      break;
    case 9:
      if (0 < (int)__n) {
        *(undefined1 *)((int)param_3 + 0x1f) = *(undefined1 *)(param_1 + uVar17);
        *param_3 = *param_3 | 0x200;
        param_3[1] = param_3[1];
      }
      break;
    case 10:
      if ((1 < (int)__n) && (uVar17 = (uint)*(ushort *)((int)param_3 + 0x42), uVar17 < 0x10)) {
        puVar7 = (undefined1 *)((int)__src + 1);
        do {
          uVar6 = uVar17 + 1;
          iVar10 = uVar17 * 2;
          uVar17 = uVar6 & 0xffff;
          *(short *)((int)param_3 + 0x42) = (short)uVar6;
          *(ushort *)((int)param_3 + iVar10 + 0x22) = CONCAT11(puVar7[-1],*puVar7);
          if (puVar7 == (undefined1 *)((int)__src + (uVar13 - 6 | 1))) break;
          puVar7 = puVar7 + 2;
        } while (uVar17 != 0x10);
      }
      *param_3 = *param_3 | 0x400;
      param_3[1] = param_3[1];
      break;
    case 0xb:
      if (1 < (int)__n) {
        uVar2 = *(undefined1 *)(param_1 + uVar17);
        uVar3 = *(undefined1 *)((int)__src + 1);
        *param_3 = *param_3 | 0x800;
        param_3[1] = param_3[1];
        *(ushort *)(param_3 + 8) = CONCAT11(uVar2,uVar3);
      }
      break;
    case 0xc:
      uVar6 = *param_3;
      uVar9 = param_3[1];
      bVar18 = __src != (void *)0x0;
      iVar10 = 0;
      if (bVar18) {
        iVar10 = uVar13 - 5;
      }
      *(short *)((int)param_3 + 0x5ca) = (short)__n;
      *param_3 = uVar6 | 0x1000;
      param_3[1] = uVar9;
      if ((bVar18 && __n != 1) && iVar10 < 0 == (bVar18 && SBORROW4(__n,1))) {
        *(ushort *)(param_3 + 0x173) =
             CONCAT11(*(undefined1 *)(param_1 + uVar17),*(undefined1 *)((int)__src + 1));
        *param_3 = uVar6 | 0x11000;
        param_3[1] = uVar9;
      }
      break;
    case 0xd:
      bVar18 = __src == (void *)0x0;
      uVar17 = 0;
      if (!bVar18) {
        uVar17 = __n;
      }
      bVar4 = (int)uVar17 < 0;
      bVar19 = __n == 0;
      if ((bVar18 || bVar19) || bVar4) {
        uVar6 = 1;
      }
      *(short *)((int)param_3 + 0x5ce) = (short)__n;
      if ((!bVar18 && !bVar19) && !bVar4) {
        uVar6 = 0;
      }
      *(bool *)(param_3 + 0x174) = bVar5;
      if ((!bVar18 && !bVar19) && !bVar4) {
        pbVar8 = (byte *)((int)__src + -1);
        puVar16 = puVar15;
        if (0xff < (int)__n) {
          __n = 0x100;
        }
        do {
          pbVar8 = pbVar8 + 1;
          bVar1 = *pbVar8;
          uVar17 = uVar6;
          if (bVar1 == 0) break;
          if (((bVar1 & 0x80) == 0) && (0x5e < bVar1 - 0x20)) {
            *(byte *)puVar16 = 0x3f;
          }
          else {
            *(byte *)puVar16 = bVar1;
          }
          uVar6 = uVar6 + 1;
          puVar16 = (uint *)((int)puVar16 + 1);
          uVar17 = __n;
        } while (uVar6 != __n);
        *(bool *)((int)puVar15 + uVar17) = bVar5;
      }
      *param_3 = *param_3 | 0x6000;
      param_3[1] = param_3[1];
    }
  }
  if ((*param_3 & 0x800) == 0) {
    *(undefined2 *)(param_3 + 8) = 1000;
  }
  return;
}



/* Function: FUN_00019860 @ 00019860 */

undefined4 FUN_00019860(undefined1 *param_1,uint param_2,undefined4 param_3)

{
  int iVar1;
  
  if (param_1 != (undefined1 *)0x0 && 0x55 < param_2) {
    *param_1 = 0;
    param_1[2] = 0;
    param_1[3] = 0x1e;
    iVar1 = DAT_000199b4;
    param_1[9] = (char)param_3;
    param_1[8] = (char)((uint)param_3 >> 8);
    param_1[1] = 0x56;
    param_1[0xb] = 0x22;
    param_1[4] = 0;
    param_1[6] = 0;
    param_1[7] = 0;
    param_1[10] = 0;
    param_1[0xc] = 0;
    param_1[5] = 6;
    param_1[0xd] = 1;
    memcpy(param_1 + 0xe,(void *)(iVar1 + 0x198bc),0x1e);
    param_1[0x2d] = 6;
    param_1[0x30] = 1;
    param_1[0x33] = 6;
    param_1[0x36] = 1;
    param_1[0x39] = 6;
    param_1[0x3c] = 1;
    param_1[0x3f] = 6;
    param_1[0x42] = 1;
    param_1[0x45] = 6;
    param_1[0x47] = 6;
    param_1[0x49] = 6;
    param_1[0x4b] = 6;
    param_1[0x2c] = 0;
    param_1[0x2e] = 0;
    param_1[0x31] = 0;
    param_1[0x32] = 0;
    param_1[0x34] = 0;
    param_1[0x37] = 0;
    param_1[0x38] = 0;
    param_1[0x3a] = 0;
    param_1[0x3d] = 0;
    param_1[0x3e] = 0;
    param_1[0x40] = 0;
    param_1[0x43] = 0;
    param_1[0x44] = 0;
    param_1[0x46] = 0;
    param_1[0x48] = 0;
    param_1[0x4a] = 0;
    param_1[0x4c] = 0;
    param_1[0x2f] = 2;
    param_1[0x35] = 3;
    param_1[0x3b] = 4;
    param_1[0x41] = 5;
    param_1[0x4d] = 7;
    param_1[0x4e] = 1;
    param_1[0x51] = 6;
    param_1[0x55] = 6;
    param_1[0x4f] = 0;
    param_1[0x50] = 0;
    param_1[0x52] = 0;
    param_1[0x54] = 0;
    param_1[0x53] = 8;
    return 0x56;
  }
  return 0;
}



/* Function: FUN_000199b8 @ 000199b8 */

undefined2 FUN_000199b8(int param_1,uint param_2)

{
  uint uVar1;
  uint uVar2;
  int iVar3;
  uint uVar4;
  
  if (param_1 == 0 || param_2 < 10) {
    return 0;
  }
  uVar1 = 4;
  do {
    uVar4 = uVar1 + 4;
    iVar3 = uVar1 + 2;
    if (param_2 < uVar4) {
      return 0;
    }
    uVar2 = (uint)CONCAT11(*(undefined1 *)(param_1 + uVar1),*(undefined1 *)(param_1 + uVar1 + 1));
    uVar1 = uVar1 + uVar2;
    if (uVar2 < 4) {
      return 0;
    }
    if (param_2 < uVar1) {
      return 0;
    }
  } while (CONCAT11(*(undefined1 *)(param_1 + iVar3),*(undefined1 *)(param_1 + iVar3 + 1)) != 0 ||
           uVar2 < 6);
  return CONCAT11(*(undefined1 *)(param_1 + uVar4),*(undefined1 *)(param_1 + uVar4 + 1));
}



/* Function: FUN_00019a54 @ 00019a54 */

undefined4 FUN_00019a54(void)

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
  
  __mutex_00 = (pthread_mutex_t *)(DAT_00019ce8 + 0x19a74);
  piVar8 = (int *)(DAT_00019cec + 0x19a7c);
  iVar7 = DAT_00019cf0 + 0x19a84;
  __mutex_01 = (pthread_mutex_t *)(DAT_00019cf4 + 0x19a8c);
  piVar9 = (int *)(DAT_00019cf8 + 0x19a90);
  local_2c[0] = 0;
  puVar3 = (undefined4 *)(DAT_00019cfc + 0x19a98);
LAB_00019a98:
  pthread_mutex_lock(__mutex_00);
  iVar4 = *piVar8;
  pthread_mutex_unlock(__mutex_00);
  if (iVar4 != 0) {
    return 0;
  }
  iVar4 = spawnl(1,iVar7,iVar7,0);
  if (iVar4 == -1) {
    pcVar10 = *(code **)(DAT_00019d2c + 0x19c14);
    if (pcVar10 != (code *)0x0) {
      puVar5 = (undefined4 *)__get_errno_ptr();
      (*pcVar10)(DAT_00019d30 + 0x19c2c,*puVar5);
    }
  }
  else {
    if ((code *)*puVar3 != (code *)0x0) {
      (*(code *)*puVar3)(DAT_00019d00 + 0x19af4,iVar4);
    }
    pthread_mutex_lock(__mutex_01);
    if (*piVar9 != 0) {
      pthread_mutex_unlock(__mutex_01);
      kill(iVar4,0xf);
      waitpid(iVar4,(int *)local_2c,0);
      return 0;
    }
    *(int *)(DAT_00019d04 + 0x19b1c) = iVar4;
    pthread_mutex_unlock(__mutex_01);
    do {
      _Var2 = waitpid(iVar4,(int *)local_2c,0);
      if (_Var2 != -1) {
        pthread_mutex_lock((pthread_mutex_t *)(DAT_00019d08 + 0x19b5c));
        if (*(int *)(DAT_00019d0c + 0x19b68) != iVar4) goto LAB_00019b70;
        goto LAB_00019ca4;
      }
      piVar1 = (int *)__get_errno_ptr();
    } while (*piVar1 == 4);
    __mutex = (pthread_mutex_t *)(DAT_00019d20 + 0x19bd8);
    pthread_mutex_lock(__mutex);
    if (*(int *)(DAT_00019d24 + 0x19be8) == iVar4) {
LAB_00019ca4:
      *(undefined4 *)(DAT_00019d40 + 0x19cb4) = 0xffffffff;
LAB_00019b70:
      iVar6 = *(int *)(DAT_00019d10 + 0x19b80);
      pthread_mutex_unlock((pthread_mutex_t *)(DAT_00019d14 + 0x19b84));
      if (((_Var2 != iVar4) || ((local_2c[0] & 0xff) != 0)) || ((local_2c[0] & 0xffff) >> 8 != 0x49)
         ) goto LAB_00019b90;
      puVar5 = (undefined4 *)(DAT_00019d34 + 0x19c60);
      pcVar10 = (code *)*puVar5;
      if (pcVar10 != (code *)0x0) {
        (*pcVar10)(DAT_00019d38 + 0x19c7c,_Var2,local_2c[0],iVar6);
        pcVar10 = (code *)*puVar5;
        if (pcVar10 != (code *)0x0) {
          (*pcVar10)(DAT_00019d3c + 0x19c94);
        }
      }
      if (iVar6 == 0) {
        __seconds = 10;
LAB_00019bc4:
        sleep(__seconds);
      }
      goto LAB_00019a98;
    }
    iVar6 = *(int *)(DAT_00019d28 + 0x19c00);
    pthread_mutex_unlock(__mutex);
LAB_00019b90:
    if (*(code **)(DAT_00019d18 + 0x19b9c) != (code *)0x0) {
      (**(code **)(DAT_00019d18 + 0x19b9c))(DAT_00019d1c + 0x19bb8,_Var2,local_2c[0],iVar6);
      if (iVar6 == 0) goto LAB_00019bc0;
      goto LAB_00019a98;
    }
    if (iVar6 != 0) goto LAB_00019a98;
  }
LAB_00019bc0:
  __seconds = 1;
  goto LAB_00019bc4;
}



/* Function: FUN_00019e40 @ 00019e40 */

void FUN_00019e40(void)

{
  undefined4 *puVar1;
  pthread_mutex_t *__mutex;
  __pid_t __pid;
  
  if (*(int *)(DAT_00019ed8 + 0x19e4c) == 0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_00019edc + 0x19e64);
  pthread_mutex_lock(__mutex);
  __pid = *(__pid_t *)(DAT_00019ee0 + 0x19e7c);
  *(undefined4 *)(DAT_00019ee4 + 0x19e84) = 1;
  pthread_mutex_unlock(__mutex);
  if (0 < __pid) {
    kill(__pid,0xf);
  }
  pthread_join(*(pthread_t *)(DAT_00019ee8 + 0x19eb0),(void **)0x0);
  puVar1 = (undefined4 *)(DAT_00019ef0 + 0x19ed0);
  *(undefined4 *)(DAT_00019eec + 0x19ec8) = 0;
  *puVar1 = 0xffffffff;
  return;
}



/* Function: FUN_00019ef4 @ 00019ef4 */

void FUN_00019ef4(void)

{
  char *pcVar1;
  int iVar2;
  
  if (-1 < *(int *)(DAT_00019f38 + 0x19f04)) {
    close(*(int *)(DAT_00019f38 + 0x19f04));
  }
  iVar2 = DAT_00019f3c;
  *(undefined4 *)(DAT_00019f3c + 0x19f24) = 0xffffffff;
  pcVar1 = "SocketAccept" + iVar2 + 0xc;
  pcVar1[0] = '\0';
  pcVar1[1] = '\0';
  pcVar1[2] = '\0';
  pcVar1[3] = '\0';
  pcVar1 = "SocketAccept" + iVar2 + 4;
  pcVar1[0] = '\x01';
  pcVar1[1] = '\0';
  pcVar1[2] = '\0';
  pcVar1[3] = '\0';
  pcVar1 = "NetSocket_CreateWithNative" + iVar2;
  pcVar1[0] = '\0';
  pcVar1[1] = '\0';
  pcVar1[2] = '\0';
  pcVar1[3] = '\0';
  return;
}



/* Function: FUN_00019f40 @ 00019f40 */

void FUN_00019f40(void)

{
  int iVar1;
  uint *puVar2;
  int *piVar3;
  uint uVar4;
  bool bVar5;
  
  iVar1 = DAT_00019fc8;
  DataMemoryBarrier(0xf);
  piVar3 = (int *)("HIDDeviceCreateVirtual" + DAT_00019fc8 + 8);
  do {
    bVar5 = *piVar3 == 0;
    if (!bVar5) goto LAB_00019f74;
    bVar5 = (bool)hasExclusiveAccess(piVar3);
  } while (!bVar5);
  *piVar3 = 1;
  bVar5 = true;
LAB_00019f74:
  DataMemoryBarrier(0xf);
  if (bVar5) {
    puVar2 = (uint *)("HIDDeviceCopyProperty" + iVar1 + 4);
    DataMemoryBarrier(0xf);
    do {
      uVar4 = *puVar2;
      bVar5 = (bool)hasExclusiveAccess(puVar2);
    } while (!bVar5);
    *puVar2 = uVar4 + 1;
    *(uint *)("HIDDeviceCopyProperty" + iVar1 + 8) =
         *(int *)("HIDDeviceCopyProperty" + iVar1 + 8) + (uint)(0xfffffffe < uVar4);
    DataMemoryBarrier(0xf);
  }
  pthread_cond_signal((pthread_cond_t *)("HIDDevicePostReport" + DAT_00019fcc + 0x10));
  return;
}



/* Function: FUN_00019fd0 @ 00019fd0 */

void FUN_00019fd0(void)

{
  char *pcVar1;
  int iVar2;
  ssize_t sVar3;
  int iVar4;
  char *pcVar5;
  int *piVar6;
  char cStack_39;
  char local_38 [20];
  
  piVar6 = (int *)(DAT_0001a088 + 0x19fe4);
  if (((1 < *(int *)("CFRetain" + DAT_0001a088) - 2U) || (iVar2 = *piVar6, iVar2 < 0)) ||
     (sVar3 = read(iVar2,local_38,0x10), iVar2 = DAT_0001a08c, sVar3 < 1)) {
    return;
  }
  do {
    iVar4 = 0;
    pcVar5 = &cStack_39;
    do {
      while( true ) {
        pcVar5 = pcVar5 + 1;
        iVar4 = iVar4 + 1;
        if (*pcVar5 != 'K') break;
        pcVar1 = "kCFLArrayCallBacksCFLTypes" + iVar2 + 0xc;
        pcVar1[0] = '\x01';
        pcVar1[1] = '\0';
        pcVar1[2] = '\0';
        pcVar1[3] = '\0';
        FUN_00019f40();
        if (sVar3 <= iVar4) goto LAB_0001a068;
      }
    } while (iVar4 < sVar3);
LAB_0001a068:
    sVar3 = read(*piVar6,local_38,0x10);
    if (sVar3 < 1) {
      return;
    }
  } while( true );
}



/* Function: FUN_0001a090 @ 0001a090 */

void FUN_0001a090(char *param_1,undefined4 param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  char acStack_2a0 [644];
  undefined4 uStack_c;
  undefined4 uStack_8;
  undefined4 uStack_4;
  
  iVar1 = DAT_0001a0f8;
  if (*(int *)("_ZN14NmeIAP2Message6DecodeEPKhi" + DAT_0001a0f8 + 0x10) != 0) {
    uStack_c = param_2;
    uStack_8 = param_3;
    uStack_4 = param_4;
    vsnprintf(acStack_2a0,0x280,param_1,&uStack_c);
    (**(code **)("_ZN14NmeIAP2Message6DecodeEPKhi" + iVar1 + 0x10))
              (DAT_0001a0fc + 0x1a0e8,acStack_2a0);
  }
  return;
}



/* Function: FUN_0001a100 @ 0001a100 */

undefined4 FUN_0001a100(void)

{
  char *pcVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  int *piVar6;
  
  iVar2 = DAT_0001a1dc;
  piVar6 = (int *)(DAT_0001a1dc + 0x1a110);
  if (-1 < *piVar6) {
    return 1;
  }
  iVar3 = accept(*(int *)(DAT_0001a1dc + 0x1a114),(sockaddr *)0x0,(socklen_t *)0x0);
  *piVar6 = iVar3;
  if (iVar3 < 0) {
    return 0;
  }
  uVar4 = fcntl(iVar3,3,0);
  if ((-1 < (int)uVar4) && (iVar3 = fcntl(*piVar6,4,uVar4 | 0x80), -1 < iVar3)) {
    fcntl(*piVar6,2);
    iVar3 = DAT_0001a1e0;
    iVar5 = *(int *)("_ZNK14NmeIAP2Message6EncodeER8NmeArrayIhE" + iVar2 + 0x14);
    pcVar1 = "_ZNK14NmeIAP2Message6EncodeER8NmeArrayIhE" + iVar2 + 0x20;
    pcVar1[0] = '\x01';
    pcVar1[1] = '\0';
    pcVar1[2] = '\0';
    pcVar1[3] = '\0';
    pcVar1 = "_ZNK14NmeIAP2Message6EncodeER8NmeArrayIhE" + iVar2 + 0x18;
    pcVar1[0] = '\x01';
    pcVar1[1] = '\0';
    pcVar1[2] = '\0';
    pcVar1[3] = '\0';
    *(uint *)("_ZNK14NmeIAP2Message6EncodeER8NmeArrayIhE" + iVar2 + 0x24) =
         (uint)(iVar5 == 0 || iVar5 == 3);
    FUN_0001a090(iVar3 + 0x1a19c);
    return 1;
  }
  piVar6 = (int *)(DAT_0001a1e4 + 0x1a1c8);
  close(*piVar6);
  *piVar6 = -1;
  return 0;
}



/* Function: FUN_0001a1e8 @ 0001a1e8 */

undefined4 FUN_0001a1e8(uint param_1,int param_2,uint param_3)

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
    lVar1 = FUN_0001b7ec(local_30._4_4_,(int)local_30._4_4_ >> 0x1f,1000,0);
    lVar1 = (ulonglong)(uint)iVar3 * 1000000 + lVar1;
  }
  else {
    lVar1 = 0;
  }
  iVar4 = DAT_0001a434;
  iVar3 = DAT_0001a430;
  uVar10 = (uint)((ulonglong)lVar1 >> 0x20);
  uVar5 = (uint)lVar1;
  if (param_3 == 0) {
    return 1;
  }
  if ((*(int *)("/eso/bin/apps/mhi2q-aa-turn-card-renderer" + DAT_0001a42c + 0x24) != 0) ||
     (*(int *)("/eso/bin/apps/mhi2q-aa-turn-card-renderer" + DAT_0001a42c + 0x1c) != 0)) {
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
    FUN_00019fd0();
    iVar6 = clock_gettime(2,(timespec *)&local_30);
    iVar9 = local_30.fd;
    uVar11 = extraout_r1;
    if (iVar6 != 0) {
      uVar11 = 0;
    }
    lVar2 = (ulonglong)uVar11 << 0x20;
    puVar13 = extraout_r3;
    if (iVar6 == 0) {
      lVar2 = FUN_0001b7ec(local_30._4_4_,(int)local_30._4_4_ >> 0x1f,1000,0);
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
        if (*(int *)("turn-card: cannot spawn renderer errno=%d" + iVar3 + 0x18) - 2U < 2) {
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
    if (*(int *)("turn-card: singleton owned by an earlier GAL renderer; retrying in 10s" +
                iVar4 + 8) != 0) break;
    if (*(int *)("turn-card: singleton owned by an earlier GAL renderer; retrying in 10s" + iVar4)
        != 0) {
      return 0;
    }
  }
  return 0;
}



/* Function: FUN_0001a438 @ 0001a438 */

bool FUN_0001a438(int param_1,uint *param_2,undefined4 param_3,undefined4 param_4)

{
  int iVar1;
  uint uVar2;
  uint uVar3;
  int iVar4;
  undefined4 *puVar5;
  
  uVar2 = 0x800000 - param_1;
  uVar3 = *param_2;
  puVar5 = (undefined4 *)(DAT_0001a4ac + 0x1a45c);
  if (uVar3 <= uVar2) {
    uVar2 = uVar3;
  }
  iVar4 = DAT_0001a4b0 + 0x1a468;
  iVar1 = FUN_0001a1e8(*puVar5,iVar4 + param_1,uVar2,uVar3,param_4);
  if (iVar1 != 0) {
    if (*param_2 == uVar2) {
      return true;
    }
    iVar1 = FUN_0001a1e8(*puVar5,iVar4,*param_2 - uVar2);
    return iVar1 != 0;
  }
  return false;
}



/* Function: FUN_0001a4b4 @ 0001a4b4 */

bool FUN_0001a4b4(undefined4 *param_1,int param_2,byte param_3)

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
  
  if (*(int *)("stream: viewer disconnected after %llu bytes" + DAT_0001a730 + 0x20) - 2U < 2) {
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
  puVar6 = (undefined4 *)(DAT_0001a734 + 0x1a5cc);
  iVar3 = FUN_0001a1e8(*puVar6,&local_48,uVar4);
  bVar11 = false;
  if (iVar3 != 0) {
    if (param_2 == 0) {
      iVar3 = FUN_0001a438(*param_1,param_1 + 1);
      bVar11 = iVar3 != 0;
    }
    else {
      iVar3 = FUN_0001a1e8(*puVar6,param_2,param_1[1]);
      bVar11 = false;
      if (iVar3 != 0) {
        bVar11 = true;
      }
    }
  }
  return bVar11;
}



/* Function: FUN_0001a738 @ 0001a738 */

undefined4 FUN_0001a738(int param_1)

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
  
  iVar2 = DAT_0001a894;
  puVar5 = (undefined4 *)(DAT_0001a894 + 0x1a754);
  __mutex = (pthread_mutex_t *)(DAT_0001a894 + 0x1cbb0);
  pthread_mutex_lock(__mutex);
  __n = *(uint *)(iVar2 + 0x1ab5c);
  if (__n == 0) {
    pthread_mutex_unlock(__mutex);
    return 0;
  }
  memcpy(auStack_420,(void *)(iVar2 + 0x1a75c),__n);
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
    iVar3 = FUN_0001a1e8(*puVar5,auStack_420,__n);
    if (iVar3 == 0) {
      return 0;
    }
    puVar6 = (uint *)(iVar2 + 0x1cbc4);
    DataMemoryBarrier(0xf);
    do {
      uVar4 = *puVar6;
      bVar1 = (bool)hasExclusiveAccess(puVar6);
    } while (!bVar1);
    *puVar6 = uVar4 + __n;
    *(uint *)(iVar2 + 0x1cbc8) = *(int *)(iVar2 + 0x1cbc8) + (uint)CARRY4(uVar4,__n);
  }
  else {
    iVar3 = FUN_0001a4b4(&local_440);
    if (iVar3 == 0) {
      return 0;
    }
    puVar6 = (uint *)(iVar2 + 0x1cbc4);
    DataMemoryBarrier(0xf);
    if (*(int *)(iVar2 + 0x1cb7c) - 2U < 2) {
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
    *(uint *)(iVar2 + 0x1cbc8) = *(int *)(iVar2 + 0x1cbc8) + (uint)CARRY4(uVar4,iVar3 + __n);
  }
  DataMemoryBarrier(0xf);
  return 1;
}



/* Function: FUN_0001ad4c @ 0001ad4c */

undefined4 FUN_0001ad4c(undefined4 param_1,uint param_2,undefined4 param_3)

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
  
  iVar2 = DAT_0001b008;
  bVar7 = param_2 == 3;
  if (param_2 < 4) {
    bVar7 = *(int *)(DAT_0001b008 + 0x1d190) == 0;
  }
  if (bVar7) {
    __mutex_00 = (pthread_mutex_t *)(DAT_0001b008 + 0x1d1bc);
    __mutex = (pthread_mutex_t *)(DAT_0001b008 + 0x1d1b4);
    pthread_mutex_lock(__mutex);
    *(undefined4 *)(iVar2 + 0x1d1ac) = param_1;
    *(uint *)(iVar2 + 0x1d188) = param_2;
    *(undefined4 *)(iVar2 + 0x1d1cc) = param_3;
    pthread_mutex_lock(__mutex_00);
    *(undefined4 *)(iVar2 + 0x1b168) = 0;
    pthread_mutex_unlock(__mutex_00);
    *(undefined4 *)(iVar2 + 0x1d194) = 0;
    *(undefined4 *)(iVar2 + 0x1d170) = 0;
    *(undefined4 *)(iVar2 + 0x1d198) = 0;
    *(undefined4 *)(iVar2 + 0x1d174) = 0;
    *(undefined4 *)(iVar2 + 0x1d178) = 0;
    *(undefined4 *)(iVar2 + 0x1d17c) = 0;
    *(undefined4 *)(iVar2 + 0x1d180) = 0;
    *(undefined4 *)(iVar2 + 0x1d19c) = 0;
    *(undefined4 *)(iVar2 + 0x1d1a0) = 0;
    *(undefined4 *)(iVar2 + 0x1d1a4) = 0;
    *(undefined4 *)(iVar2 + 0x1d1a8) = 0;
    *(undefined4 *)(iVar2 + 0x1d184) = 1;
    *(undefined4 *)(iVar2 + 0x1d18c) = 1;
    memset((void *)(iVar2 + 0x1d1d0),0,0x40);
    pthread_mutex_unlock(__mutex);
    local_3c = 1;
    iVar3 = socket(2,1,0);
    *(int *)(iVar2 + 0x1ad64) = iVar3;
    if (iVar3 < 0) {
      puVar4 = (undefined4 *)__get_errno_ptr();
      FUN_0001a090(DAT_0001b014 + 0x1af08,*puVar4);
    }
    else {
      setsockopt(iVar3,0xffff,4,&local_3c,4);
      uVar6 = *(uint *)(iVar2 + 0x1d1ac) & 0xffff;
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
      iVar3 = bind(*(int *)(iVar2 + 0x1ad64),&local_38,0x10);
      if ((iVar3 == 0) && (iVar3 = listen(*(int *)(iVar2 + 0x1ad64),1), iVar3 == 0)) {
        uVar6 = fcntl(*(int *)(iVar2 + 0x1ad64),3,0);
        if (-1 < (int)uVar6) {
          fcntl(*(int *)(iVar2 + 0x1ad64),4,uVar6 | 0x80);
        }
        iVar2 = DAT_0001b018;
        fcntl(*(int *)(DAT_0001b018 + 0x1af4c),2,1);
        __start_routine = (__start_routine *)(DAT_0001b01c + 0x1af6c);
        *(undefined4 *)(iVar2 + 0x1d378) = 1;
        iVar3 = pthread_create((pthread_t *)(iVar2 + 0x1d398),(pthread_attr_t *)0x0,__start_routine,
                               (void *)0x0);
        if (iVar3 == 0) {
          if (*(int *)(iVar2 + 0x1d370) - 2U < 2) {
            iVar3 = DAT_0001b028 + 0x1afd4;
          }
          else if (param_2 == 1) {
            iVar3 = (int)&DAT_0001b008 + DAT_0001b030;
          }
          else {
            iVar3 = DAT_0001b020 + 0x1afa4;
          }
          FUN_0001a090(DAT_0001b024 + 0x1afc0,*(undefined4 *)(iVar2 + 0x1d394),iVar3,0x800000,0x100,
                       0x200000);
          return 1;
        }
        FUN_0001a090(DAT_0001b02c + 0x1afe0);
        *(undefined4 *)(iVar2 + 0x1d378) = 0;
        close(*(int *)(iVar2 + 0x1af4c));
        *(undefined4 *)(iVar2 + 0x1af4c) = 0xffffffff;
        return 0;
      }
      iVar2 = DAT_0001b00c;
      uVar5 = *(undefined4 *)(DAT_0001b00c + 0x1d310);
      puVar4 = (undefined4 *)__get_errno_ptr();
      FUN_0001a090(DAT_0001b010 + 0x1aee0,uVar5,*puVar4);
      close(*(int *)(iVar2 + 0x1aec8));
      *(undefined4 *)(iVar2 + 0x1aec8) = 0xffffffff;
    }
  }
  return 0;
}



/* Function: FUN_0001b034 @ 0001b034 */

void FUN_0001b034(void)

{
  int iVar1;
  int *piVar2;
  pthread_mutex_t *__mutex;
  
  iVar1 = DAT_0001b0d0;
  piVar2 = (int *)(DAT_0001b0d0 + 0x1b044);
  if (*(int *)(DAT_0001b0d0 + 0x1d474) == 0) {
    return;
  }
  __mutex = (pthread_mutex_t *)(DAT_0001b0d0 + 0x1d498);
  *(undefined4 *)(DAT_0001b0d0 + 0x1d474) = 0;
  pthread_mutex_lock(__mutex);
  *(undefined4 *)(iVar1 + 0x1d48c) = 1;
  pthread_cond_signal((pthread_cond_t *)(iVar1 + 0x1d4a8));
  pthread_mutex_unlock(__mutex);
  if (-1 < *piVar2) {
    shutdown(*piVar2,2);
  }
  iVar1 = DAT_0001b0d4;
  pthread_join(*(pthread_t *)(DAT_0001b0d4 + 0x1d4f4),(void **)0x0);
  if (-1 < *(int *)(iVar1 + 0x1b0a8)) {
    close(*(int *)(iVar1 + 0x1b0a8));
  }
  *(undefined4 *)((int)&DAT_0001b0d0 + DAT_0001b0d8) = 0xffffffff;
  return;
}



/* Function: FUN_0001b0dc @ 0001b0dc */

void FUN_0001b0dc(void)

{
  int iVar1;
  
  iVar1 = DAT_0001b140;
  pthread_mutex_lock((pthread_mutex_t *)(DAT_0001b140 + 0x1d540));
  if ((*(int *)(iVar1 + 0x1d51c) != 0) && (*(int *)(iVar1 + 0x1d534) == 0)) {
    *(undefined4 *)(iVar1 + 0x1d52c) = 1;
    pthread_cond_signal((pthread_cond_t *)(iVar1 + 0x1d550));
  }
  pthread_mutex_unlock((pthread_mutex_t *)(DAT_0001b144 + 0x1d574));
  return;
}



/* Function: FUN_0001b148 @ 0001b148 */

void FUN_0001b148(char *param_1,uint param_2,int param_3,undefined4 param_4,undefined4 param_5,
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
    lVar3 = FUN_0001b7ec(local_30.tv_nsec,local_30.tv_nsec >> 0x1f,1000,0);
    lVar3 = (ulonglong)(uint)_Var4 * 1000000 + lVar3;
  }
  iVar5 = DAT_0001b738;
  if (param_2 == 0 || param_1 == (char *)0x0) {
    return;
  }
  if (*(int *)(DAT_0001b738 + 0x1d5f0) == 0) {
    return;
  }
  if (param_3 == 0) {
    if (*(int *)(DAT_0001b738 + 0x1d5f4) == 0) {
      puVar7 = (uint *)(DAT_0001b738 + 0x1d638);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        piVar1 = (int *)(DAT_0001b738 + 0x1d63c);
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1d63c) = *piVar1 + (uint)CARRY4(uVar17,param_2);
LAB_0001b5d4:
      DataMemoryBarrier(0xf);
      return;
    }
    pthread_mutex_lock((pthread_mutex_t *)(DAT_0001b738 + 0x1d614));
    if (0x200000 < param_2) goto LAB_0001b1f4;
  }
  else {
    if (0x400 < param_2) {
      puVar16 = (uint *)(DAT_0001b738 + 0x1d650);
      puVar7 = (uint *)(DAT_0001b738 + 0x1d648);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        piVar1 = (int *)(DAT_0001b738 + 0x1d64c);
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + 1;
      *(uint *)(iVar5 + 0x1d64c) = *piVar1 + (uint)(0xfffffffe < uVar17);
      DataMemoryBarrier(0xf);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar16;
        iVar8 = *(int *)(iVar5 + 0x1d654);
        bVar2 = (bool)hasExclusiveAccess(puVar16);
      } while (!bVar2);
      *puVar16 = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1d654) = iVar8 + (uint)CARRY4(uVar17,param_2);
      puVar7 = (uint *)(iVar5 + 0x1d658);
      DataMemoryBarrier(0xf);
      DataMemoryBarrier(0xf);
      do {
        uVar17 = *puVar7;
        bVar2 = (bool)hasExclusiveAccess(puVar7);
      } while (!bVar2);
      *puVar7 = uVar17 + 1;
      *(uint *)(iVar5 + 0x1d65c) = *(int *)(iVar5 + 0x1d65c) + (uint)(0xfffffffe < uVar17);
      goto LAB_0001b5d4;
    }
    ppVar6 = (pthread_mutex_t *)(DAT_0001b738 + 0x1d61c);
    pthread_mutex_lock(ppVar6);
    memcpy((void *)(iVar5 + 0x1b1c8),param_1,param_2);
    *(uint *)(iVar5 + 0x1b5c8) = param_2;
    pthread_mutex_unlock(ppVar6);
    if (*(int *)(iVar5 + 0x1d5f4) == 0) {
      return;
    }
    pthread_mutex_lock((pthread_mutex_t *)(iVar5 + 0x1d614));
  }
  if (((0xff < *(uint *)(DAT_0001b750 + 0x1d868)) ||
      (0x800000U - *(int *)(DAT_0001b750 + 0x1d86c) < param_2)) &&
     (iVar19 = clock_gettime(0,&local_30), iVar8 = DAT_0001b758, iVar5 = DAT_0001b754, iVar19 == 0))
  {
    iVar19 = local_30.tv_nsec + 200000000;
    if (999999999 < local_30.tv_nsec + 200000000) {
      local_30.tv_sec = local_30.tv_sec + 1;
      iVar19 = local_30.tv_nsec + -800000000;
    }
    local_30.tv_nsec = iVar19;
    ppVar6 = (pthread_mutex_t *)(DAT_0001b754 + 0x1d92c);
    __cond = (pthread_cond_t *)(DAT_0001b754 + 0x1d93c);
    iVar19 = 0;
    while (((0xff < *(uint *)(iVar8 + 0x1d8fc) || (0x800000U - *(int *)(iVar8 + 0x1d900) < param_2))
           && ((*(int *)(iVar5 + 0x1d908) != 0 &&
               (*(int *)(iVar5 + 0x1d920) == 0 && iVar19 != 0x104))))) {
      iVar19 = pthread_cond_timedwait(__cond,ppVar6,&local_30);
    }
  }
LAB_0001b1f4:
  iVar8 = DAT_0001b764;
  iVar5 = DAT_0001b73c;
  iVar19 = DAT_0001b73c + 0x1b200;
  if ((*(int *)(DAT_0001b73c + 0x1d630) == 0) || (*(int *)(DAT_0001b73c + 0x1d648) != 0)) {
    pthread_mutex_unlock((pthread_mutex_t *)(DAT_0001b75c + 0x1da40));
  }
  else {
    if (param_2 < 0x200001) {
      uVar17 = *(uint *)(DAT_0001b73c + 0x1d618);
      iVar12 = *(int *)(DAT_0001b73c + 0x1d61c);
      if ((uVar17 < 0x100) && (param_2 <= 0x800000U - iVar12)) {
        iVar15 = *(int *)(DAT_0001b73c + 0x1d614);
        iVar18 = *(int *)(DAT_0001b73c + 0x1d620);
        *(longlong *)(iVar19 + (iVar15 + 0x21) * 0x20) = lVar3;
        iVar19 = iVar19 + iVar15 * 0x20;
        *(uint *)(iVar19 + 0x414) = param_2;
        *(undefined4 *)(iVar19 + 0x418) = param_5;
        *(undefined4 *)(iVar19 + 0x41c) = param_6;
        iVar8 = *(int *)(iVar5 + 0x1d624);
        *(int *)(iVar19 + 0x410) = iVar18;
        *(bool *)(iVar19 + 0x42c) = param_3 != 0;
        uVar9 = *(undefined4 *)(iVar5 + 0x1d624);
        *(int *)(iVar5 + 0x1d624) = iVar8 + 1;
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
                  if ((pcVar10[2] != '\0') || (pcVar10[3] != '\x01')) goto LAB_0001b2c4;
                  iVar19 = 4;
                }
                if (((uint)(iVar19 + iVar5) < param_2) && ((param_1[iVar19 + iVar5] & 0x1fU) == 5))
                {
                  uVar11 = 1;
                  goto LAB_0001b31c;
                }
              }
LAB_0001b2c4:
              pcVar10 = pcVar10 + 1;
              iVar5 = iVar8;
            } while (iVar8 != param_2 - 3);
            uVar11 = 0;
          }
        }
LAB_0001b31c:
        uVar14 = 0x800000 - iVar18;
        *(undefined1 *)((int)&DAT_0001b75c + DAT_0001b740 + iVar15 * 0x20 + 1) = uVar11;
        if (param_2 < uVar14) {
          memcpy((void *)(DAT_0001b76c + 0x1b714 + iVar18),param_1,param_2);
        }
        else {
          __dest = (void *)(DAT_0001b744 + 0x1b350);
          memcpy((void *)((int)__dest + iVar18),param_1,uVar14);
          if (uVar14 < param_2) {
            memcpy(__dest,param_1 + uVar14,param_2 - uVar14);
          }
        }
        iVar8 = DAT_0001b74c;
        iVar5 = DAT_0001b748;
        uVar13 = param_2 + iVar12;
        puVar7 = (uint *)(DAT_0001b74c + 0x1d818);
        uVar14 = *(uint *)(DAT_0001b748 + 0x1d834);
        *(uint *)(DAT_0001b748 + 0x1d7a8) = uVar13;
        *(uint *)(iVar5 + 0x1d7ac) = param_2 + iVar18 & 0x7fffff;
        if (uVar14 < uVar13) {
          *(uint *)(iVar5 + 0x1d834) = uVar13;
        }
        *(uint *)(iVar5 + 0x1d7a0) = iVar15 + 1U & 0xff;
        *(uint *)(iVar5 + 0x1d7a4) = uVar17 + 1;
        uVar17 = *puVar7;
        iVar5 = *(int *)(iVar8 + 0x1d81c);
        *puVar7 = uVar17 + 1;
        *(uint *)(iVar8 + 0x1d81c) = iVar5 + (uint)(0xfffffffe < uVar17);
        pthread_cond_signal((pthread_cond_t *)(iVar8 + 0x1d7fc));
        pthread_mutex_unlock((pthread_mutex_t *)(iVar8 + 0x1d7ec));
        return;
      }
      uVar14 = *(uint *)((int)&_DYNAMIC[0x10].d_val + DAT_0001b764);
      iVar5 = *(int *)((int)&_DYNAMIC[0x11].d_tag + DAT_0001b764);
      ppVar6 = (pthread_mutex_t *)((int)&_DYNAMIC[9].d_tag + DAT_0001b764);
      uVar13 = *(uint *)((int)&_DYNAMIC[0xf].d_val + DAT_0001b764);
      iVar19 = *(int *)((int)&_DYNAMIC[0x10].d_tag + DAT_0001b764);
      *(uint *)((int)&_DYNAMIC[0x10].d_val + DAT_0001b764) = uVar14 + param_2;
      *(uint *)((int)&_DYNAMIC[0x11].d_tag + iVar8) = iVar5 + (uint)CARRY4(uVar14,param_2);
      *(uint *)((int)&_DYNAMIC[0xf].d_val + iVar8) = uVar13 + 1;
      *(uint *)((int)&_DYNAMIC[0x10].d_tag + iVar8) = iVar19 + (uint)(0xfffffffe < uVar13);
      pthread_mutex_unlock(ppVar6);
      FUN_0001a090(DAT_0001b768 + 0x1b6f4,200,uVar17,iVar12,param_2);
    }
    else {
      uVar17 = *(uint *)(DAT_0001b73c + 0x1d690);
      iVar8 = *(int *)(DAT_0001b73c + 0x1d694);
      ppVar6 = (pthread_mutex_t *)(DAT_0001b73c + 0x1d654);
      uVar14 = *(uint *)(DAT_0001b73c + 0x1d688);
      piVar1 = (int *)(DAT_0001b73c + 0x1d68c);
      uVar13 = *(uint *)(DAT_0001b73c + 0x1d698);
      iVar19 = *(int *)(DAT_0001b73c + 0x1d69c);
      *(uint *)(DAT_0001b73c + 0x1d688) = uVar14 + 1;
      *(uint *)(iVar5 + 0x1d68c) = *piVar1 + (uint)(0xfffffffe < uVar14);
      *(uint *)(iVar5 + 0x1d698) = uVar13 + 1;
      *(uint *)(iVar5 + 0x1d69c) = iVar19 + (uint)(0xfffffffe < uVar13);
      *(uint *)(iVar5 + 0x1d690) = uVar17 + param_2;
      *(uint *)(iVar5 + 0x1d694) = iVar8 + (uint)CARRY4(uVar17,param_2);
      pthread_mutex_unlock(ppVar6);
      FUN_0001a090(DAT_0001b760 + 0x1b654,param_2,0x200000);
    }
    if (param_3 == 0) {
      FUN_00019f40();
    }
  }
  return;
}



/* Function: FUN_0001b770 @ 0001b770 */

bool FUN_0001b770(void)

{
  int *piVar1;
  bool bVar2;
  
  DataMemoryBarrier(0xf);
  piVar1 = (int *)((int)&PTR_snprintf_0001dbbc + DAT_0001b7b0);
  do {
    bVar2 = *piVar1 == 1;
    if (!bVar2) goto LAB_0001b7a0;
    bVar2 = (bool)hasExclusiveAccess(piVar1);
  } while (!bVar2);
  *piVar1 = 2;
  bVar2 = true;
LAB_0001b7a0:
  DataMemoryBarrier(0xf);
  return bVar2;
}



/* Function: FUN_0001b7b4 @ 0001b7b4 */

void FUN_0001b7b4(void)

{
  bool bVar1;
  
  do {
    bVar1 = (bool)hasExclusiveAccess((undefined4 *)((int)&PTR_kill_0001dbfc + DAT_0001b7e0));
  } while (!bVar1);
  *(undefined4 *)((int)&PTR_kill_0001dbfc + DAT_0001b7e0) = 0;
  DataMemoryBarrier(0xf);
  return;
}



/* Function: FUN_0001b7e4 @ 0001b7e4 */

undefined4 FUN_0001b7e4(void)

{
  return 1;
}



/* Function: FUN_0001b7ec @ 0001b7ec */

undefined8 FUN_0001b7ec(int param_1,int param_2,int param_3,int param_4)

{
  undefined8 uVar1;
  
  if (param_4 != 0 || param_3 != 0) {
    uVar1 = FUN_0001b864();
    return uVar1;
  }
  if (param_2 != 0 || param_1 != 0) {
    param_2 = -1;
    param_1 = -1;
  }
  return CONCAT44(param_2,param_1);
}



/* Function: FUN_0001b828 @ 0001b828 */

void FUN_0001b828(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_0001b8a4();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_0001b864 @ 0001b864 */

void FUN_0001b864(uint param_1,int param_2,undefined4 param_3,undefined4 param_4,int *param_5)

{
  uint uVar1;
  longlong lVar2;
  
  lVar2 = FUN_0001ba24();
  lVar2 = lVar2 * CONCAT44(param_4,param_3);
  uVar1 = (uint)lVar2;
  *param_5 = param_1 - uVar1;
  param_5[1] = param_2 - ((int)((ulonglong)lVar2 >> 0x20) + (uint)(param_1 < uVar1));
  return;
}



/* Function: FUN_0001b8a4 @ 0001b8a4 */

undefined8 FUN_0001b8a4(uint param_1,uint param_2,uint param_3,uint param_4)

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
          if (uVar4 == 0) goto LAB_0001b9a4;
        }
        bVar11 = param_1 < uVar7;
        uVar6 = param_1 - uVar7;
        param_1 = uVar6 * 2 + 1;
        param_2 = (param_2 - (uVar5 + bVar11)) * 2 + (uint)CARRY4(uVar6,uVar6) +
                  (uint)(0xfffffffe < uVar6 * 2);
        uVar4 = uVar4 - 1;
      } while (uVar4 != 0);
LAB_0001b9a4:
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



/* Function: FUN_0001ba24 @ 0001ba24 */

undefined8 FUN_0001ba24(uint param_1,uint param_2,uint param_3,uint param_4)

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
      if (uVar6 == 0) goto LAB_0001bb00;
    }
    bVar10 = param_1 < uVar8;
    uVar7 = param_1 - uVar8;
    param_1 = uVar7 * 2 + 1;
    param_2 = (param_2 - (uVar9 + bVar10)) * 2 + (uint)CARRY4(uVar7,uVar7) +
              (uint)(0xfffffffe < uVar7 * 2);
    uVar6 = uVar6 - 1;
  } while (uVar6 != 0);
LAB_0001bb00:
  uVar9 = param_1 >> (uVar5 & 0xff) | param_2 << (0x20 - uVar5 & 0xff) |
          param_2 >> (uVar5 - 0x20 & 0xff);
  uVar8 = uVar9 << (uVar5 & 0xff);
  return CONCAT44((uVar2 + param_2 + (uint)CARRY4(uVar1,param_1)) -
                  (((param_2 >> (uVar5 & 0xff)) << (uVar5 & 0xff) | uVar9 << (uVar5 - 0x20 & 0xff) |
                   uVar9 >> (0x20 - uVar5 & 0xff)) + (uint)(uVar1 + param_1 < uVar8)),
                  (uVar1 + param_1) - uVar8);
}



/* Function: _fini @ 0001bb48 */

void _fini(void)

{
  return;
}


