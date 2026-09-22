param([switch]$Reprocess)
$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$javaExe = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME 'bin\java.exe' } else { (Get-Command java -ErrorAction Stop).Source }
$ghidraRoot = $env:GHIDRA_HOME
if (-not $ghidraRoot) { throw 'Set GHIDRA_HOME to the extracted Ghidra installation.' }
foreach ($subdir in @('.local\analysis-build\ghidra-project', 'reference\native', '.local\analysis-build\ghidra-settings', '.local\analysis-build\ghidra-cache', '.local\analysis-build\ghidra-temp', 'build\reports')) {
    New-Item -ItemType Directory -Force -Path (Join-Path $projectRoot $subdir) | Out-Null
}
$javaArgs = @(
    '-Xmx2G', '-XX:ParallelGCThreads=2', '-XX:CICompilerCount=2',
    '-Djava.system.class.loader=ghidra.GhidraClassLoader',
    '-Dfile.encoding=UTF8', '-Djava.awt.headless=true', '--enable-native-access=ALL-UNNAMED',
    "-Dapplication.settingsdir=$projectRoot\.local\analysis-build\ghidra-settings",
    "-Dapplication.cachedir=$projectRoot\.local\analysis-build\ghidra-cache",
    "-Dapplication.tempdir=$projectRoot\.local\analysis-build\ghidra-temp",
    '-cp', (Join-Path $ghidraRoot 'Ghidra\Framework\Utility\lib\Utility.jar'),
    'ghidra.Ghidra', 'ghidra.app.util.headless.AnalyzeHeadless',
    (Join-Path $projectRoot '.local\analysis-build\ghidra-project'), 'MIBSI'
)
if ($Reprocess) {
    $javaArgs += @('-process', '-recursive', '-noanalysis')
} else {
    $javaArgs += @('-import', (Join-Path $projectRoot 'reference\binaries'), '-recursive')
}
$javaArgs += @(
    '-scriptPath', $PSScriptRoot,
    '-postScript', 'ExportNativeCode.java', (Join-Path $projectRoot 'reference\native'),
    '-analysisTimeoutPerFile', '180', '-max-cpu', '2',
    '-log', (Join-Path $projectRoot 'build\reports\ghidra-analysis.log'),
    '-scriptlog', (Join-Path $projectRoot 'build\reports\ghidra-export.log')
)
& $javaExe @javaArgs 2>&1 | Tee-Object -FilePath (Join-Path $projectRoot 'build\reports\ghidra-console.log')
exit $LASTEXITCODE
