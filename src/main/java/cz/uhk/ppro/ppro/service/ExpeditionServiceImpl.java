package cz.uhk.ppro.ppro.service;

import cz.uhk.ppro.ppro.model.Expedition;
import cz.uhk.ppro.ppro.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpeditionServiceImpl implements ExpeditionService{

    ExpeditionRepository expeditionRepository;

    @Autowired
    public ExpeditionServiceImpl(ExpeditionRepository expeditionRepository) {
        this.expeditionRepository = expeditionRepository;
    }

    @Override
    public List<Expedition> getAllExpeditions() {
        return expeditionRepository.findAll();
    }

    @Override
    public Expedition getExpeditionById(long id) {
        return expeditionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteExpeditionById(long id) {
        Optional<Expedition> expedition = expeditionRepository.findById(id);
        if (expedition.isPresent()){
            expeditionRepository.delete(expedition.get());
        }
    }

    @Override
    public void saveExpedition(Expedition expedition) {
        expeditionRepository.save(expedition);
    }
}
