package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.RedSocial;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IRedSocialService {

    public List<RedSocial> getAll();

    public RedSocial getById(Long id);

    public RedSocial save(RedSocial redSocial);

    public void deleteById(Long id);

    public boolean existsById(Long id);

}
