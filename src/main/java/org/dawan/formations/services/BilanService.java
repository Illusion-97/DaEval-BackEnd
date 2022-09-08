package org.dawan.formations.services;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface BilanService {
    int generate3To5EvalPerStudientPerBlocForPromo(long promotionId);
    String GETgenerate(long etudiantId, long promotionId) throws IOException, TemplateException;
}
