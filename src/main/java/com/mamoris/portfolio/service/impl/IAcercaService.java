package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Acerca;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IAcercaService {

    public List<Acerca> getAll();

    public Acerca getById(Long id);

    public Acerca save(Acerca acerca);

    public void deleteById(Long id);

    public boolean existsById(Long id);
    
     public boolean existsByPersonaId(Long id);
     
     public Acerca getByPersonaId(Long id);

}
