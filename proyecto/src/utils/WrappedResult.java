package utils;

import entities.QualityAttributeInterface;

public class WrappedResult implements Comparable<WrappedResult> {
        QualityAttributeInterface qualityAttributeInterface;
        Double value;

        public WrappedResult(QualityAttributeInterface qualityAttributeInterface, Double value){
                this.qualityAttributeInterface = qualityAttributeInterface;
                this.value = value;
        }
        public QualityAttributeInterface getQualityAttributeInterface() {
                return qualityAttributeInterface;
        }
        public void setQualityAttributeInterface(
                        QualityAttributeInterface qualityAttributeInterface) {
                this.qualityAttributeInterface = qualityAttributeInterface;
        }
        public Double getValue() {
                return value;
        }
        public void setValue(Double value) {
                this.value = value;
        }

        @Override
        public int compareTo(WrappedResult arg0) {
                return this.getValue().compareTo(arg0.getValue());
        }
}
