package comidev.dswgrupo5.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    @Autowired
    private RolRepo rolRepo;

    public Rol getRolByNameRol(String rolName) {
        return rolRepo.findByRolName(rolName);
    }
}
