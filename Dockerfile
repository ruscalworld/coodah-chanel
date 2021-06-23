FROM openjdk:11.0-jre

WORKDIR /home/container
ADD build/libs/CoodahChanel-1.0-all.jar .
CMD java -cp CoodahChanel-1.0-all.jar ru.ruscalworld.coodahchanel.CoodahChanel