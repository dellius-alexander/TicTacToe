FROM openjdk:11.0.12-jdk
RUN mkdir -p /usr/src/${appname}
COPY maven /usr/src/${appname}/
VOLUME [ "/usr/src/${appname}/data/" ]
WORKDIR /usr/src/${appname}
RUN echo "${project.build.finalName}.${project.packaging}:lib/*"
CMD [ "java","-cp","${project.build.finalName}.${project.packaging}:lib/*","com.hyfi.tictactoe.TicTacToe" ]