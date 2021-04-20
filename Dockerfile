From registry.dellius.app/oracle/jdk:11 
COPY [ ".src/", "/app"]
WORKDIR "/app"
RUN javac "/app/TicTacToe.java"
RUN rm -rf "/app/*.java"
CMD [ "java", "/app/TicTacToe.class" ]
