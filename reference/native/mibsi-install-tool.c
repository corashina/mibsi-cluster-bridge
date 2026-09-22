/* RECONSTRUCTED PSEUDOCODE - not original source, not build-ready.
 * Input: mibsi-install-tool
 * Language: ARM:LE:32:v8
 * Generated with Ghidra. Types, signatures and control flow may be inaccurate.
 */


/* Function: _DT_INIT @ 00100de8 */

void _DT_INIT(void)

{
  return;
}



/* Function: strcpy @ 00100e04 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strcpy(char *__dest,char *__src)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strcpy_00106ca4)(__dest);
  return pcVar1;
}



/* Function: waitpid @ 00100e10 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t waitpid(__pid_t __pid,int *__stat_loc,int __options)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_waitpid_00106ca8)(__pid);
  return _Var1;
}



/* Function: printf @ 00100e1c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int printf(char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_printf_00106cac)(__format);
  return iVar1;
}



/* Function: utime @ 00100e28 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int utime(char *__file,utimbuf *__file_times)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_utime_00106cb0)(__file);
  return iVar1;
}



/* Function: _init_array @ 00100e34 */

void _init_array(void)

{
  (*(code *)PTR__init_array_00106cb4)();
  return;
}



/* Function: sigemptyset @ 00100e40 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigemptyset(sigset_t *__set)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigemptyset_00106cb8)(__set);
  return iVar1;
}



/* Function: snprintf @ 00100e4c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int snprintf(char *__s,size_t __maxlen,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_snprintf_00106cbc)(__s);
  return iVar1;
}



/* Function: getpid @ 00100e58 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

__pid_t getpid(void)

{
  __pid_t _Var1;
  
  _Var1 = (*(code *)PTR_getpid_00106cc0)();
  return _Var1;
}



/* Function: _preinit_array @ 00100e64 */

void _preinit_array(void)

{
  (*(code *)PTR__preinit_array_00106cc4)();
  return;
}



/* Function: fgets @ 00100e70 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * fgets(char *__s,int __n,FILE *__stream)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_fgets_00106cc8)(__s);
  return pcVar1;
}



/* Function: memcpy @ 00100e7c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memcpy(void *__dest,void *__src,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memcpy_00106ccc)(__dest);
  return pvVar1;
}



/* Function: readlink @ 00100e88 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

ssize_t readlink(char *__path,char *__buf,size_t __len)

{
  ssize_t sVar1;
  
  sVar1 = (*(code *)PTR_readlink_00106cd0)(__path);
  return sVar1;
}



/* Function: puts @ 00100e94 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int puts(char *__s)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_puts_00106cd4)(__s);
  return iVar1;
}



/* Function: malloc @ 00100ea0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * malloc(size_t __size)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_malloc_00106cd8)(__size);
  return pvVar1;
}



/* Function: confstr @ 00100eac */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t confstr(int __name,char *__buf,size_t __len)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_confstr_00106cdc)(__name);
  return sVar1;
}



/* Function: lchown @ 00100eb8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int lchown(char *__file,__uid_t __owner,__gid_t __group)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_lchown_00106ce0)(__file);
  return iVar1;
}



/* Function: fflush @ 00100ec4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fflush(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fflush_00106ce4)(__stream);
  return iVar1;
}



/* Function: chown @ 00100ed0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int chown(char *__file,__uid_t __owner,__gid_t __group)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_chown_00106ce8)(__file);
  return iVar1;
}



/* Function: chmod @ 00100edc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int chmod(char *__file,__mode_t __mode)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_chmod_00106cec)(__file);
  return iVar1;
}



/* Function: pipe @ 00100ee8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pipe(int *__pipedes)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pipe_00106cf0)(__pipedes);
  return iVar1;
}



/* Function: spawn @ 00100ef4 */

void spawn(void)

{
  (*(code *)PTR_spawn_00106cf4)();
  return;
}



/* Function: rename @ 00100f00 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int rename(char *__old,char *__new)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_rename_00106cf8)(__old);
  return iVar1;
}



/* Function: strrchr @ 00100f0c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strrchr(char *__s,int __c)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strrchr_00106cfc)(__s);
  return pcVar1;
}



/* Function: fprintf @ 00100f18 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fprintf(FILE *__stream,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fprintf_00106d00)(__stream);
  return iVar1;
}



/* Function: pthread_once @ 00100f24 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_once(pthread_once_t *__once_control,__init_routine *__init_routine)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_once_00106d04)(__once_control);
  return iVar1;
}



/* Function: __deregister_frame_info @ 00100f30 */

void __deregister_frame_info(void)

{
  (*(code *)PTR___deregister_frame_info_00106d08)();
  return;
}



/* Function: fseek @ 00100f3c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fseek(FILE *__stream,long __off,int __whence)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fseek_00106d0c)(__stream);
  return iVar1;
}



/* Function: memchr @ 00100f48 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memchr(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memchr_00106d10)(__s);
  return pvVar1;
}



/* Function: __get_errno_ptr @ 00100f54 */

void __get_errno_ptr(void)

{
  (*(code *)PTR___get_errno_ptr_00106d14)();
  return;
}



/* Function: lstat @ 00100f60 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int lstat(char *__file,stat *__buf)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_lstat_00106d18)(__file);
  return iVar1;
}



/* Function: ferror @ 00100f6c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int ferror(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_ferror_00106d1c)(__stream);
  return iVar1;
}



/* Function: strstr @ 00100f78 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strstr(char *__haystack,char *__needle)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strstr_00106d20)(__haystack);
  return pcVar1;
}



/* Function: unlink @ 00100f84 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int unlink(char *__name)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_unlink_00106d24)(__name);
  return iVar1;
}



/* Function: sync @ 00100f90 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void sync(void)

{
  (*(code *)PTR_sync_00106d28)();
  return;
}



/* Function: memcmp @ 00100f9c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int memcmp(void *__s1,void *__s2,size_t __n)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_memcmp_00106d2c)(__s1);
  return iVar1;
}



/* Function: fdopen @ 00100fa8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

FILE * fdopen(int __fd,char *__modes)

{
  FILE *pFVar1;
  
  pFVar1 = (FILE *)(*(code *)PTR_fdopen_00106d30)(__fd);
  return pFVar1;
}



/* Function: sscanf @ 00100fb4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sscanf(char *__s,char *__format,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sscanf_00106d34)(__s);
  return iVar1;
}



/* Function: sigaction @ 00100fc0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int sigaction(int __sig,sigaction *__act,sigaction *__oact)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_sigaction_00106d38)(__sig);
  return iVar1;
}



/* Function: fread @ 00100fcc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t fread(void *__ptr,size_t __size,size_t __n,FILE *__stream)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_fread_00106d3c)(__ptr);
  return sVar1;
}



/* Function: symlink @ 00100fd8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int symlink(char *__from,char *__to)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_symlink_00106d40)(__from);
  return iVar1;
}



/* Function: _init_libc @ 00100fe4 */

void _init_libc(void)

{
  (*(code *)PTR__init_libc_00106d44)();
  return;
}



/* Function: fopen @ 00100ff0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

FILE * fopen(char *__filename,char *__modes)

{
  FILE *pFVar1;
  
  pFVar1 = (FILE *)(*(code *)PTR_fopen_00106d48)(__filename);
  return pFVar1;
}



/* Function: memset @ 00100ffc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void * memset(void *__s,int __c,size_t __n)

{
  void *pvVar1;
  
  pvVar1 = (void *)(*(code *)PTR_memset_00106d4c)(__s);
  return pvVar1;
}



/* Function: ftell @ 00101008 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

long ftell(FILE *__stream)

{
  long lVar1;
  
  lVar1 = (*(code *)PTR_ftell_00106d50)(__stream);
  return lVar1;
}



/* Function: fclose @ 00101014 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fclose(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fclose_00106d54)(__stream);
  return iVar1;
}



/* Function: time @ 00101020 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

time_t time(time_t *__timer)

{
  time_t tVar1;
  
  tVar1 = (*(code *)PTR_time_00106d58)(__timer);
  return tVar1;
}



/* Function: _fini_array @ 0010102c */

void _fini_array(void)

{
  (*(code *)PTR__fini_array_00106d5c)();
  return;
}



/* Function: strcmp @ 00101038 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int strcmp(char *__s1,char *__s2)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_strcmp_00106d60)(__s1);
  return iVar1;
}



/* Function: pthread_mutex_unlock @ 00101044 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_unlock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_unlock_00106d64)(__mutex);
  return iVar1;
}



/* Function: pathfind_r @ 00101050 */

void pathfind_r(void)

{
  (*(code *)PTR_pathfind_r_00106d68)();
  return;
}



/* Function: fgetc @ 0010105c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fgetc(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fgetc_00106d6c)(__stream);
  return iVar1;
}



/* Function: atexit @ 00101068 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int atexit(__func *__func)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_atexit_00106d70)(__func);
  return iVar1;
}



/* Function: fsync @ 00101074 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fsync(int __fd)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fsync_00106d74)(__fd);
  return iVar1;
}



/* Function: fwide @ 00101080 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fwide(__FILE *__fp,int __mode)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fwide_00106d78)(__fp);
  return iVar1;
}



/* Function: stat @ 0010108c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int stat(char *__file,stat *__buf)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_stat_00106d7c)(__file);
  return iVar1;
}



/* Function: fwrite @ 00101098 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t fwrite(void *__ptr,size_t __size,size_t __n,FILE *__s)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_fwrite_00106d80)(__ptr);
  return sVar1;
}



/* Function: access @ 001010a4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int access(char *__name,int __type)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_access_00106d84)(__name);
  return iVar1;
}



/* Function: pthread_mutex_lock @ 001010b0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_lock(pthread_mutex_t *__mutex)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_lock_00106d88)(__mutex);
  return iVar1;
}



/* Function: exit @ 001010bc */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void exit(int __status)

{
  (*(code *)PTR_exit_00106d8c)(__status);
  return;
}



/* Function: pthread_atfork @ 001010c8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_atfork(__prepare *__prepare,__parent *__parent,__child *__child)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_atfork_00106d90)(__prepare);
  return iVar1;
}



/* Function: atoi @ 001010d4 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int atoi(char *__nptr)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_atoi_00106d94)(__nptr);
  return iVar1;
}



/* Function: fileno @ 001010e0 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fileno(FILE *__stream)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fileno_00106d98)(__stream);
  return iVar1;
}



/* Function: pthread_mutex_init @ 001010ec */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int pthread_mutex_init(pthread_mutex_t *__mutex,pthread_mutexattr_t *__mutexattr)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_pthread_mutex_init_00106d9c)(__mutex);
  return iVar1;
}



/* Function: strlen @ 001010f8 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

size_t strlen(char *__s)

{
  size_t sVar1;
  
  sVar1 = (*(code *)PTR_strlen_00106da0)(__s);
  return sVar1;
}



/* Function: open @ 00101104 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int open(char *__file,int __oflag,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_open_00106da4)(__file);
  return iVar1;
}



/* Function: strchr @ 00101110 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

char * strchr(char *__s,int __c)

{
  char *pcVar1;
  
  pcVar1 = (char *)(*(code *)PTR_strchr_00106da8)(__s);
  return pcVar1;
}



/* Function: fcntl @ 0010111c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int fcntl(int __fd,int __cmd,...)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_fcntl_00106dac)(__fd);
  return iVar1;
}



/* Function: mkdir @ 00101128 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int mkdir(char *__path,__mode_t __mode)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_mkdir_00106db0)(__path);
  return iVar1;
}



/* Function: __register_frame_info @ 00101134 */

void __register_frame_info(void)

{
  (*(code *)PTR___register_frame_info_00106db4)();
  return;
}



/* Function: close @ 00101140 */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

int close(int __fd)

{
  int iVar1;
  
  iVar1 = (*(code *)PTR_close_00106db8)(__fd);
  return iVar1;
}



/* Function: free @ 0010114c */

/* WARNING: Unknown calling convention -- yet parameter storage is locked */

void free(void *__ptr)

{
  (*(code *)PTR_free_00106dbc)(__ptr);
  return;
}



/* Function: entry @ 00101158 */

void processEntry entry(undefined4 param_1,int param_2)

{
  int *piVar1;
  int iVar2;
  
  piVar1 = (int *)(&stack0x00000008 + param_2 * 4);
  do {
    iVar2 = *piVar1;
    piVar1 = piVar1 + 1;
  } while (iVar2 != 0);
  (*DAT_00101228)();
  (*DAT_0010122c)(DAT_00101248,DAT_0010124c);
  (*DAT_00101230)(DAT_00101250);
  (*DAT_00101234)(DAT_00101254,DAT_00101258);
  (*DAT_00101238)();
  (*DAT_0010123c)(DAT_0010125c,DAT_00101260);
  *DAT_00101264 = 0;
  (*DAT_00101240)(param_2,&stack0x00000004,&stack0x00000008 + param_2 * 4);
  (*DAT_00101244)();
  return;
}



/* Function: FUN_00101268 @ 00101268 */

/* WARNING: Removing unreachable block (ram,0x00101290) */

void FUN_00101268(void)

{
  if (6 < DAT_00101294 - 0x106e14U) {
    return;
  }
  return;
}



/* Function: _FINI_0 @ 001012d0 */

/* WARNING: Removing unreachable block (ram,0x001012fc) */

void _FINI_0(void)

{
  if (DAT_00106eb8 == '\0') {
    FUN_00101268();
    DAT_00106eb8 = 1;
    return;
  }
  return;
}



/* Function: _INIT_0 @ 00101314 */

/* WARNING: Removing unreachable block (ram,0x001012bc) */
/* WARNING: Removing unreachable block (ram,0x001012c8) */
/* WARNING: Removing unreachable block (ram,0x00101328) */
/* WARNING: Removing unreachable block (ram,0x00101368) */
/* WARNING: Removing unreachable block (ram,0x001012cc) */

void _INIT_0(void)

{
  return;
}



/* Function: FUN_00101370 @ 00101370 */

void FUN_00101370(uint *param_1,int param_2)

{
  uint uVar1;
  uint uVar2;
  byte *pbVar3;
  uint uVar4;
  uint uVar5;
  int iVar6;
  uint *puVar7;
  uint uVar8;
  uint *puVar9;
  uint uVar10;
  uint *puVar11;
  uint uVar12;
  uint uVar13;
  uint uVar14;
  uint uVar15;
  uint uVar16;
  uint uVar17;
  uint *puVar18;
  int iVar19;
  uint local_12c;
  uint local_128 [65];
  
  puVar18 = &local_12c;
  pbVar3 = (byte *)(param_2 + 1);
  do {
    puVar18 = puVar18 + 1;
    *puVar18 = (uint)*pbVar3 << 0x10 | (uint)pbVar3[-1] << 0x18 | (uint)pbVar3[2] |
               (uint)pbVar3[1] << 8;
    pbVar3 = pbVar3 + 4;
  } while (puVar18 != local_128 + 0xf);
  puVar11 = local_128 + 0xd;
  puVar9 = local_128 + 8;
  iVar19 = 0x30;
  puVar7 = local_128;
  do {
    puVar11 = puVar11 + 1;
    uVar5 = *puVar11;
    iVar19 = iVar19 + -1;
    uVar13 = puVar7[1];
    puVar9 = puVar9 + 1;
    puVar18 = puVar18 + 1;
    *puVar18 = ((uVar5 >> 0x11 | uVar5 << 0xf) ^ (uVar5 >> 0x13 | uVar5 << 0xd) ^ uVar5 >> 10) +
               *puVar9 + *puVar7 +
               ((uVar13 >> 7 | uVar13 << 0x19) ^ (uVar13 >> 0x12 | uVar13 << 0xe) ^ uVar13 >> 3);
    puVar7 = puVar7 + 1;
  } while (iVar19 != 0);
  iVar19 = 0;
  uVar5 = param_1[7];
  uVar13 = param_1[4];
  uVar4 = *param_1;
  uVar1 = param_1[2];
  uVar10 = param_1[1];
  uVar2 = param_1[6];
  uVar8 = param_1[5];
  uVar17 = param_1[3];
  do {
    uVar16 = uVar8;
    uVar15 = uVar2;
    uVar14 = uVar10;
    uVar12 = uVar1;
    uVar10 = uVar4;
    uVar8 = uVar13;
    iVar6 = iVar19 * 4;
    puVar18 = local_128 + iVar19;
    iVar19 = iVar19 + 1;
    iVar6 = *(int *)(&UNK_00104ab0 + iVar6) + *puVar18 +
            ((uVar8 >> 6 | uVar8 << 0x1a) ^ (uVar8 >> 0xb | uVar8 << 0x15) ^
            (uVar8 >> 0x19 | uVar8 << 7)) + (uVar15 & ~uVar8 ^ uVar16 & uVar8) + uVar5;
    uVar13 = uVar17 + iVar6;
    uVar4 = ((uVar12 ^ uVar14) & uVar10 ^ uVar12 & uVar14) +
            ((uVar10 >> 2 | uVar10 << 0x1e) ^ (uVar10 >> 0xd | uVar10 << 0x13) ^
            (uVar10 >> 0x16 | uVar10 << 10)) + iVar6;
    uVar5 = uVar15;
    uVar1 = uVar14;
    uVar2 = uVar16;
    uVar17 = uVar12;
  } while (iVar19 != 0x40);
  *param_1 = uVar4 + *param_1;
  param_1[1] = uVar10 + param_1[1];
  param_1[2] = uVar14 + param_1[2];
  param_1[3] = uVar12 + param_1[3];
  param_1[4] = uVar13 + param_1[4];
  param_1[5] = uVar8 + param_1[5];
  param_1[6] = uVar16 + param_1[6];
  param_1[7] = uVar15 + param_1[7];
  return;
}



/* Function: FUN_00101570 @ 00101570 */

void FUN_00101570(int param_1,char *param_2)

{
  byte *pbVar1;
  char *pcVar2;
  char *pcVar3;
  
  pbVar1 = (byte *)(param_1 + -1);
  pcVar2 = param_2;
  do {
    pbVar1 = pbVar1 + 1;
    pcVar3 = pcVar2 + 2;
    *pcVar2 = "0123456789abcdef"[*pbVar1 >> 4];
    pcVar2[1] = "0123456789abcdef"[*pbVar1 & 0xf];
    pcVar2 = pcVar3;
  } while (pcVar3 != param_2 + 0x40);
  param_2[0x40] = '\0';
  return;
}



/* Function: FUN_001015bc @ 001015bc */

void FUN_001015bc(int param_1,int param_2,undefined4 param_3,undefined4 param_4)

{
  void *__s;
  undefined1 *puVar1;
  uint uVar2;
  int iVar3;
  size_t __n;
  uint uVar4;
  uint uVar5;
  undefined4 *puVar6;
  uint uVar8;
  uint uVar9;
  uint uVar10;
  undefined4 *puVar7;
  
  iVar3 = *(int *)(param_1 + 0x68);
  *(uint *)(param_1 + 0x68) = iVar3 + 1U;
  *(undefined1 *)(param_1 + iVar3 + 0x28) = 0x80;
  if (iVar3 + 1U < 0x39) {
    __n = 0x37 - iVar3;
    __s = (void *)(param_1 + iVar3 + 0x29);
  }
  else {
    __s = (void *)(param_1 + 0x28);
    memset((void *)(param_1 + iVar3 + 0x29),0,0x3f - iVar3);
    FUN_00101370(param_1,__s);
    __n = 0x38;
    *(undefined4 *)(param_1 + 0x68) = 0;
  }
  memset(__s,0,__n);
  uVar8 = *(uint *)(param_1 + 0x20);
  uVar9 = *(uint *)(param_1 + 0x24);
  puVar1 = (undefined1 *)(param_1 + 0x68);
  uVar5 = 0;
  do {
    uVar2 = 0x20 - uVar5;
    uVar4 = uVar5 & 0xff;
    uVar10 = uVar5 - 0x20;
    uVar5 = uVar5 + 8;
    uVar4 = uVar8 >> uVar4 | uVar9 << (uVar2 & 0xff) | uVar9 >> (uVar10 & 0xff);
    puVar1 = puVar1 + -1;
    *puVar1 = (char)uVar4;
  } while (uVar5 != 0x40);
  FUN_00101370(param_1,param_1 + 0x28,uVar4,0x40,param_4);
  puVar1 = (undefined1 *)(param_2 + 1);
  puVar6 = (undefined4 *)(param_1 + -4);
  do {
    puVar7 = puVar6 + 1;
    puVar1[-1] = (char)((uint)*puVar7 >> 0x18);
    *puVar1 = (char)*(undefined2 *)((int)puVar6 + 6);
    puVar1[1] = (char)((uint)*puVar7 >> 8);
    puVar1[2] = (char)*puVar7;
    puVar1 = puVar1 + 4;
    puVar6 = puVar7;
  } while (puVar7 != (undefined4 *)(param_1 + 0x1c));
  return;
}



/* Function: FUN_001016b8 @ 001016b8 */

void FUN_001016b8(int param_1,void *param_2,uint param_3)

{
  uint uVar1;
  uint __n;
  int iVar2;
  
  *(ulonglong *)(param_1 + 0x20) = (ulonglong)param_3 * 8 + *(longlong *)(param_1 + 0x20);
  if (param_3 == 0) {
    return;
  }
  do {
    while( true ) {
      uVar1 = 0x40 - *(int *)(param_1 + 0x68);
      __n = param_3;
      if (uVar1 <= param_3) {
        __n = uVar1;
      }
      param_3 = param_3 - __n;
      memcpy((void *)(param_1 + *(int *)(param_1 + 0x68) + 0x28),param_2,__n);
      param_2 = (void *)((int)param_2 + __n);
      iVar2 = __n + *(int *)(param_1 + 0x68);
      *(int *)(param_1 + 0x68) = iVar2;
      if (iVar2 == 0x40) break;
      if (param_3 == 0) {
        return;
      }
    }
    FUN_00101370(param_1,param_1 + 0x28);
    *(undefined4 *)(param_1 + 0x68) = 0;
  } while (param_3 != 0);
  return;
}



/* Function: FUN_00101754 @ 00101754 */

undefined4 FUN_00101754(undefined4 param_1,undefined4 param_2)

{
  undefined4 *puVar1;
  
  puVar1 = (undefined4 *)__get_errno_ptr();
  fprintf((FILE *)_Stderr,"MIBSI: %s: %s (errno=%d)\n",param_1,param_2,*puVar1);
  return 0xffffffff;
}



/* Function: FUN_00101798 @ 00101798 */

undefined4 FUN_00101798(byte *param_1)

{
  size_t sVar1;
  byte *pbVar2;
  byte *pbVar3;
  
  sVar1 = strlen((char *)param_1);
  if (sVar1 - 0xc < 0x35) {
    pbVar3 = param_1;
    while( true ) {
      pbVar2 = pbVar3 + 1;
      if ((9 < *pbVar3 - 0x30) && (5 < (*pbVar3 & 0xffffffdf) - 0x41)) break;
      pbVar3 = pbVar2;
      if (pbVar2 == param_1 + sVar1) {
        return 1;
      }
    }
  }
  return 0;
}



/* Function: FUN_001017f0 @ 001017f0 */

undefined4 FUN_001017f0(void *param_1,char *param_2)

{
  size_t sVar1;
  
  sVar1 = strlen(param_2);
  if (sVar1 < 0x400) {
    memcpy(param_1,param_2,sVar1 + 1);
    return 0;
  }
  return 0xffffffff;
}



/* Function: FUN_00101830 @ 00101830 */

undefined4 FUN_00101830(char *param_1,__mode_t param_2)

{
  char cVar1;
  size_t sVar2;
  int iVar3;
  int *piVar4;
  undefined4 uVar5;
  timespec *__dest;
  stat asStack_460 [12];
  
  sVar2 = strlen(param_1);
  if (sVar2 < 0x400) {
    __dest = &asStack_460[0].st_ctim;
    memcpy(__dest,param_1,sVar2 + 1);
    do {
      do {
        __dest = (timespec *)((int)&__dest->tv_sec + 1);
        cVar1 = (char)__dest->tv_sec;
      } while (cVar1 != '\0' && cVar1 != '/');
      *(undefined1 *)&__dest->tv_sec = 0;
      iVar3 = mkdir((char *)&asStack_460[0].st_ctim,param_2);
      if ((iVar3 != 0) && (piVar4 = (int *)__get_errno_ptr(), *piVar4 != 0x11)) {
        uVar5 = FUN_00101754("mkdir",&asStack_460[0].st_ctim);
        return uVar5;
      }
      iVar3 = stat((char *)&asStack_460[0].st_ctim,asStack_460);
      if ((iVar3 != 0) || ((asStack_460[0].st_size & 0xf000U) != 0x4000)) goto LAB_001018e4;
      *(char *)&__dest->tv_sec = cVar1;
      uVar5 = 0;
    } while (cVar1 != '\0');
  }
  else {
LAB_001018e4:
    uVar5 = 0xffffffff;
  }
  return uVar5;
}



/* Function: FUN_00101900 @ 00101900 */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 FUN_00101900(char *param_1,undefined4 param_2)

{
  FILE *__stream;
  size_t sVar1;
  int iVar2;
  undefined4 local_4090;
  undefined4 uStack_408c;
  undefined4 uStack_4088;
  undefined4 uStack_4084;
  undefined4 local_4080;
  undefined4 uStack_407c;
  undefined4 uStack_4078;
  undefined4 uStack_4074;
  undefined4 local_4070;
  undefined4 uStack_406c;
  undefined4 local_4028;
  undefined1 auStack_4020 [16388];
  
  __stream = fopen(param_1,"rb");
  if (__stream != (FILE *)0x0) {
    local_4028 = 0;
    local_4090 = DAT_00105adc;
    uStack_408c = DAT_00105ae0;
    uStack_4088 = DAT_00105ae4;
    uStack_4084 = DAT_00105ae8;
    local_4080 = DAT_00105aec;
    uStack_407c = DAT_00105af0;
    uStack_4078 = DAT_00105af4;
    uStack_4074 = _DAT_00105af8;
    local_4070 = 0;
    uStack_406c = 0;
    while( true ) {
      sVar1 = fread(auStack_4020,1,0x4000,__stream);
      if (sVar1 == 0) break;
      FUN_001016b8(&local_4090,auStack_4020);
    }
    iVar2 = ferror(__stream);
    if (iVar2 != 0) {
      fclose(__stream);
      return 0xffffffff;
    }
    iVar2 = fclose(__stream);
    if (iVar2 == 0) {
      FUN_001015bc(&local_4090,param_2);
      return 0;
    }
  }
  return 0xffffffff;
}



/* Function: FUN_001019d4 @ 001019d4 */

bool FUN_001019d4(char *param_1,undefined4 param_2)

{
  byte bVar1;
  size_t sVar2;
  int iVar3;
  byte *pbVar4;
  byte *pbVar5;
  byte bVar6;
  uint uVar7;
  uint uVar8;
  uint uVar9;
  byte abStack_50 [32];
  undefined1 auStack_30 [32];
  
  sVar2 = strlen(param_1);
  if (sVar2 == 0x40) {
    pbVar5 = (byte *)(param_1 + 1);
    pbVar4 = abStack_50;
    do {
      uVar9 = (uint)pbVar5[-1];
      bVar1 = *pbVar5;
      uVar7 = (uint)bVar1;
      uVar8 = uVar9 - 0x30;
      if (9 < (uVar8 & 0xff)) {
        if (uVar9 - 0x61 < 6) {
          uVar8 = uVar9 - 0x57;
        }
        else {
          if (5 < uVar9 - 0x41) {
            return true;
          }
          uVar8 = uVar9 - 0x37;
        }
      }
      bVar6 = (byte)(uVar7 - 0x30);
      if (9 < (uVar7 - 0x30 & 0xff)) {
        if (uVar7 - 0x61 < 6) {
          bVar6 = bVar1 + 0xa9;
        }
        else {
          if (5 < uVar7 - 0x41) {
            return true;
          }
          bVar6 = bVar1 - 0x37;
        }
      }
      pbVar5 = pbVar5 + 2;
      *pbVar4 = bVar6 | (byte)(uVar8 << 4);
      pbVar4 = pbVar4 + 1;
    } while (pbVar5 != (byte *)(param_1 + 0x41));
    iVar3 = FUN_00101900(param_2,auStack_30);
    if (iVar3 == 0) {
      iVar3 = memcmp(abStack_50,auStack_30,0x20);
      return iVar3 != 0;
    }
  }
  return true;
}



/* Function: FUN_00101ab8 @ 00101ab8 */

void * FUN_00101ab8(char *param_1,size_t *param_2)

{
  FILE *__stream;
  int iVar1;
  uint __n;
  void *__ptr;
  size_t sVar2;
  
  __stream = fopen(param_1,"rb");
  if (__stream == (FILE *)0x0) {
    return (void *)0x0;
  }
  iVar1 = fseek(__stream,0,2);
  if (((iVar1 != 0) || (__n = ftell(__stream), 0x100000 < __n)) ||
     (iVar1 = fseek(__stream,0,0), iVar1 != 0)) {
    fclose(__stream);
    return (void *)0x0;
  }
  __ptr = malloc(__n + 1);
  if (__ptr == (void *)0x0) {
    fclose(__stream);
    return (void *)0x0;
  }
  sVar2 = fread(__ptr,1,__n,__stream);
  if ((sVar2 == __n) && (iVar1 = fclose(__stream), iVar1 == 0)) {
    *(undefined1 *)((int)__ptr + sVar2) = 0;
    *param_2 = sVar2;
    return __ptr;
  }
  free(__ptr);
  return (void *)0x0;
}



/* Function: FUN_00101b98 @ 00101b98 */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

bool FUN_00101b98(char *param_1,char *param_2)

{
  char cVar1;
  int *piVar2;
  int iVar3;
  ssize_t sVar4;
  bool bVar5;
  undefined1 auStack_534 [32];
  char acStack_514 [68];
  stat sStack_4d0;
  undefined4 local_478;
  undefined4 uStack_474;
  undefined4 uStack_470;
  undefined4 uStack_46c;
  undefined4 local_468;
  undefined4 uStack_464;
  int local_420;
  char acStack_418 [1024];
  
  local_420 = lstat(param_1,&sStack_4d0);
  if (local_420 == 0) {
    cVar1 = *param_2;
    bVar5 = false;
    if (((cVar1 != 'N') && (sStack_4d0.st_uid == *(__uid_t *)(param_2 + 8))) &&
       (sStack_4d0.st_gid == *(__gid_t *)(param_2 + 0xc))) {
      if (cVar1 == 'F') {
        if (((sStack_4d0.st_size & 0xf000U) == 0x8000) &&
           ((sStack_4d0.st_size & 0xfffU) == *(uint *)(param_2 + 4))) {
          iVar3 = FUN_001019d4(param_2 + 0x20,param_1);
          bVar5 = iVar3 == 0;
        }
      }
      else if (((cVar1 == 'L') && ((sStack_4d0.st_size & 0xf000U) == 0xa000)) &&
              (sVar4 = readlink(param_1,acStack_418,0x400), sVar4 - 1U < 0x3ff)) {
        sStack_4d0.st_ctim.tv_sec = DAT_00105adc;
        sStack_4d0.st_ctim.tv_nsec = DAT_00105ae0;
        sStack_4d0.__unused4 = DAT_00105ae4;
        sStack_4d0.__unused5 = DAT_00105ae8;
        local_478 = DAT_00105aec;
        uStack_474 = DAT_00105af0;
        uStack_470 = DAT_00105af4;
        uStack_46c = _DAT_00105af8;
        local_468 = 0;
        uStack_464 = 0;
        FUN_001016b8(&sStack_4d0.st_ctim,acStack_418,sVar4);
        FUN_001015bc(&sStack_4d0.st_ctim,auStack_534);
        FUN_00101570(auStack_534,acStack_514);
        iVar3 = strcmp(acStack_514,param_2 + 0x20);
        bVar5 = iVar3 == 0;
      }
    }
  }
  else {
    piVar2 = (int *)__get_errno_ptr();
    if (*piVar2 == 2) {
      bVar5 = *param_2 == 'N';
    }
    else {
      bVar5 = false;
    }
  }
  return bVar5;
}



/* Function: FUN_00101cf8 @ 00101cf8 */

undefined4 FUN_00101cf8(char *param_1,char *param_2)

{
  FILE *__stream;
  undefined4 uVar1;
  FILE *__s;
  size_t __n;
  size_t sVar2;
  int iVar3;
  undefined1 auStack_4010 [16384];
  
  __stream = fopen(param_1,"rb");
  if (__stream == (FILE *)0x0) {
    uVar1 = FUN_00101754(&DAT_00104d20,param_1);
  }
  else {
    __s = fopen(param_2,"wb");
    if (__s == (FILE *)0x0) {
      fclose(__stream);
      uVar1 = FUN_00101754(&DAT_00104d2c,param_2);
    }
    else {
      do {
        __n = fread(auStack_4010,1,0x4000,__stream);
        uVar1 = 0;
        if (__n == 0) goto LAB_00101da0;
        sVar2 = fwrite(auStack_4010,1,__n,__s);
      } while (sVar2 == __n);
      uVar1 = 0xffffffff;
LAB_00101da0:
      iVar3 = ferror(__stream);
      if ((iVar3 == 0) && (iVar3 = fflush(__s), iVar3 == 0)) {
        iVar3 = fileno(__s);
        iVar3 = fsync(iVar3);
        if (iVar3 != 0) {
          uVar1 = 0xffffffff;
        }
      }
      else {
        uVar1 = 0xffffffff;
      }
      iVar3 = fclose(__stream);
      if (iVar3 != 0) {
        uVar1 = 0xffffffff;
      }
      iVar3 = fclose(__s);
      if (iVar3 != 0) {
        uVar1 = 0xffffffff;
      }
    }
  }
  return uVar1;
}



/* Function: FUN_00101e08 @ 00101e08 */

int FUN_00101e08(char *param_1,char *param_2)

{
  size_t sVar1;
  char *pcVar2;
  int iVar3;
  
  sVar1 = strlen(param_2);
  iVar3 = 0;
  while( true ) {
    pcVar2 = strstr(param_1,param_2);
    param_1 = pcVar2 + sVar1;
    if (pcVar2 == (char *)0x0) break;
    iVar3 = iVar3 + 1;
  }
  return iVar3;
}



/* Function: FUN_00101e4c @ 00101e4c */

undefined4 FUN_00101e4c(FILE *param_1,char *param_2,char *param_3,char *param_4,undefined4 *param_5)

{
  char *pcVar1;
  size_t sVar2;
  size_t sVar3;
  
  pcVar1 = strstr(param_2,param_3);
  if ((pcVar1 != (char *)0x0) &&
     (sVar2 = fwrite(param_2,1,(int)pcVar1 - (int)param_2,param_1),
     sVar2 == (int)pcVar1 - (int)param_2)) {
    sVar2 = strlen(param_4);
    sVar2 = fwrite(param_4,1,sVar2,param_1);
    sVar3 = strlen(param_4);
    if (sVar2 == sVar3) {
      sVar2 = strlen(param_3);
      *param_5 = pcVar1 + sVar2;
      return 0;
    }
  }
  return 0xffffffff;
}



/* Function: FUN_00101eec @ 00101eec */

char * FUN_00101eec(char *param_1,int *param_2)

{
  byte bVar1;
  char *pcVar2;
  char cVar3;
  char *pcVar4;
  
  pcVar4 = (char *)0x0;
  *param_2 = 0;
  cVar3 = *param_1;
  if (cVar3 == '\0') {
    pcVar4 = (char *)0x0;
  }
  else {
    do {
      pcVar2 = param_1;
      if (cVar3 == ' ' || cVar3 == '\t') {
        do {
          pcVar2 = pcVar2 + 1;
          cVar3 = *pcVar2;
        } while (cVar3 == ' ' || cVar3 == '\t');
      }
      if ((((cVar3 == '$') && (pcVar2[1] == 'J')) && (pcVar2[2] == '9')) &&
         (bVar1 = pcVar2[3], bVar1 == 0xd || (bVar1 - 9 < 2 || (bVar1 & 0xdf) == 0))) {
        *param_2 = *param_2 + 1;
        pcVar4 = param_1;
      }
      param_1 = strchr(param_1,10);
      if (param_1 == (char *)0x0) {
        return pcVar4;
      }
      cVar3 = param_1[1];
      param_1 = param_1 + 1;
    } while (cVar3 != '\0');
  }
  return pcVar4;
}



/* Function: FUN_00101fb4 @ 00101fb4 */

undefined4 FUN_00101fb4(char *param_1)

{
  int iVar1;
  char *pcVar2;
  int local_14 [2];
  
  FUN_00101eec(param_1,local_14);
  iVar1 = FUN_00101e08(param_1,"MIBSI_JAR_BOOTSTRAP_V1");
  if (iVar1 == 1) {
    iVar1 = FUN_00101e08(param_1,"002_mhi2q_aa_navigation_proxy.jar");
    if (iVar1 == 1) {
      return 0;
    }
    return 2;
  }
  if (iVar1 == 0) {
    pcVar2 = strstr(param_1,"Find and append jar files");
    if ((((pcVar2 != (char *)0x0) && (pcVar2 = strstr(param_1,"lsd/jars"), pcVar2 != (char *)0x0))
        && (pcVar2 = strstr(param_1,"for jar in $JARS"), pcVar2 != (char *)0x0)) &&
       (pcVar2 = strstr(param_1,"BOOTCLASSPATH=\"$BOOTCLASSPATH:$jar\""), pcVar2 != (char *)0x0)) {
      return 0;
    }
    if (((local_14[0] == 1) && (pcVar2 = strstr(param_1,"J9="), pcVar2 != (char *)0x0)) &&
       (pcVar2 = strstr(param_1,"BOOTCLASSPATH"), pcVar2 != (char *)0x0)) {
      pcVar2 = strstr(param_1,"BASE_DIR=");
      if (pcVar2 != (char *)0x0) {
        return 10;
      }
      return 2;
    }
  }
  return 2;
}



/* Function: FUN_001020d8 @ 001020d8 */

undefined4 FUN_001020d8(undefined4 param_1)

{
  void *__ptr;
  undefined4 uVar1;
  undefined1 auStack_14 [8];
  
  __ptr = (void *)FUN_00101ab8(param_1,auStack_14);
  if (__ptr == (void *)0x0) {
    uVar1 = 3;
  }
  else {
    uVar1 = FUN_00101fb4(__ptr);
    free(__ptr);
  }
  return uVar1;
}



/* Function: FUN_00102110 @ 00102110 */

bool FUN_00102110(int param_1)

{
  if (0x19 < param_1 - 0x41U || (param_1 - 0x49U & 0xf7) == 0) {
    return false;
  }
  return param_1 != 0x4f;
}



/* Function: FUN_0010214c @ 0010214c */

bool FUN_0010214c(uint param_1)

{
  int iVar1;
  
  if (param_1 - 0x30 < 10) {
    return true;
  }
  iVar1 = FUN_00102110();
  if ((iVar1 == 0) && (0x19 < param_1 - 0x61)) {
    return (param_1 & 0xef) == 0x4f || (param_1 - 0x49 & 0xf7) == 0;
  }
  return true;
}



/* Function: FUN_001021a8 @ 001021a8 */

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 FUN_001021a8(void)

{
  byte *pbVar1;
  bool bVar2;
  byte bVar3;
  undefined4 uVar4;
  int iVar5;
  undefined4 *puVar6;
  FILE *__stream;
  int iVar7;
  void *pvVar8;
  byte bVar9;
  int iVar10;
  char *pcVar11;
  undefined4 uVar12;
  uint uVar13;
  uint uVar14;
  bool bVar15;
  byte *pbVar16;
  size_t __n;
  undefined1 auStack_2114 [32];
  undefined1 auStack_20f4 [20];
  byte local_20e0 [32];
  byte local_20c0 [32];
  ulong local_20a0;
  ulong uStack_209c;
  ulong uStack_2098;
  ulong uStack_2094;
  int local_2090;
  _func_4849 *p_Stack_208c;
  undefined4 uStack_2088;
  undefined4 uStack_2084;
  undefined4 local_2080;
  undefined4 uStack_207c;
  undefined4 local_2038;
  byte abStack_202d [18];
  undefined1 auStack_201b [8183];
  
  pbVar16 = local_20e0 + 0x1f;
  bVar3 = 0;
  iVar5 = 0;
  bVar9 = 0xa7;
  do {
    iVar10 = iVar5 + 1;
    bVar3 = bVar3 | (&DAT_00106dd8)[iVar5];
    pbVar16 = pbVar16 + 1;
    *pbVar16 = (&DAT_00106dd8)[iVar5] ^ bVar9;
    iVar5 = iVar10;
    bVar9 = bVar9 + 0x1d;
  } while (iVar10 != 0x20);
  if (bVar3 == 0) {
LAB_00102200:
    uVar4 = 0;
  }
  else {
    memset(auStack_2114,0,0x10);
    sigemptyset((sigset_t *)(auStack_2114 + 8));
    iVar5 = sigaction(0x12,(sigaction *)auStack_2114,(sigaction *)(auStack_2114 + 0x10));
    if (iVar5 == 0) {
      __stream = (FILE *)FUN_00104770("IPL_CONFIG_DIR=/etc/eso/production /eso/bin/apps/pc s:1501:30 2>/dev/null"
                                      ,"r");
      if (__stream == (FILE *)0x0) {
        puVar6 = (undefined4 *)__get_errno_ptr();
        uVar4 = *puVar6;
        sigaction(0x12,(sigaction *)(auStack_2114 + 0x10),(sigaction *)0x0);
        fprintf((FILE *)_Stderr,
                "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                "reader_start",0x37,0,0,uVar4);
        return 0x37;
      }
      __n = fread(abStack_202d + 1,1,0x2001,__stream);
      iVar5 = ferror(__stream);
      if (iVar5 == 0) {
        uVar4 = 0;
      }
      else {
        puVar6 = (undefined4 *)__get_errno_ptr();
        uVar4 = *puVar6;
      }
      iVar10 = FUN_0010465c(__stream);
      if (iVar10 < 0) {
        puVar6 = (undefined4 *)__get_errno_ptr();
        uVar12 = *puVar6;
      }
      else {
        uVar12 = 0;
      }
      iVar7 = sigaction(0x12,(sigaction *)(auStack_2114 + 0x10),(sigaction *)0x0);
      if (iVar7 == 0) {
        if (__n == 0x2001) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "output_limit",0x37,iVar10,0x2001,uVar12);
          return 0x37;
        }
        if (iVar5 != 0) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "reader_read",0x37,iVar10,__n,uVar4);
          return 0x37;
        }
        if (iVar10 != 0) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "reader_exit",0x37,iVar10,__n,uVar12);
          return 0x37;
        }
        pvVar8 = memchr(abStack_202d + 1,0,__n);
        if (pvVar8 != (void *)0x0) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "binary_output",0x37,0,__n,0);
          return 0x37;
        }
        bVar15 = false;
        for (iVar5 = 0; iVar5 + 0x11U <= __n; iVar5 = iVar5 + 1) {
          if (((iVar5 == 0) || (iVar10 = FUN_0010214c(abStack_202d[iVar5]), iVar10 == 0)) &&
             ((__n <= iVar5 + 0x11U || (iVar10 = FUN_0010214c(auStack_201b[iVar5]), iVar10 == 0))))
          {
            bVar2 = false;
            pbVar16 = abStack_202d + iVar5 + 1;
            uVar14 = 0;
            do {
              uVar13 = (uint)pbVar16[uVar14];
              if ((9 < uVar13 - 0x30) &&
                 ((iVar10 = FUN_00102110(uVar13), iVar10 == 0 || (0xc < uVar14))))
              goto LAB_00102484;
              uVar14 = uVar14 + 1;
              if (uVar13 - 0x41 < 0x1a) {
                bVar2 = true;
              }
            } while (uVar14 != 0x11);
            if (bVar2) {
              if ((bVar15) && (iVar10 = memcmp(auStack_20f4,pbVar16,0x11), iVar10 != 0)) {
                fprintf((FILE *)_Stderr,
                        "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n"
                        ,"ambiguous",0x37,0,__n,0);
                return 0x37;
              }
              iVar5 = iVar5 + 0x10;
              memcpy(auStack_20f4,pbVar16,0x11);
              bVar15 = true;
            }
          }
LAB_00102484:
        }
        if (!bVar15) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "no_candidate",0x37,0,__n,0);
          return 0x37;
        }
        local_2038 = 0;
        local_20a0 = DAT_00105adc;
        uStack_209c = DAT_00105ae0;
        uStack_2098 = DAT_00105ae4;
        uStack_2094 = DAT_00105ae8;
        local_2090 = DAT_00105aec;
        p_Stack_208c = DAT_00105af0;
        uStack_2088 = DAT_00105af4;
        uStack_2084 = _DAT_00105af8;
        local_2080 = 0;
        uStack_207c = 0;
        FUN_001016b8(&local_20a0,auStack_20f4,0x11);
        FUN_001015bc(&local_20a0,local_20e0);
        iVar5 = 0;
        bVar9 = 0;
        do {
          pbVar16 = local_20c0 + iVar5;
          pbVar1 = local_20e0 + iVar5;
          iVar5 = iVar5 + 1;
          bVar9 = bVar9 | *pbVar1 ^ *pbVar16;
        } while (iVar5 != 0x20);
        if (bVar9 != 0) {
          fprintf((FILE *)_Stderr,
                  "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",
                  "mismatch",0x38,0,__n,0);
          return 0x38;
        }
        goto LAB_00102200;
      }
      puVar6 = (undefined4 *)__get_errno_ptr();
      pcVar11 = "child_restore";
    }
    else {
      puVar6 = (undefined4 *)__get_errno_ptr();
      pcVar11 = "child_setup";
      iVar10 = 0;
      __n = 0;
    }
    fprintf((FILE *)_Stderr,
            "MIBSI: VIN check failed stage=%s code=%d reader_status=%d bytes=%lu errno=%d\n",pcVar11
            ,0x37,iVar10,__n,*puVar6);
    uVar4 = 0x37;
  }
  return uVar4;
}



/* Function: FUN_0010267c @ 0010267c */

undefined4 FUN_0010267c(char *param_1,undefined4 param_2,undefined4 param_3)

{
  uint uVar1;
  undefined4 uVar2;
  
  uVar1 = snprintf(param_1,0x400,"%s/%s",param_2,param_3);
  if (uVar1 < 0x400) {
    return 0;
  }
  uVar2 = FUN_00101754("path too long",param_2);
  return uVar2;
}



/* Function: FUN_001026cc @ 001026cc */

void FUN_001026cc(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  char acStack_20 [20];
  
  snprintf(acStack_20,0x10,"%u",param_3);
  FUN_0010267c(param_1,param_2,acStack_20);
  return;
}



/* Function: FUN_0010270c @ 0010270c */

undefined4 FUN_0010270c(undefined4 param_1,int param_2)

{
  byte bVar1;
  bool bVar2;
  int iVar3;
  void *__ptr;
  undefined4 uVar4;
  FILE *__stream;
  char *pcVar5;
  char *pcVar6;
  int iVar7;
  int iVar8;
  undefined1 uStack_d75;
  int local_d74;
  uint local_d70;
  int local_d6c;
  stat asStack_d68 [3];
  char acStack_c20 [1024];
  undefined1 auStack_820 [1024];
  char acStack_420 [1024];
  
  iVar3 = FUN_0010267c(acStack_c20,param_1,"manifest");
  if ((iVar3 == 0) && (iVar3 = FUN_0010267c(auStack_820,param_1,"manifest.sha256"), iVar3 == 0)) {
    __ptr = (void *)FUN_00101ab8(auStack_820,&local_d74);
    if (__ptr == (void *)0x0) {
      uVar4 = FUN_00101754("backup checksum missing",auStack_820);
      return uVar4;
    }
    if (local_d74 == 0x40) {
      iVar3 = FUN_001019d4(__ptr,acStack_c20);
      bVar2 = iVar3 == 0;
    }
    else {
      bVar2 = false;
    }
    free(__ptr);
    if (!bVar2) {
      uVar4 = FUN_00101754("backup manifest corrupt",acStack_c20);
      return uVar4;
    }
    __stream = fopen(acStack_c20,"rb");
    if (__stream != (FILE *)0x0) {
      pcVar5 = fgets((char *)&asStack_d68[0].st_ctim,0x100,__stream);
      if (((pcVar5 == (char *)0x0) ||
          (iVar3 = sscanf((char *)&asStack_d68[0].st_ctim,"MIBSI_BACKUP_V2 %u",&local_d70),
          iVar3 != 1)) || (local_d70 != 0x11)) {
        fclose(__stream);
        return 0xffffffff;
      }
      pcVar5 = (char *)(param_2 + 0x20);
      iVar3 = 0;
      do {
        pcVar6 = fgets((char *)&asStack_d68[0].st_ctim,0x100,__stream);
        iVar8 = iVar3;
        if (((pcVar6 == (char *)0x0) ||
            (iVar7 = sscanf((char *)&asStack_d68[0].st_ctim,"%u %c %o %lu %lu %64s %lld %lld %c",
                            &local_d6c,pcVar5 + -0x20,pcVar5 + -0x1c,pcVar5 + -0x18,pcVar5 + -0x14,
                            pcVar5,pcVar5 + -0x10,pcVar5 + -8,&uStack_d75), iVar7 != 8)) ||
           ((local_d6c != iVar3 || (iVar8 = local_d6c, 0xfff < *(uint *)(pcVar5 + -0x1c))))) {
LAB_00102988:
          if (iVar8 != 0x11) goto LAB_00102990;
          break;
        }
        bVar1 = pcVar5[-0x20];
        if ((bVar1 & 0xf7) != 0x46) {
          if (bVar1 == 0x4c) goto LAB_00102924;
          goto LAB_00102988;
        }
        if (bVar1 == 0x4e) {
          iVar7 = strcmp(pcVar5,"-");
        }
        else {
LAB_00102924:
          iVar7 = FUN_001026cc(acStack_420,param_1,iVar3);
          if (((iVar7 != 0) || (iVar7 = lstat(acStack_420,asStack_d68), iVar7 != 0)) ||
             ((asStack_d68[0].st_size & 0xf000U) != 0x8000)) goto LAB_00102988;
          iVar7 = FUN_001019d4(pcVar5,acStack_420);
        }
        if (iVar7 != 0) goto LAB_00102988;
        iVar3 = iVar3 + 1;
        pcVar5 = pcVar5 + 0x68;
      } while (iVar3 != 0x11);
      iVar3 = fgetc(__stream);
      if (iVar3 == -1) {
        iVar3 = ferror(__stream);
        local_d70 = (uint)(iVar3 == 0);
      }
      else {
LAB_00102990:
        local_d70 = 0;
      }
      fclose(__stream);
      if (local_d70 != 0) {
        return 0;
      }
      uVar4 = FUN_00101754("backup corrupt",param_1);
      return uVar4;
    }
  }
  return 0xffffffff;
}



/* Function: FUN_001029f4 @ 001029f4 */

undefined4 FUN_001029f4(undefined4 param_1,int param_2)

{
  undefined4 *puVar1;
  int iVar2;
  FILE *__stream;
  char *pcVar3;
  size_t sVar4;
  size_t sVar5;
  undefined4 uVar6;
  undefined4 *puVar7;
  undefined1 uStack_5a9;
  char acStack_5a8 [128];
  char acStack_528 [256];
  char acStack_428 [1028];
  
  iVar2 = FUN_0010267c(acStack_428,param_1,"install.sha256");
  if ((iVar2 == 0) && (__stream = fopen(acStack_428,"rb"), __stream != (FILE *)0x0)) {
    puVar7 = &DAT_00105954;
    do {
      pcVar3 = fgets(acStack_528,0x100,__stream);
      if (pcVar3 == (char *)0x0) goto LAB_00102b34;
      iVar2 = sscanf(acStack_528,"%64s %127s %c",param_2,acStack_5a8,&uStack_5a9);
      if (iVar2 != 2) goto LAB_00102b34;
      puVar1 = puVar7 + 1;
      puVar7 = puVar7 + 3;
      iVar2 = strcmp(acStack_5a8,(char *)*puVar1);
      if ((iVar2 != 0) || (iVar2 = FUN_0010267c(acStack_428,param_1,"files"), iVar2 != 0))
      goto LAB_00102b34;
      sVar4 = strlen(acStack_428);
      sVar5 = strlen(acStack_5a8);
      if (0x3ff < sVar4 + sVar5 + 2) goto LAB_00102b34;
      acStack_428[sVar4] = '/';
      memcpy(acStack_428 + sVar4 + 1,acStack_5a8,sVar5 + 1);
      iVar2 = FUN_001019d4(param_2,acStack_428);
      if (iVar2 != 0) goto LAB_00102b34;
      param_2 = param_2 + 0x41;
    } while (puVar7 != (undefined4 *)&UNK_001059fc);
    iVar2 = fgetc(__stream);
    if ((iVar2 == -1) && (iVar2 = ferror(__stream), iVar2 == 0)) {
      fclose(__stream);
      uVar6 = 0;
    }
    else {
LAB_00102b34:
      fclose(__stream);
      uVar6 = FUN_00101754("SD payload corrupt",param_1);
    }
  }
  else {
    uVar6 = 0xffffffff;
  }
  return uVar6;
}



/* Function: FUN_00102b84 @ 00102b84 */

undefined4 FUN_00102b84(char *param_1,void *param_2)

{
  int iVar1;
  int *piVar2;
  void *__src;
  undefined4 uVar3;
  uint uVar4;
  bool bVar5;
  int local_5c [12];
  uint local_2c;
  
  iVar1 = lstat(param_1,(stat *)(local_5c + 1));
  if (iVar1 == 0) {
    if (((local_2c & 0xf000) == 0x8000) &&
       (__src = (void *)FUN_00101ab8(param_1,local_5c), __src != (void *)0x0)) {
      if (local_5c[0] - 3U < 0x5d) {
        iVar1 = 0;
        do {
          uVar4 = (uint)*(byte *)((int)__src + iVar1);
          bVar5 = 0x2c < uVar4;
          if (uVar4 != 0x2d) {
            bVar5 = 8 < uVar4 - 0x30;
          }
          if (bVar5 && (uVar4 != 0x2d && uVar4 - 0x30 != 9)) {
            if (local_5c[0] != iVar1) {
              free(__src);
              return 0xffffffff;
            }
            break;
          }
          iVar1 = iVar1 + 1;
        } while (iVar1 != local_5c[0]);
        memcpy(param_2,__src,local_5c[0] + 1);
        free(__src);
        uVar3 = 0;
      }
      else {
        free(__src);
        uVar3 = 0xffffffff;
      }
    }
    else {
      uVar3 = 0xffffffff;
    }
  }
  else {
    piVar2 = (int *)__get_errno_ptr();
    if (*piVar2 == 2) {
      uVar3 = 1;
    }
    else {
      uVar3 = 0xffffffff;
    }
  }
  return uVar3;
}



/* Function: FUN_00102c64 @ 00102c64 */

int FUN_00102c64(char *param_1,void *param_2,size_t param_3)

{
  FILE *__s;
  int iVar1;
  size_t sVar2;
  int iVar3;
  
  __s = fopen(param_1,"wb");
  if (__s != (FILE *)0x0) {
    sVar2 = fwrite(param_2,1,param_3,__s);
    if ((sVar2 == param_3) && (iVar1 = fflush(__s), iVar1 == 0)) {
      iVar1 = fileno(__s);
      iVar1 = fsync(iVar1);
      iVar1 = -(uint)(iVar1 != 0);
    }
    else {
      iVar1 = -1;
    }
    iVar3 = fclose(__s);
    if (iVar3 != 0) {
      iVar1 = -1;
    }
    return iVar1;
  }
  iVar1 = FUN_00101754("write",param_1);
  return iVar1;
}



/* Function: FUN_00102d00 @ 00102d00 */

undefined4 FUN_00102d00(char *param_1,undefined4 param_2,undefined1 *param_3)

{
  int iVar1;
  int *piVar2;
  undefined4 uVar3;
  ssize_t sVar4;
  undefined1 auStack_480 [32];
  stat asStack_460 [12];
  
  memset(param_3,0,0x68);
  iVar1 = lstat(param_1,asStack_460);
  if (iVar1 != 0) {
    piVar2 = (int *)__get_errno_ptr();
    if (*piVar2 == 2) {
      *param_3 = 0x4e;
      memcpy(param_3 + 0x20,&DAT_00104f9c,2);
      return 0;
    }
    uVar3 = FUN_00101754(&DAT_00104fec,param_1);
    return uVar3;
  }
  *(undefined4 *)(param_3 + 0x14) = 0;
  *(undefined4 *)(param_3 + 0x1c) = 0;
  *(uint *)(param_3 + 4) = asStack_460[0].st_size & 0xfff;
  *(__uid_t *)(param_3 + 8) = asStack_460[0].st_uid;
  *(__gid_t *)(param_3 + 0xc) = asStack_460[0].st_gid;
  *(undefined4 *)(param_3 + 0x10) = asStack_460[0].st_rdev._4_4_;
  *(undefined4 *)(param_3 + 0x18) = (undefined4)asStack_460[0].st_rdev;
  if ((asStack_460[0].st_size & 0xf000U) == 0x8000) {
    *param_3 = 0x46;
    iVar1 = FUN_00101cf8(param_1,param_2);
    if (iVar1 != 0) {
      return 0xffffffff;
    }
    iVar1 = FUN_00101900(param_1,auStack_480);
  }
  else {
    if ((asStack_460[0].st_size & 0xf000U) != 0xa000) {
      uVar3 = FUN_00101754("refusing non-file destination",param_1);
      return uVar3;
    }
    sVar4 = readlink(param_1,(char *)&asStack_460[0].st_ctim,0x400);
    *param_3 = 0x4c;
    if (0x3fe < sVar4 - 1U) {
      return 0xffffffff;
    }
    iVar1 = FUN_00102c64(param_2,&asStack_460[0].st_ctim,sVar4);
    if (iVar1 != 0) {
      return 0xffffffff;
    }
    iVar1 = FUN_00101900(param_2,auStack_480);
  }
  if (iVar1 != 0) {
    return 0xffffffff;
  }
  FUN_00101570(auStack_480,param_3 + 0x20);
  iVar1 = FUN_001019d4(param_3 + 0x20,param_2);
  if (iVar1 != 0) {
    uVar3 = FUN_00101754("backup readback",param_2);
    return uVar3;
  }
  return 0;
}



/* Function: FUN_00102ea8 @ 00102ea8 */

undefined4 FUN_00102ea8(undefined4 param_1,int param_2)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  uint __maxlen;
  int iVar4;
  int iVar5;
  undefined1 auStack_14b8 [32];
  undefined1 auStack_1498 [68];
  undefined1 auStack_1454 [1024];
  undefined1 auStack_1054 [1024];
  char acStack_c54 [3124];
  
  iVar1 = snprintf(acStack_c54,0xc34,"MIBSI_BACKUP_V2 %u\n",0x11);
  if (-1 < iVar1) {
    iVar3 = param_2 + 0x20;
    iVar4 = 0;
    do {
      __maxlen = 0xc34 - iVar1;
      iVar5 = iVar4 + 1;
      uVar2 = snprintf(acStack_c54 + iVar1,__maxlen,"%u %c %o %lu %lu %s %lld %lld\n",iVar4,
                       (uint)*(byte *)(iVar3 + -0x20),*(undefined4 *)(iVar3 + -0x1c),
                       *(undefined4 *)(iVar3 + -0x18),*(undefined4 *)(iVar3 + -0x14),iVar3);
      iVar1 = iVar1 + uVar2;
      if ((int)uVar2 < 0) {
        return 0xffffffff;
      }
      if (__maxlen <= uVar2) {
        return 0xffffffff;
      }
      iVar3 = iVar3 + 0x68;
      iVar4 = iVar5;
    } while (iVar5 != 0x11);
    iVar3 = FUN_0010267c(auStack_1454,param_1,"manifest");
    if ((((iVar3 == 0) && (iVar3 = FUN_0010267c(auStack_1054,param_1,"manifest.sha256"), iVar3 == 0)
         ) && (iVar1 = FUN_00102c64(auStack_1454,acStack_c54,iVar1), iVar1 == 0)) &&
       (iVar1 = FUN_00101900(auStack_1454,auStack_14b8), iVar1 == 0)) {
      FUN_00101570(auStack_14b8,auStack_1498);
      iVar1 = FUN_00102c64(auStack_1054,auStack_1498,0x40);
      if (iVar1 == 0) {
        sync();
        return 0;
      }
    }
  }
  return 0xffffffff;
}



/* Function: FUN_00103018 @ 00103018 */

undefined4 FUN_00103018(undefined4 param_1)

{
  __time_t _Var1;
  int iVar2;
  int iVar3;
  size_t sVar4;
  stat *psVar5;
  undefined4 uVar6;
  int *piVar7;
  char *__s;
  int iVar8;
  char *pcVar9;
  utimbuf local_1718;
  char acStack_1710 [1024];
  char acStack_1310 [1024];
  undefined1 auStack_f10 [1024];
  stat asStack_b10 [11];
  char local_710 [1772];
  
  iVar2 = FUN_0010270c(param_1,local_710);
  if (iVar2 == 0) {
    iVar2 = 0;
    pcVar9 = local_710;
    do {
      iVar3 = FUN_001017f0(acStack_1710,(&DAT_00105954)[iVar2 * 3]);
      if ((((iVar3 != 0) ||
           (iVar3 = snprintf(acStack_1310,0x400,"%s.mibsi-stage",acStack_1710), 0x3ff < iVar3)) ||
          (iVar3 = FUN_001026cc(auStack_f10,param_1,iVar2), iVar3 != 0)) ||
         (sVar4 = strlen(acStack_1710), 0x3ff < sVar4)) goto LAB_00103304;
      memcpy(asStack_b10,acStack_1710,sVar4 + 1);
      psVar5 = (stat *)strrchr((char *)asStack_b10,0x2f);
      if (psVar5 == asStack_b10 || psVar5 == (stat *)0x0) goto LAB_00103304;
      *(char *)&psVar5->st_dev = '\0';
      iVar3 = FUN_00101830(asStack_b10,0x1ed);
      if (iVar3 != 0) goto LAB_00103304;
      iVar3 = lstat(acStack_1710,asStack_b10);
      if ((iVar3 == 0) && ((asStack_b10[0].st_size & 0xd000U) != 0x8000)) {
        uVar6 = FUN_00101754("non-file destination",acStack_1710);
        return uVar6;
      }
      iVar3 = unlink(acStack_1310);
      if ((iVar3 != 0) && (piVar7 = (int *)__get_errno_ptr(), *piVar7 != 2)) goto LAB_00103304;
      if (*pcVar9 != 'N') {
        if (*pcVar9 == 'L') {
          __s = (char *)FUN_00101ab8(auStack_f10,&local_1718);
          _Var1 = local_1718.actime;
          if (((__s == (char *)0x0) || (0x3fe < local_1718.actime - 1U)) ||
             (sVar4 = strlen(__s), _Var1 != sVar4)) {
            iVar3 = -1;
          }
          else {
            iVar3 = symlink(__s,acStack_1310);
          }
          free(__s);
          if (iVar3 != 0) goto LAB_00103304;
          iVar3 = lchown(acStack_1310,*(__uid_t *)(pcVar9 + 8),*(__gid_t *)(pcVar9 + 0xc));
        }
        else {
          local_1718.actime = *(__time_t *)(pcVar9 + 0x10);
          local_1718.modtime = *(__time_t *)(pcVar9 + 0x18);
          iVar3 = FUN_00101cf8(auStack_f10,acStack_1310);
          if (((iVar3 != 0) ||
              (iVar3 = chown(acStack_1310,*(__uid_t *)(pcVar9 + 8),*(__gid_t *)(pcVar9 + 0xc)),
              iVar3 != 0)) || (iVar3 = chmod(acStack_1310,*(__mode_t *)(pcVar9 + 4)), iVar3 != 0))
          goto LAB_00103304;
          iVar3 = utime(acStack_1310,&local_1718);
        }
        if (iVar3 != 0) goto LAB_00103304;
        iVar3 = FUN_00101b98(acStack_1310,pcVar9);
        if (iVar3 == 0) {
          uVar6 = FUN_00101754("staging readback",acStack_1310);
          return uVar6;
        }
      }
      iVar2 = iVar2 + 1;
      pcVar9 = pcVar9 + 0x68;
    } while (iVar2 != 0x11);
    sync();
    iVar2 = 0;
    do {
      iVar3 = FUN_001017f0(acStack_1710,(&DAT_00105954)[iVar2 * 3]);
      if (iVar3 != 0) goto LAB_00103304;
      iVar3 = snprintf(acStack_1310,0x400,"%s.mibsi-stage",acStack_1710);
      iVar8 = iVar2 * 0x68;
      iVar2 = iVar2 + 1;
      if (0x3ff < iVar3) goto LAB_00103304;
      if (local_710[iVar8] == 'N') {
        iVar3 = unlink(acStack_1710);
        if ((iVar3 != 0) && (piVar7 = (int *)__get_errno_ptr(), *piVar7 != 2)) goto LAB_00103304;
      }
      else {
        iVar3 = rename(acStack_1310,acStack_1710);
        if (iVar3 != 0) {
          uVar6 = FUN_00101754("publish",acStack_1710);
          return uVar6;
        }
      }
    } while (iVar2 != 0x11);
    sync();
    iVar2 = 0;
    do {
      iVar8 = FUN_001017f0(acStack_1710,(&DAT_00105954)[iVar2 * 3]);
      iVar3 = iVar2 * 0x68;
      iVar2 = iVar2 + 1;
      if ((iVar8 != 0) || (iVar3 = FUN_00101b98(acStack_1710,local_710 + iVar3), iVar3 == 0)) {
        uVar6 = FUN_00101754("final readback",acStack_1710);
        return uVar6;
      }
      uVar6 = 0;
    } while (iVar2 != 0x11);
  }
  else {
LAB_00103304:
    uVar6 = 0xffffffff;
  }
  return uVar6;
}



/* Function: FUN_001033fc @ 001033fc */

undefined4 FUN_001033fc(char *param_1,void *param_2)

{
  int iVar1;
  undefined4 uVar2;
  undefined1 auStack_30 [36];
  
  iVar1 = access(param_1,0);
  if (iVar1 == 0) {
    iVar1 = FUN_00101900(param_1,auStack_30);
    if (iVar1 == 0) {
      FUN_00101570(auStack_30,param_2);
      uVar2 = 0;
    }
    else {
      uVar2 = 0xffffffff;
    }
    return uVar2;
  }
  memcpy(param_2,&DAT_00104f9c,2);
  return 0;
}



/* Function: FUN_00103474 @ 00103474 */

int FUN_00103474(undefined4 param_1,undefined4 param_2,undefined4 param_3)

{
  int iVar1;
  undefined1 auStack_810 [1024];
  undefined1 auStack_410 [1028];
  
  iVar1 = FUN_0010267c(auStack_810,param_2,"history");
  if ((iVar1 == 0) && (iVar1 = FUN_0010267c(auStack_410,auStack_810,param_3), iVar1 == 0)) {
    iVar1 = FUN_0010267c(param_1,auStack_410,"before");
    iVar1 = -(uint)(iVar1 != 0);
  }
  else {
    iVar1 = -1;
  }
  return iVar1;
}



/* Function: main @ 001034ec */

int main(int param_1,int param_2)

{
  byte bVar1;
  char cVar2;
  void *pvVar3;
  int iVar4;
  int iVar5;
  FILE *pFVar6;
  void *__ptr;
  size_t sVar7;
  int iVar8;
  time_t tVar9;
  __pid_t _Var10;
  int iVar11;
  int *piVar12;
  int iVar13;
  char *pcVar14;
  char *__format;
  uint uVar15;
  undefined4 *puVar16;
  undefined4 uVar17;
  int iVar18;
  char *pcVar19;
  undefined4 uVar20;
  size_t __n;
  undefined2 local_4318 [16];
  undefined1 auStack_42f8 [32];
  char acStack_42d8 [96];
  char acStack_4278 [96];
  char acStack_4218 [256];
  undefined1 auStack_4118 [912];
  char acStack_3d88 [912];
  undefined1 auStack_39f8 [1024];
  char acStack_35f8 [1024];
  char acStack_31f8 [1024];
  undefined1 auStack_2df8 [1024];
  char acStack_29f8 [1024];
  char acStack_25f8 [1024];
  undefined1 auStack_21f8 [1024];
  char acStack_1df8 [1024];
  undefined1 auStack_19f8 [1024];
  char acStack_15f8 [1024];
  int local_11f8 [256];
  int local_df8 [16];
  undefined1 auStack_db7 [65];
  undefined1 auStack_d76 [65];
  undefined1 auStack_d35 [65];
  undefined1 auStack_cf4 [65];
  undefined1 auStack_cb3 [65];
  undefined1 auStack_c72 [1378];
  undefined4 local_710;
  char acStack_6f0 [32];
  undefined1 local_6d0;
  
  if (param_1 == 2) {
    iVar4 = strcmp(*(char **)(param_2 + 4),"check-vin");
    if (iVar4 == 0) {
      iVar4 = FUN_001021a8();
      return iVar4;
    }
LAB_00103528:
    fwrite("usage: mibsi-install-tool {sha256 FILE|verify SHA256 FILE|patch-dio SOURCE DEST|lsd-state FILE|lsd-mode FILE|patch-lsd SOURCE DEST|identify-release MANIFEST GAL DIO NAV AA CARPLAY TURN DISPLAY|known-component MANIFEST COLUMN FILE}\n"
           ,1,0xe7,(FILE *)_Stderr);
    return 2;
  }
  if (param_1 != 5) {
    if (param_1 == 3) {
      pcVar19 = *(char **)(param_2 + 4);
      iVar4 = strcmp(pcVar19,"check-payload");
      if (iVar4 == 0) {
        iVar4 = FUN_001029f4(*(undefined4 *)(param_2 + 8),&local_710);
        if (iVar4 != 0) {
          return 0x5b;
        }
        return 0;
      }
      iVar4 = strcmp(pcVar19,"sha256");
      if (iVar4 == 0) {
        iVar4 = FUN_00101900(*(undefined4 *)(param_2 + 8),local_df8);
        if (iVar4 == 0) {
          iVar4 = 0;
          do {
            bVar1 = *(byte *)((int)local_df8 + iVar4);
            iVar5 = iVar4 * 2;
            cVar2 = "0123456789abcdef"[bVar1 & 0xf];
            *(char *)((int)&local_710 + iVar4 * 2) = "0123456789abcdef"[bVar1 >> 4];
            iVar4 = iVar4 + 1;
            *(char *)((int)&local_710 + iVar5 + 1) = cVar2;
          } while (iVar4 != 0x20);
          local_6d0 = 0;
          puts((char *)&local_710);
          return 0;
        }
      }
      else {
        iVar4 = strcmp(pcVar19,"lsd-state");
        if (iVar4 == 0) {
          iVar4 = FUN_001020d8(*(undefined4 *)(param_2 + 8));
          return iVar4;
        }
        iVar4 = strcmp(pcVar19,"lsd-mode");
        if (iVar4 != 0) goto LAB_00103528;
        pvVar3 = (void *)FUN_00101ab8(*(undefined4 *)(param_2 + 8),&local_710);
        if (pvVar3 != (void *)0x0) {
          iVar4 = FUN_00101e08(pvVar3,"MIBSI_JAR_BOOTSTRAP_V1");
          if ((iVar4 == 1) &&
             (iVar4 = FUN_00101e08(pvVar3,"002_mhi2q_aa_navigation_proxy.jar"), iVar4 == 1)) {
            iVar4 = 1;
          }
          else {
            iVar4 = FUN_00101fb4(pvVar3);
            if (iVar4 == 0) {
              iVar4 = 2;
            }
            else {
              iVar4 = FUN_00101fb4(pvVar3);
              if (iVar4 == 10) {
                iVar4 = 10;
              }
              else {
                iVar4 = 0x14;
              }
            }
          }
          free(pvVar3);
          return iVar4;
        }
      }
      return 3;
    }
    if (param_1 == 4) {
      pcVar19 = *(char **)(param_2 + 4);
      iVar4 = strcmp(pcVar19,"verify");
      if (iVar4 == 0) {
        iVar4 = FUN_001019d4(*(undefined4 *)(param_2 + 8),*(undefined4 *)(param_2 + 0xc));
        return iVar4;
      }
      iVar4 = strcmp(pcVar19,"patch-dio");
      if (iVar4 == 0) {
        uVar17 = *(undefined4 *)(param_2 + 8);
        pcVar19 = *(char **)(param_2 + 0xc);
        iVar4 = FUN_001019d4("8a188ae556890051b623b18ab82f8a32f3dfdade4fd69240bf2b17b3f9ea5d4d",
                             uVar17);
        if (iVar4 != 0) {
          return 10;
        }
        pvVar3 = (void *)FUN_00101ab8(uVar17,local_11f8);
        if (pvVar3 == (void *)0x0) {
          return 0xb;
        }
        iVar4 = FUN_00101e08(pvVar3,
                             "\"MessagesSentByAccessory\":[\"0x5000\", \"0x5002\", \"0xAE00\", \"0xAE02\", \"0xAE03\", \"0x4154\", \"0x4156\", \"0x4157\", \"0x4159\", \"0xFFFB\", \"0x4C00\", \"0x4C02\", \"0x4C03\", \"0x4C05\"]"
                            );
        if ((iVar4 != 1) ||
           (iVar4 = FUN_00101e08(pvVar3,
                                 "\"MessagesReceivedFromDevice\":[\"0x4E09\", \"0x4E0A\", \"0x4E0C\", \"0x5001\", \"0xAE01\", \"0x4155\", \"0x4158\", \"0xFFFA\", \"0xFFFC\", \"0x4C01\", \"0x4C04\"]"
                                ), iVar4 != 1)) {
          free(pvVar3);
          return 0xc;
        }
        pFVar6 = fopen(pcVar19,"wb");
        if (pFVar6 == (FILE *)0x0) {
          free(pvVar3);
          return 0xd;
        }
        iVar4 = FUN_00101e4c(pFVar6,pvVar3,
                             "\"MessagesSentByAccessory\":[\"0x5000\", \"0x5002\", \"0xAE00\", \"0xAE02\", \"0xAE03\", \"0x4154\", \"0x4156\", \"0x4157\", \"0x4159\", \"0xFFFB\", \"0x4C00\", \"0x4C02\", \"0x4C03\", \"0x4C05\"]"
                             ,
                             "\"MessagesSentByAccessory\":[\"0x5000\", \"0x5002\", \"0xAE00\", \"0xAE02\", \"0xAE03\", \"0x4154\", \"0x4156\", \"0x4157\", \"0x4159\", \"0xFFFB\", \"0x4C00\", \"0x4C02\", \"0x4C03\", \"0x4C05\", \"0x5200\", \"0x5203\"]"
                             ,local_df8);
        if ((((iVar4 == 0) &&
             (iVar4 = FUN_00101e4c(pFVar6,local_df8[0],
                                   "\"MessagesReceivedFromDevice\":[\"0x4E09\", \"0x4E0A\", \"0x4E0C\", \"0x5001\", \"0xAE01\", \"0x4155\", \"0x4158\", \"0xFFFA\", \"0xFFFC\", \"0x4C01\", \"0x4C04\"]"
                                   ,
                                   "\"MessagesReceivedFromDevice\":[\"0x4E09\", \"0x4E0A\", \"0x4E0C\", \"0x5001\", \"0xAE01\", \"0x4155\", \"0x4158\", \"0xFFFA\", \"0xFFFC\", \"0x4C01\", \"0x4C04\", \"0x5201\", \"0x5202\", \"0x5204\"]"
                                   ,&local_710), iVar4 == 0)) &&
            (sVar7 = fwrite(local_710,1,(int)pvVar3 + (local_11f8[0] - (int)local_710),pFVar6),
            sVar7 == (int)pvVar3 + (local_11f8[0] - (int)local_710))) &&
           (iVar4 = fflush(pFVar6), iVar4 == 0)) {
          iVar4 = fileno(pFVar6);
          iVar4 = fsync(iVar4);
          if ((iVar4 == 0) && (iVar4 = fclose(pFVar6), iVar4 == 0)) {
            free(pvVar3);
            return 0;
          }
        }
        free(pvVar3);
        unlink(pcVar19);
        return 0xe;
      }
      iVar4 = strcmp(pcVar19,"patch-lsd");
      if (iVar4 == 0) {
        pcVar19 = *(char **)(param_2 + 0xc);
        pvVar3 = (void *)FUN_00101ab8(*(undefined4 *)(param_2 + 8),local_df8);
        if (pvVar3 == (void *)0x0) {
          return 0x14;
        }
        iVar4 = FUN_00101fb4();
        if (iVar4 == 10) {
          __ptr = (void *)FUN_00101eec(pvVar3,&local_710);
          if ((__ptr == (void *)0x0) || (local_710 != (void *)0x1)) {
            free(pvVar3);
            return 0x16;
          }
          pFVar6 = fopen(pcVar19,"wb");
          if (pFVar6 == (FILE *)0x0) {
            free(pvVar3);
            return 0x17;
          }
          __n = (int)__ptr - (int)pvVar3;
          sVar7 = fwrite(pvVar3,1,__n,pFVar6);
          if ((((__n == sVar7) &&
               (sVar7 = fwrite("# MIBSI_JAR_BOOTSTRAP_V1\nBOOTCLASSPATH=\"$BOOTCLASSPATH -Xbootclasspath/p:$BASE_DIR/lsd/jars/002_mhi2q_aa_navigation_proxy.jar\"\n"
                               ,1,0x7f,pFVar6), sVar7 == 0x7f)) &&
              (sVar7 = fwrite(__ptr,1,local_df8[0] - __n,pFVar6), sVar7 == local_df8[0] - __n)) &&
             (iVar4 = fflush(pFVar6), iVar4 == 0)) {
            iVar4 = fileno(pFVar6);
            iVar4 = fsync(iVar4);
            if ((iVar4 == 0) && (iVar4 = fclose(pFVar6), iVar4 == 0)) {
              free(pvVar3);
              iVar4 = FUN_001020d8(pcVar19);
              if (iVar4 != 0) {
                unlink(pcVar19);
                return 0x19;
              }
              return 0;
            }
          }
          free(pvVar3);
          unlink(pcVar19);
          return 0x18;
        }
        free(pvVar3);
        return 0x15;
      }
    }
    else if ((param_1 == 10) &&
            (iVar4 = strcmp(*(char **)(param_2 + 4),"identify-release"), iVar4 == 0)) {
      puVar16 = (undefined4 *)(param_2 + 8);
      pcVar19 = (char *)*puVar16;
      iVar4 = 0;
      do {
        iVar5 = iVar4 * 0x41;
        puVar16 = puVar16 + 1;
        iVar4 = iVar4 + 1;
        iVar5 = FUN_001033fc(*puVar16,(int)local_11f8 + iVar5);
        if (iVar5 != 0) {
          return 0x47;
        }
      } while (iVar4 != 7);
      pFVar6 = fopen(pcVar19,"rb");
      if (pFVar6 == (FILE *)0x0) {
        return 0x48;
      }
      while( true ) {
        do {
          pcVar19 = fgets((char *)&local_710,0x400,pFVar6);
          if (pcVar19 == (char *)0x0) {
            iVar4 = ferror(pFVar6);
            if (iVar4 == 0) {
              iVar4 = 0x4c;
            }
            else {
              iVar4 = 0x4b;
            }
            fclose(pFVar6);
            return iVar4;
          }
          uVar15 = (uint)local_710 & 0xff;
        } while (uVar15 == 0xd || (uVar15 == 0x23 || uVar15 == 10));
        iVar4 = sscanf((char *)&local_710,"%64s %64s %64s %64s %64s %64s %64s %64s %1s",acStack_15f8
                       ,local_df8,auStack_db7,auStack_d76,auStack_d35,auStack_cf4,auStack_cb3,
                       auStack_c72,auStack_19f8);
        if ((iVar4 != 8) || (iVar4 = FUN_00101798(acStack_15f8), iVar4 == 0)) break;
        iVar4 = 0;
        while( true ) {
          pcVar14 = (char *)((int)local_df8 + iVar4);
          pcVar19 = (char *)((int)local_11f8 + iVar4);
          iVar4 = iVar4 + 0x41;
          iVar5 = strcmp(pcVar19,pcVar14);
          if (iVar5 != 0) break;
          if (iVar4 == 0x1c7) {
            fclose(pFVar6);
            puts(acStack_15f8);
            iVar4 = ferror((FILE *)_Stdout);
            if (iVar4 != 0) {
              return 0x4a;
            }
            return 0;
          }
        }
      }
      fclose(pFVar6);
      return 0x49;
    }
    goto LAB_00103528;
  }
  iVar4 = strcmp(*(char **)(param_2 + 4),"lifecycle");
  if (iVar4 != 0) {
    iVar4 = strcmp(*(char **)(param_2 + 4),"known-component");
    if (iVar4 == 0) {
      pcVar19 = *(char **)(param_2 + 8);
      uVar17 = *(undefined4 *)(param_2 + 0x10);
      iVar4 = atoi(*(char **)(param_2 + 0xc));
      if (iVar4 - 1U < 7) {
        iVar5 = FUN_001033fc(uVar17,acStack_15f8);
        if (iVar5 == 0) {
          pFVar6 = fopen(pcVar19,"rb");
          if (pFVar6 == (FILE *)0x0) {
            return 0x51;
          }
          while( true ) {
            do {
              pcVar19 = fgets((char *)&local_710,0x400,pFVar6);
              if (pcVar19 == (char *)0x0) {
                iVar4 = ferror(pFVar6);
                if (iVar4 == 0) {
                  iVar4 = 0x54;
                }
                else {
                  iVar4 = 0x53;
                }
                fclose(pFVar6);
                return iVar4;
              }
              uVar15 = (uint)local_710 & 0xff;
            } while (uVar15 == 0xd || (uVar15 == 0x23 || uVar15 == 10));
            iVar5 = sscanf((char *)&local_710,"%64s %64s %64s %64s %64s %64s %64s %64s %1s",
                           local_11f8,local_df8,auStack_db7,auStack_d76,auStack_d35,auStack_cf4,
                           auStack_cb3,auStack_c72,auStack_19f8);
            if (iVar5 != 8) break;
            iVar5 = FUN_00101798(local_11f8);
            if (iVar5 == 0) break;
            iVar5 = strcmp(acStack_15f8,(char *)((int)local_df8 + (iVar4 - 1U) * 0x41));
            if (iVar5 == 0) {
              fclose(pFVar6);
              return 0;
            }
          }
          fclose(pFVar6);
          return 0x52;
        }
      }
      return 0x50;
    }
    goto LAB_00103528;
  }
  pcVar19 = *(char **)(param_2 + 8);
  uVar20 = *(undefined4 *)(param_2 + 0xc);
  uVar17 = *(undefined4 *)(param_2 + 0x10);
  iVar4 = strcmp(pcVar19,"install");
  if (iVar4 == 0) {
    iVar4 = FUN_00101798(uVar17);
    if (iVar4 == 0) {
      return 0x5a;
    }
    iVar4 = FUN_001021a8();
    if (iVar4 != 0) {
      fwrite("MIBSI: VIN non autorise ou lecture VIN impossible\n",1,0x32,(FILE *)_Stderr);
      return iVar4;
    }
    iVar4 = strcmp(pcVar19,"install");
    if ((iVar4 == 0) && (iVar4 = FUN_001029f4(uVar20,auStack_4118), iVar4 != 0)) {
      return 0x5b;
    }
  }
  else {
    iVar4 = strcmp(pcVar19,"uninstall");
    if ((iVar4 != 0) || (iVar4 = FUN_00101798(uVar17), iVar4 == 0)) {
      return 0x5a;
    }
  }
  iVar4 = FUN_001017f0(auStack_39f8,"/mnt/app/root/mibsi");
  if (((iVar4 != 0) || (iVar4 = FUN_00101830(auStack_39f8,0x1c0), iVar4 != 0)) ||
     (iVar4 = FUN_0010267c(acStack_35f8,auStack_39f8,"installer.lock"), iVar4 != 0)) {
    return 0x5c;
  }
  iVar4 = open(acStack_35f8,0x101,0x180);
  if (iVar4 < 0) {
    return 0x5d;
  }
  memset(local_4318,0,0x20);
  local_4318[0] = 2;
  iVar5 = fcntl(iVar4,6,local_4318);
  if (iVar5 != 0) {
    close(iVar4);
    return 0x5e;
  }
  iVar5 = strcmp(pcVar19,"uninstall");
  iVar8 = FUN_0010267c(acStack_31f8,auStack_39f8,"pending");
  if (((iVar8 != 0) || (iVar8 = FUN_0010267c(auStack_2df8,auStack_39f8,"origin"), iVar8 != 0)) ||
     ((iVar8 = FUN_0010267c(auStack_21f8,auStack_39f8,"history"), iVar8 != 0 ||
      (iVar8 = FUN_00101830(auStack_21f8,0x1c0), iVar8 != 0)))) goto LAB_001040c8;
  iVar8 = FUN_00102b84(acStack_31f8,acStack_42d8);
  if (-1 < iVar8) {
    if (iVar8 == 0) {
      puts("MIBSI: recovering interrupted operation before new work");
      iVar8 = FUN_00103474(acStack_29f8,auStack_39f8,acStack_42d8);
      if ((iVar8 != 0) || (iVar8 = FUN_00103018(acStack_29f8), iVar8 != 0)) {
        iVar5 = FUN_00101754("recovery incomplete; keep SD and retry",acStack_29f8);
        goto LAB_00103fec;
      }
      iVar8 = unlink(acStack_31f8);
      if (iVar8 != 0) goto LAB_001040c8;
      sync();
    }
    iVar8 = FUN_00102b84(auStack_2df8,acStack_4278);
    if (iVar8 < 0) {
      iVar5 = FUN_00101754("invalid original backup pointer",auStack_2df8);
      goto LAB_00103fec;
    }
    if (iVar8 == 0) {
      iVar11 = FUN_00103474(acStack_25f8,auStack_39f8,acStack_4278);
      if ((iVar11 != 0) || (iVar11 = FUN_0010270c(acStack_25f8,local_df8), iVar11 != 0))
      goto LAB_001040c8;
    }
    else if (iVar5 == 0) {
      puts("MIBSI: no V2 installation to uninstall; existing files kept");
      goto LAB_001044ec;
    }
    iVar11 = 0;
    while( true ) {
      tVar9 = time((time_t *)0x0);
      _Var10 = getpid();
      iVar18 = iVar11 + 1;
      snprintf(acStack_42d8,0x60,"%lu-%lu-%u",tVar9,_Var10,iVar11);
      iVar11 = FUN_0010267c(acStack_1df8,auStack_21f8,acStack_42d8);
      if (iVar11 != 0) break;
      iVar11 = mkdir(acStack_1df8,0x1c0);
      if (iVar11 == 0) {
        iVar11 = FUN_0010267c(acStack_29f8,acStack_1df8,"before");
        if ((iVar11 == 0) && (iVar11 = mkdir(acStack_29f8,0x1c0), iVar11 == 0)) {
          iVar11 = 0;
          goto LAB_00104200;
        }
        break;
      }
      piVar12 = (int *)__get_errno_ptr();
      if ((*piVar12 != 0x11) || (iVar11 = iVar18, iVar18 == 1000)) break;
    }
    goto LAB_001040c8;
  }
  iVar5 = FUN_00101754("invalid pending journal",acStack_31f8);
LAB_00103fec:
  close(iVar4);
  if (iVar5 != 0) {
    return 0x5f;
  }
  return 0;
  while( true ) {
    iVar13 = FUN_001026cc(local_11f8,acStack_29f8,iVar11);
    iVar18 = iVar11 * 0x68;
    iVar11 = iVar11 + 1;
    if ((iVar13 != 0) ||
       (iVar18 = FUN_00102d00(acStack_15f8,local_11f8,(char *)((int)&local_710 + iVar18)),
       iVar18 != 0)) goto LAB_001040c8;
    if (iVar11 == 0x11) break;
LAB_00104200:
    iVar18 = FUN_001017f0(acStack_15f8,(&DAT_00105954)[iVar11 * 3]);
    if (iVar18 != 0) goto LAB_001040c8;
  }
  iVar11 = FUN_00102ea8(acStack_29f8,&local_710);
  if ((iVar11 != 0) || (iVar11 = FUN_0010270c(acStack_29f8,local_df8), iVar11 != 0))
  goto LAB_001040c8;
  if (iVar5 != 0) {
    if (iVar8 == 1) {
      strcpy(acStack_4278,acStack_42d8);
    }
    iVar5 = FUN_0010267c(acStack_25f8,acStack_1df8,"after");
    if ((((iVar5 != 0) || (iVar5 = FUN_001029f4(uVar20,acStack_3d88), iVar5 != 0)) ||
        (iVar5 = mkdir(acStack_25f8,0x1c0), iVar5 != 0)) ||
       (iVar5 = FUN_0010267c(local_11f8,uVar20,"files"), iVar5 != 0)) goto LAB_001040c8;
    puVar16 = &DAT_00105954;
    memset(&local_710,0,0x6e8);
    uVar15 = 0;
    pcVar14 = acStack_6f0;
    do {
      iVar5 = FUN_001026cc(acStack_15f8,acStack_25f8,uVar15);
      if (iVar5 != 0) goto LAB_001040c8;
      if (uVar15 < 0xe) {
        iVar5 = FUN_0010267c(auStack_19f8,local_11f8,puVar16[1]);
        if (((iVar5 != 0) ||
            (iVar5 = FUN_00102d00(auStack_19f8,acStack_15f8,pcVar14 + -0x20), iVar5 != 0)) ||
           ((pcVar14[-0x20] != 'F' ||
            (iVar5 = strcmp(pcVar14,acStack_3d88 + uVar15 * 0x41), iVar5 != 0)))) goto LAB_001040c8;
      }
      else {
        if (uVar15 == 0xe) {
          snprintf(acStack_4218,0x100,"%s",acStack_4278);
        }
        else {
          if (uVar15 == 0xf) {
            __format = "release_id=%s\n";
          }
          else {
            __format = 
            "format=MIBSI_INSTALLED_V2\nstate=installed\nrelease_id=%s\nfirmware=MHI2Q_ER_AUG22_P5092_MU1329\n"
            ;
          }
          snprintf(acStack_4218,0x100,__format,uVar17);
        }
        sVar7 = strlen(acStack_4218);
        iVar5 = FUN_00102c64(acStack_15f8,acStack_4218,sVar7);
        if ((iVar5 != 0) || (iVar5 = FUN_00101900(acStack_15f8,auStack_42f8), iVar5 != 0))
        goto LAB_001040c8;
        FUN_00101570(auStack_42f8,pcVar14);
        tVar9 = time((time_t *)0x0);
        pcVar14[-0xffffffff0000000c] = '\0';
        pcVar14[-0xffffffff0000000b] = '\0';
        pcVar14[-0xffffffff0000000a] = '\0';
        pcVar14[-0xffffffff00000009] = '\0';
        pcVar14[-0xffffffff00000004] = '\0';
        pcVar14[-0xffffffff00000003] = '\0';
        pcVar14[-0xffffffff00000002] = '\0';
        pcVar14[-0xffffffff00000001] = '\0';
        *(time_t *)(pcVar14 + -8) = tVar9;
        *(time_t *)(pcVar14 + -0x10) = tVar9;
      }
      uVar20 = puVar16[2];
      uVar15 = uVar15 + 1;
      puVar16 = puVar16 + 3;
      pcVar14[-0x20] = 'F';
      *(undefined4 *)(pcVar14 + -0x1c) = uVar20;
      pcVar14[-0xffffffff00000018] = '\0';
      pcVar14[-0xffffffff00000017] = '\0';
      pcVar14[-0xffffffff00000016] = '\0';
      pcVar14[-0xffffffff00000015] = '\0';
      pcVar14[-0xffffffff00000014] = '\0';
      pcVar14[-0xffffffff00000013] = '\0';
      pcVar14[-0xffffffff00000012] = '\0';
      pcVar14[-0xffffffff00000011] = '\0';
      pcVar14 = pcVar14 + 0x68;
    } while (uVar15 != 0x11);
    iVar5 = FUN_00102ea8(acStack_25f8,&local_710);
    if (iVar5 != 0) goto LAB_001040c8;
  }
  iVar5 = snprintf((char *)&local_710,0x400,"%s.new",acStack_31f8);
  if (iVar5 < 0x400) {
    sVar7 = strlen(acStack_42d8);
    iVar5 = FUN_00102c64(&local_710,acStack_42d8,sVar7);
    if ((iVar5 == 0) && (iVar5 = rename((char *)&local_710,acStack_31f8), iVar5 == 0)) {
      sync();
      iVar5 = FUN_00103018(acStack_25f8);
      if (iVar5 == 0) {
        iVar5 = unlink(acStack_31f8);
        if (iVar5 == 0) {
          sync();
          printf("MIBSI: %s verified; backup history=%s\n",pcVar19,acStack_42d8);
LAB_001044ec:
          close(iVar4);
          return 0;
        }
      }
      else {
        puts("MIBSI: operation failed; restoring previous files");
        iVar5 = FUN_00103018(acStack_29f8);
        if (iVar5 != 0) {
          iVar5 = FUN_00101754("rollback incomplete; pending journal retained",acStack_31f8);
          goto LAB_00103fec;
        }
        iVar5 = unlink(acStack_31f8);
        if (iVar5 == 0) {
          sync();
        }
      }
    }
  }
LAB_001040c8:
  close(iVar4);
  return 0x5f;
}



/* Function: FUN_0010465c @ 0010465c */

undefined4 FUN_0010465c(int param_1)

{
  undefined4 *__ptr;
  pthread_mutex_t *__mutex;
  undefined4 *puVar1;
  undefined4 *puVar2;
  __pid_t _Var3;
  int local_14 [2];
  
  pthread_once(DAT_00104720,DAT_0010471c);
  pthread_mutex_lock(DAT_00104724);
  __mutex = DAT_00104724;
  puVar1 = (void *)*DAT_00104728;
  puVar2 = DAT_00104728;
  while( true ) {
    __ptr = puVar1;
    if (__ptr == (void *)0x0) {
      pthread_mutex_unlock(DAT_00104724);
      puVar2 = (undefined4 *)__get_errno_ptr();
      *puVar2 = 0x16;
      return 0xffffffff;
    }
    if ((__ptr[2] != -1) && (__ptr[1] == param_1)) break;
    puVar1 = (undefined4 *)*__ptr;
    puVar2 = __ptr;
  }
  *puVar2 = *__ptr;
  pthread_mutex_unlock(__mutex);
  _Var3 = __ptr[2];
  fclose((FILE *)__ptr[1]);
  free(__ptr);
  _Var3 = waitpid(_Var3,local_14,0);
  if (_Var3 != -1) {
    return local_14[0];
  }
  return 0xffffffff;
}



/* Function: FUN_00104770 @ 00104770 */

int FUN_00104770(undefined4 param_1,char *param_2)

{
  int *piVar1;
  int *piVar2;
  pthread_mutex_t *__mutex;
  int *piVar3;
  int *piVar4;
  int iVar5;
  FILE *__fp;
  undefined4 *puVar6;
  size_t sVar7;
  int iVar8;
  undefined4 uVar9;
  bool bVar10;
  undefined1 auStack_854 [1028];
  char acStack_450 [1028];
  undefined4 local_4c;
  undefined4 local_48;
  undefined4 local_44;
  undefined4 local_40;
  int local_3c;
  int local_38;
  undefined4 local_34;
  int local_30 [3];
  
  pthread_once(DAT_00104a30,DAT_00104a2c);
  if (*param_2 == 'w' || *param_2 == 'r') {
    iVar8 = 0;
    piVar4 = malloc(0xc);
    if (piVar4 == (int *)0x0) {
      puVar6 = (undefined4 *)__get_errno_ptr();
      *puVar6 = 0xc;
      iVar8 = 0;
    }
    else {
      iVar5 = pipe(local_30);
      if (iVar5 == -1) {
        free(piVar4);
      }
      else {
        iVar5 = pthread_mutex_lock(DAT_00104a34);
        piVar2 = DAT_00104a38;
        if (iVar5 == 0) {
          piVar4[2] = -1;
          __mutex = DAT_00104a34;
          *piVar4 = *piVar2;
          *piVar2 = (int)piVar4;
          pthread_mutex_unlock(__mutex);
          bVar10 = *param_2 == 'r';
          if (bVar10) {
            local_38 = local_30[1];
            local_3c = 0;
          }
          else {
            local_38 = 1;
            local_3c = local_30[0];
          }
          local_34 = 2;
          __fp = fdopen(local_30[!bVar10],param_2);
          piVar4[1] = (int)__fp;
          if (__fp == (FILE *)0x0) {
            puVar6 = (undefined4 *)__get_errno_ptr();
            uVar9 = *puVar6;
            close(local_30[!bVar10]);
          }
          else {
            fwide(__fp,-1);
            puVar6 = (undefined4 *)__get_errno_ptr();
            uVar9 = *puVar6;
            sVar7 = confstr(1,acStack_450,0x401);
            local_4c = DAT_00104a3c;
            if ((sVar7 == 0) ||
               (iVar8 = pathfind_r(acStack_450,DAT_00104a3c,DAT_00104a48,auStack_854,0x401),
               iVar8 == 0)) {
              iVar8 = DAT_00104a40;
            }
            local_48 = DAT_00104a44;
            *puVar6 = uVar9;
            local_40 = 0;
            local_44 = param_1;
            iVar8 = spawn(iVar8,3,&local_3c,0,&local_4c,0);
            piVar4[2] = iVar8;
            if (iVar8 == -1) {
              uVar9 = *puVar6;
            }
          }
          close(local_30[bVar10]);
          if (piVar4[2] == -1) {
            pthread_mutex_lock(DAT_00104a34);
            piVar3 = (int *)*piVar2;
            piVar2 = DAT_00104a38;
            while (piVar1 = piVar3, piVar1 != (int *)0x0) {
              if (piVar4 == piVar1) {
                *piVar2 = *piVar1;
                break;
              }
              piVar2 = piVar1;
              piVar3 = (int *)*piVar1;
            }
            pthread_mutex_unlock(DAT_00104a34);
            if ((FILE *)piVar4[1] != (FILE *)0x0) {
              fclose((FILE *)piVar4[1]);
            }
            iVar8 = 0;
            free(piVar4);
            puVar6 = (undefined4 *)__get_errno_ptr();
            *puVar6 = uVar9;
          }
          else {
            iVar8 = piVar4[1];
          }
        }
        else {
          free(piVar4);
          close(local_30[0]);
          close(local_30[1]);
          piVar4 = (int *)__get_errno_ptr();
          *piVar4 = iVar5;
        }
      }
    }
  }
  else {
    puVar6 = (undefined4 *)__get_errno_ptr();
    iVar8 = 0;
    *puVar6 = 0x16;
  }
  return iVar8;
}



/* Function: _DT_FINI @ 00104a4c */

void _DT_FINI(void)

{
  return;
}


