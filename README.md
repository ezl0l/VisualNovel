# VisualNovel
Commands:
  load_image <image_name> <image_path>
  Загружает изображение с именем <image_name>, которое (имя) можно будет позднее использовать в set_back(fore)ground. (Типа переменной)
  
  load_audio <audio_name> <audio_path>
  Загружает аудио с именем <audio_name>, которое (имя) можно будет позднее использовать в play_audio. (Типа переменной)
  
  set_flag <flag_name>
  Устанавливает флажок, существование которого можно проверить при помощи check_flag.
  
  check_flag <flag_name> <action_name>
  Если flag_name существует, вызывается действие <action_name>.
  
  remove_flag <flag_name>
  Удаляет флаг с именем <flag_name>.
  
  choice <agree_title> <decline_title> <agree_action> <decline_action>
  Ставит пользователя перед выбором... Если согласится, вызывается действие <agree_action>, если нет - <decline_action> соответственно.
  
  goto <scene_name>
  Переход к сцене <scene_name>.
  
  add_dialog <dialog_id> [action_name]
  Добавляет диалог с id <dialog_id> в очередь диалогов. При завершении диалога вызывается действие [action_name] (если соответствующий параметр передан).
  
  set_background <image_name>
  Устанавливает фон изображением, ранее подгруженным с помощью метода load_image.
  
  set_foreground <image_name>
  Устанавливает передний (фон??) изображением, ранее подгруженным с помощью load_image.
