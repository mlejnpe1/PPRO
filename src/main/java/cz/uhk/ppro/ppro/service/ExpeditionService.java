package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Expedition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpeditionService {

    List<Expedition> getAllExpeditions();
    Expedition getExpeditionById(long id);
    void deleteExpeditionById(long id);
    void saveExpedition(Expedition expedition);

}
