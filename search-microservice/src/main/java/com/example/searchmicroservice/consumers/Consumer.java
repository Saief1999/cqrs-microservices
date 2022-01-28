package com.example.searchmicroservice.consumers;
import com.example.searchmicroservice.pojos.UpdateMessage;
import com.example.searchmicroservice.services.Projector;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer
{

    private Projector projector ;

    public Consumer(Projector projector) {
        this.projector = projector;
    }
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void receiveUpdate(UpdateMessage updateMessage) throws IOException {


        if (updateMessage.isDeleted()) { // a movie has been deleted
            projector.removeMovie(updateMessage.getMovie());
        }
        else { // a movie has been inserted / updated
            projector.upsertMovie(updateMessage.getMovie());
        }
    }

}
