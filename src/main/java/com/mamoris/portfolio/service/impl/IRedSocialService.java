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

    public RedSocial save(RedSocial user);

    public void deleteById(Long id);


}
