package pt.techfirm.luckynumbers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.techfirm.luckynumbers.model.SlotMachineModel;

@Repository
public interface SlotMachineRepository extends CrudRepository<SlotMachineModel, String> {
}
