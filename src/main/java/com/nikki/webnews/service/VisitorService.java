package com.nikki.webnews.service;

import com.nikki.webnews.model.Visitor;
import com.nikki.webnews.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getVisitorData() {
        return visitorRepository.getVisitorData();
    }

    public int getVisitorCountByDate(LocalDate date) {
        Optional<Visitor> optionalVisitor = visitorRepository.findByDate(date);
        return optionalVisitor.map(Visitor::getCount).orElse(0);
    }

    public void incrementVisitorCount(LocalDate date) {
        Optional<Visitor> optionalVisitor = visitorRepository.findByDate(date);
        if (optionalVisitor.isPresent()) {
            Visitor visitor = optionalVisitor.get();
            visitor.setCount(visitor.getCount() + 1);
            visitorRepository.save(visitor);
        } else {
            Visitor newVisitor = new Visitor();
            newVisitor.setDate(date);
            newVisitor.setCount(1);
            visitorRepository.save(newVisitor);
        }
    }
}
