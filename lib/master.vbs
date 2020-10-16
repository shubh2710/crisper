Set WshShell = CreateObject("WScript.Shell")


ReDim arr(WScript.Arguments.Count-1)
For i = 0 To WScript.Arguments.Count-1
  arr(i) = WScript.Arguments(i)
Next


WshShell.Run """runvoid.bat"" " & Join(arr)  & """", 0
Set WshShell = Nothing