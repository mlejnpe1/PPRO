package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Expedition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpeditionService {

    List<Expedition> getAllExpeditions();
    Expedition getExpeditionById(Long id);
    void deleteExpeditionById(Long id);
    void saveExpedition(Expedition expedition);

}
