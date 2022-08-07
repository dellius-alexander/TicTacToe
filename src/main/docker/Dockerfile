ARG appname="tictactoe"
#FROM openjdk:11.0.12-jdk
FROM tomcat:9.0.55-jdk17-openjdk-buster
EXPOSE 8080
RUN mkdir -p /usr/src/${appname}
COPY maven /usr/src/${appname}/
VOLUME [ "/usr/src/${appname}/data/" ]
WORKDIR /usr/src/${appname}
RUN echo "PRINT OUT:  ${project.build.finalName}.${project.packaging}:lib/*"

#CMD [ "java","--module-path","${project.build.finalName}.${project.packaging}:lib/*",\
#"--add-modules", "javafx.controls","com.hyfi.tictactoe.GameBoardView" ]


CMD [ "java","-jar","gameboardview-2.3.jar"]

# java --module-path ${PATH_TO_FX} --add-modules javafx.controls,GridBoardView.fxml
# java --module-path "target/lib" --add-modules target/lib/javafx.controls,target/classes/GridBoardView.fxml -jar target/gameboardview-2.3.jar