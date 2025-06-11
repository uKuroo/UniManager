package br.edu.ifba.inf008.uniManager.domain.ports.exports;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;

public interface ICertificateExporter<T> {
    public void export(T obj);
}
