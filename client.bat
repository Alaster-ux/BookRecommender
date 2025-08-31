@echo off
REM Vai nella cartella del progetto
cd "Documents\BookRecoomender_FXB2"

REM Avvia il server senza console con javaw
javaw -Dprism.order=sw -Dprism.verbose=true --module-path "lib\Javafx 17\javafx-sdk-17.0.16\lib" --add-modules javafx.controls,javafx.fxml -jar bin\clientBR.jar
exit
