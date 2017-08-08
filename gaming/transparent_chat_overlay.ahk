/*
This script is actually a combination of two scripts :

> https://autohotkey.com/board/topic/53249-transparent-andor-always-on-top/
> https://www.reddit.com/r/Twitch/comments/2613n5/how_to_set_up_ingame_chat_overlay_for_single/

I use it for my personnal usage when streaming on single monitor and want my HexChat to remain on top/transparent.

The combination I use is :
  Alt-7 ==> Alt-+ ==> Alt-8

When am done, and want to exit :
  Alt-TAB (to be able to select hexchat once again)
  Alt-7 ==> Alt-9
  Then exit Hexchat and AutoHotkey.

Enjoy !
*/

#InstallKeybdHook
#SingleInstance force
/*
Hotkeys:
Alt-7: make window always on top

Alt-0: make window less transparent
Alt-+: make window more transparent

Alt-8: make window clickthoughable
Alt-9: make window under mouse unclickthroughable
*/

!7::
WinGet, currentWindow, ID, A
WinGet, ExStyle, ExStyle, ahk_id %currentWindow%
if (ExStyle & 0x8)  ; 0x8 is WS_EX_TOPMOST.
{
	Winset, AlwaysOnTop, off, ahk_id %currentWindow%
	SplashImage,, x0 y0 b fs12, OFF always on top.
	Sleep, 1500
	SplashImage, Off
}
else
{
	WinSet, AlwaysOnTop, on, ahk_id %currentWindow%
	SplashImage,,x0 y0 b fs12, ON always on top.
	Sleep, 1500
	SplashImage, Off
}
return

!0::
WinGet, currentWindow, ID, A
if not (%currentWindow%)
{
	%currentWindow% := 255
}
if (%currentWindow% != 255)
{
	%currentWindow% += 5
	WinSet, Transparent, % %currentWindow%, ahk_id %currentWindow%
}
SplashImage,,w100 x0 y0 b fs12, % %currentWindow%
SetTimer, TurnOffSI, 1000, On
Return

!+::
SplashImage, Off
WinGet, currentWindow, ID, A
if not (%currentWindow%)
{
	%currentWindow% := 255
}
if (%currentWindow% != 5)
{
	%currentWindow% -= 5
	WinSet, Transparent, % %currentWindow%, ahk_id %currentWindow%
}
SplashImage,, w100 x0 y0 b fs12, % %currentWindow%
SetTimer, TurnOffSI, 1000, On
Return

!8::
WinGet, currentWindow, ID, A
WinSet, ExStyle, +0x80020, ahk_id %currentWindow%
return

!9::
MouseGetPos,,, MouseWin ; Gets the unique ID of the window under the mouse
WinSet, ExStyle, -0x80020, ahk_id %currentWindow%
Return

; Toggles Always On Top & Title Bar, and sets Transparency to 150
!^[::
 Winset, Alwaysontop, TOGGLE, A
 WinSet, Style, ^0xC00000, A
 WinSet, Transparent, 150, A
 return

; Toggles Always On Top & Title Bar, and turns off Transparency
!^]::
Winset, Alwaysontop, TOGGLE, A
WinSet, Style, ^0xC00000, A
WinSet, Transparent, OFF, A
return

; True Windowed FullScreen Fix for Games by Slomer - http://forum.egosoft.com/viewtopic.php?t=326868
!^p::
WinGet, TempWindowID, ID, A
If (WindowID != TempWindowID)
{
WindowID:=TempWindowID
WindowState:=0
}
If (WindowState != 1)
{
WinGetPos, WinPosX, WinPosY, WindowWidth, WindowHeight, ahk_id %WindowID%
WinSet, Style, ^0xC00000, ahk_id %WindowID%
WinMove, ahk_id %WindowID%, , -1, -1, ScreenWidth, ScreenHeight
}
Else
{
WinSet, Style, ^0xC00000, ahk_id %WindowID%
WinMove, ahk_id %WindowID%, , WinPosX, WinPosY, WindowWidth, WindowHeight
}
WindowState:=!WindowState
return

TurnOffSI:
SplashImage, off
SetTimer, TurnOffSI, 1000, Off
Return
